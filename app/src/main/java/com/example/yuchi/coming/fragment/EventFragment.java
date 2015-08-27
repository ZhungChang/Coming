package com.example.yuchi.coming.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.TimerDbHelper;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        dbHelper = new TimerDbHelper(getActivity());
        list = dbHelper.getData();
        mAdapter = new EventAdapter(getActivity(), list);
        setListAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        return rootView;
    }
}
