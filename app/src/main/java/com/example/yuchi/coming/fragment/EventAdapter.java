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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Created by choes_000 on 2015/3/29.
 */
public class EventAdapter extends BaseAdapter{

    private static final String TAG = "EventAdapter";

    //Inflate the view.
    private LayoutInflater mInflater;

    private List<HashMap<String, Object>> eventList;

    private TimeZone timeZone;

    //private Date date;

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
            timeZone = TimeZone.getDefault();
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

        int local = timeZone.getRawOffset();

        Log.v(TAG,"local: " + local);

        if(sec - system > 0){
            local = sec - system;
        }else{
            local =  86399 - system + sec;
        }

        Log.i(TAG, " System time:"+system+" Cal: " + local +" Saved seconds:"+ sec);

        Date date = new Date(local);
        String result = new SimpleDateFormat("HH:mm:ss").format(date);

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