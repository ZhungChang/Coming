package com.example.yuchi.coming.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.TimerDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by choes_000 on 2015/3/29.
 */
public class EventAdapter extends BaseAdapter{

    private static final String TAG = "EventAdapter";

    //Inflate the view.
    private LayoutInflater mInflater;

    private List<HashMap<String, Object>> eventList;

    public EventAdapter(Context context, List<HashMap<String, Object>> eventList)
    {
        super();
        this.eventList = eventList;
        //According to the context
        this.mInflater = LayoutInflater.from(context);
    }

    //Get the count of items which is the array recieved from constructor or the index of a set.
    @Override
    public int getCount() {
        Log.i(TAG, "Count :" + eventList.size());
        return eventList.size();
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
            convertView = mInflater.inflate(R.layout.item_event_list, null);
            viewholder = new ViewHolder();
            viewholder.content = (TextView) convertView.findViewById(R.id.event);
            viewholder.time = (TextView) convertView.findViewById(R.id.event_time);
            convertView.setTag(viewholder);
        }else{viewholder = (ViewHolder) convertView.getTag();}

        //Set text in the event field.
        String text = (String) eventList.get(position).get("event");
        //Set seconds in the time field.
        int sec = (int) eventList.get(position).get("second");

        long outputTime;

        if(sec*1000 - System.currentTimeMillis() > 0){
            outputTime = System.currentTimeMillis() - sec*1000;
        }else{
            outputTime =  86399000 - System.currentTimeMillis() + sec*1000;
        }

        Date date = new java.util.Date(outputTime);
        String result = new SimpleDateFormat("hh:mm:ss").format(date);

        viewholder.content.setText(text);
        viewholder.time.setText(result);

        return convertView;
    }

    static class ViewHolder
    {
        TextView time;
        TextView content;
    }
}
