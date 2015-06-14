package com.example.yuchi.coming.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yuchi.coming.R;


/**
 * Created by choes_000 on 2015/3/29.
 */
public class EventAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    public EventAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null){
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_event_list,null);
            vh.content = (TextView) convertView.findViewById(R.id.event);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        return null;
    }

    public static class ViewHolder{
        public TextView content;
        public TextView time;
    }
}
