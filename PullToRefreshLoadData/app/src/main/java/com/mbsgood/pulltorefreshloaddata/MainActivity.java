package com.mbsgood.pulltorefreshloaddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mbsgood.pulltorefreshloaddata.data.ChildEntity;
import com.mbsgood.pulltorefreshloaddata.data.TestEntity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PullToRefreshListView pullToRefreshListView;
    PagingRefreshHelper pagingRefreshHelper;

    TestEntity testEntity=new TestEntity();
    ArrayList<ChildEntity> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullToRefreshListView=(PullToRefreshListView)findViewById(R.id.refresh_list);

        TestAdapter testAdapter=new TestAdapter(this);
        pullToRefreshListView.setAdapter(testAdapter);

        pagingRefreshHelper=new PagingRefreshHelper(pullToRefreshListView,true) {

            @Override
            public void sendRequest(final int page) {
                /*
                * 可以请求网络数据
                * */
                list.clear();
                if(page==1){
                    for(int i=0;i<4;i++){
                        list.add(i,new ChildEntity(i+" Goods "+" 页数-->"+page));
                    }
                }else if(page>1) {
                    list.add(list.size(),new ChildEntity(" Goods "+" 页数-->"+page));
                }
//                list.clear();//无数据时
                testEntity.setList(list);
                testEntity.setHasNext(true);
                /*
                * 测试专用
                * */
                if(page==3){
                    testEntity.setHasNext(false);
                }

                pullToRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"请求页数--> "+ page,Toast.LENGTH_SHORT).show();
                        pagingRefreshHelper.onFinishRequest(testEntity.isHasNext(),testEntity.getList());
                    }
                },1000);

            }

            @Override
            public void onListItemClick(View view, int position, long id) {
                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        };
        pagingRefreshHelper.refresh();
        pagingRefreshHelper.setEmptyView(R.mipmap.ic_launcher,"test Tips");
    }
}
