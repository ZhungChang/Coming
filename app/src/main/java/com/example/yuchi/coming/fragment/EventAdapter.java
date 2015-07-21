package com.example.yuchi.coming.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Created by choes_000 on 2015/3/29.
 */
public class EventAdapter extends BaseAdapter{
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
        ViewHolder viewholder;
        if(convertView == null){
            viewholder = new ViewHolder();
        }
        return null;
    }

    static class ViewHolder
    {
        TextView time;
        TextView content;
    }
}
