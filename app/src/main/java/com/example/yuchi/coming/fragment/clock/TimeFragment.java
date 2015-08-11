package com.example.yuchi.coming.fragment.clock;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuchi.coming.R;

import java.util.Date;
import android.os.Handler;

/**
 * Created by choes_000 on 2015/3/16.
 * Clock fragment
 */
public class TimeFragment extends Fragment {

    private TextView currentTime;
    private View view;
    private TimeText tt;
    private Date date;
    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for  fragment_time
        view = inflater.inflate(R.layout.fragment_time, container, false);
        currentTime = (TextView) view.findViewById(R.id.time);
        mHandler.post(runnable);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void init(){
        tt = new TimeText();
        mHandler = new Handler();
    }


    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            date = new Date();
            currentTime.setText(tt.StringTime(date));
            mHandler.postDelayed(runnable,1000);
        }
    };
}
