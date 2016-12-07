package com.mbsgood.pulltorefreshloaddata;

import android.content.Context;
import android.view.LayoutInflater;

/*
* User: ChenCHaoXue
* Create date: 2016-12-07 
* Time: 14:20 
* From VCard
*/
public abstract class BaseListAdapter<T> extends PagingBaseAdapter<T> {
    public LayoutInflater mInflater;

    public BaseListAdapter(Context context) {
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
