package com.mbsgood.pulltorefreshloaddata;

import android.app.Activity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/*
* User: ChenCHaoXue
* Create date: 2016-12-07 
* Time: 13:47 
* From VCard
* 上下拉刷新的封装类
*/
public abstract class PagingRefreshHelper {

    private LinearLayout mEmptyView;//可以设置默认图片
    private PullToRefreshListView mPagingListView = null;
    private Activity mActivity = null;
    private int page = 1;
    private boolean hasNew;

    public PagingRefreshHelper(PullToRefreshListView listView) {
        this(listView, false);
    }

    /*
    * params hasNew 支持下拉刷新
    * */
    public PagingRefreshHelper(PullToRefreshListView listView, boolean hasNew) {
        this.mPagingListView = listView;
        this.hasNew = hasNew;
        this.mActivity = (Activity) mPagingListView.getContext();
        configListView();
    }

    /*
    * 配置上下拉刷新与点击功能
    * */
    public void configListView() {
        mPagingListView.setOnItemClickListener(mListItemClickListener);
        mPagingListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (hasNew) {
                    refresh();
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                onLoadMoreItems();
            }
        });
    }

    /**
     * once this method called,the progressbar will not visible
     *
     * @param imgRes set the icon image,-1 will make the view GONE;
     * @param text   set the text to show when no data in the list view,null will make the view GONE;
     */
    public void setEmptyView(int imgRes, String text) {
        if (mEmptyView != null) {
            mPagingListView.getRefreshableView().removeHeaderView(mEmptyView);
            mEmptyView = null;
        }
        mEmptyView = (LinearLayout) mActivity.getLayoutInflater().inflate(R.layout.empty_list_view, null);
        TextView textView = (TextView) mEmptyView.findViewById(R.id.text);
        ImageView imageView = (ImageView) mEmptyView.findViewById(R.id.icon);

        if (imgRes >= 0) {
            imageView.setImageResource(imgRes);
        } else {
            imageView.setVisibility(View.GONE);
        }

        if (text != null) {
            textView.setText(text);
        } else {
            textView.setVisibility(View.GONE);
        }

        mPagingListView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mPagingListView.removeOnLayoutChangeListener(this);
                mEmptyView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, v.getHeight()));//设置全屏
            }
        });
    }


    /*
    * 请求数据成功调用
    * 开启上下拉开关，与头部信息
    * */
    public void onFinishRequest(boolean hasMore, ArrayList<?> list) {
        ListAdapter adapter = ((HeaderViewListAdapter) mPagingListView.getRefreshableView().getAdapter()).getWrappedAdapter();
        ((PagingBaseAdapter) adapter).addMoreItems(list);

        mPagingListView.onRefreshComplete();

        if (hasMore && hasNew) {
            mPagingListView.setMode(PullToRefreshBase.Mode.BOTH);
        } else if (hasMore) {
            mPagingListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        } else if (hasNew) {
            mPagingListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        } else {
            mPagingListView.setMode(PullToRefreshBase.Mode.DISABLED);
        }

        if (mEmptyView == null) return;
        mPagingListView.getRefreshableView().removeHeaderView(mEmptyView);
        if (adapter.isEmpty()) {
            mPagingListView.getRefreshableView().addHeaderView(mEmptyView, null, false);
        }
    }


    /*
    * 上拉刷新
    * */
    private void onLoadMoreItems() {
        sendRequest(++page);
    }

    /*
    * 封装下拉刷新，下拉时移除所有的数据，重新请求新数据
    * */
    public void refresh() {
        page = 1;
        ListAdapter adapter = ((HeaderViewListAdapter) mPagingListView.getRefreshableView().getAdapter()).getWrappedAdapter();
        if (adapter instanceof PagingBaseAdapter) {
            ((PagingBaseAdapter<?>) adapter).removeAllItems();
        }
        sendRequest(page);
    }


    private AdapterView.OnItemClickListener mListItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onListItemClick(view, position, id);
        }
    };

    public abstract void sendRequest(int page);

    public abstract void onListItemClick(View view, int position, long id);
}
