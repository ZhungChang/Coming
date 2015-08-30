package com.example.yuchi.coming.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.TimerDbHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by choes_000 on 2015/3/23.
 */
public class EventFragment extends ListFragment {

    private static final String TAG = "EventFragment";

    private EventAdapter mAdapter;
    private List<HashMap<String, Object>> list;
    private TimerDbHelper dbHelper;

    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        dbHelper = new TimerDbHelper(getActivity());
        list = dbHelper.getData();
        mAdapter = new EventAdapter(getActivity(), list);

        mHandler = new Handler();
        mHandler.post(runnable);

        setListAdapter(mAdapter);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(runnable);
        super.onPause();
    }

    @Override
    public void onResume() {
        mHandler.postDelayed(runnable, 500);
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        return rootView;
    }

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mAdapter.notifyDataSetChanged();
            mHandler.postDelayed(runnable,1000);
        }
    };
}
