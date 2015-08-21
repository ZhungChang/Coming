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

    //Inflate the view.
    private LayoutInflater mInflater;

    private EventAdapter(Context context)
    {
        //According to the context
        this.mInflater = LayoutInflater.from(context);
    }

    //Get the count of items which is the array recieved from constructor or the index of a set.
    @Override
    public int getCount() {
        return 0;
    }

    //Get the item in the position where is the data or the set live in.
    @Override
    public Object getItem(int position) {
        return null;
    }

    //Get the ID of the item in the position, can be represent by the value of the postion.
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //The convertView is setting to the View of the item in this position.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        if(convertView == null){
            viewholder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_event_list, null);
        }
        return null;
    }

    static class ViewHolder
    {
        TextView time;
        TextView content;
    }
}
