package com.example.yuchi.coming.fragment;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.common.database.TimerDbHelper;

import org.w3c.dom.Text;

/**
 * Created by choes_000 on 2015/3/29.
 */
public class EventAdapter extends BaseAdapter{

    //Inflate the view.
    private LayoutInflater mInflater;

    public Cursor mCursor;

    private Context context;

    private EventAdapter(Context context, Cursor cursor)
    {
        super();

        //According to the context
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        mCursor = cursor;
    }

    //Get the count of items which is the array recieved from constructor or the index of a set.
    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    //Get the item in the position where is the data or the set live in.
    @Override
    public Object getItem(int position) {
        return position;
    }

    //Get the ID of the item in the position, can be represent by the value of the postion.
    @Override
    public long getItemId(int position) {
        return position;
    }

    //The convertView is setting to the View of the item in this position.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_event_list, null);
            viewholder = new ViewHolder();
            //viewholder.content = (TextView) convertView.findViewById(R.id.)
            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder) convertView.getTag();}
        return null;
    }

    static class ViewHolder
    {
        TextView time;
        TextView content;
    }
}
