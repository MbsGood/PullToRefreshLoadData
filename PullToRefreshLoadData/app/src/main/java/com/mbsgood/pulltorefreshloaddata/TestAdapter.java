package com.mbsgood.pulltorefreshloaddata;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mbsgood.pulltorefreshloaddata.data.ChildEntity;

/*
* User: ChenCHaoXue
* Create date: 2016-12-07 
* Time: 14:24 
* From VCard
*/
public class TestAdapter extends BaseListAdapter<ChildEntity> {
    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.layout_item, parent, false);
            viewHolder.tvName=(TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ChildEntity entity = getItem(position);
        viewHolder.tvName.setText(entity.getTradeName());
        return convertView;
    }

    private class ViewHolder {
        TextView tvName;
    }
}
