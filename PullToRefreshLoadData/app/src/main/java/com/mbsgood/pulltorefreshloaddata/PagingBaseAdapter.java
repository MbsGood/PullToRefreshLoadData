package com.mbsgood.pulltorefreshloaddata;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/*
* User: ChenCHaoXue
* Create date: 2016-12-07 
* Time: 14:01 
* From VCard
* 适配器封装
*/
public abstract class PagingBaseAdapter<T> extends BaseAdapter {
    protected List<T> items;

    public PagingBaseAdapter() {
        this.items = new ArrayList<T>();
    }

    public PagingBaseAdapter(List<T> items) {
        this.items = items;
    }

    public void addMoreItems(List<T> newItems) {
        this.items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void removeAllItems() {
        this.items.clear();
        notifyDataSetChanged();
    }

    /*
    * add a new method,delete a item
    * */
    public void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }
}
