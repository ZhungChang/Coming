package com.example.yuchi.coming.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.TimerDbHelper;
import com.example.yuchi.coming.TimerPack;

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
    private Cursor cursor;

    private TextView empty;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        empty = (TextView) rootView.findViewById(R.id.empty);
        if(dbHelper.getCount()>0){
            empty.setVisibility(View.GONE);
        }else {
            empty.setVisibility(View.VISIBLE);
        }
        return rootView;
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(runnable);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getActionBar().setTitle(R.string.actionbar_list);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);

        mHandler.postDelayed(runnable, 500);
        //Call back DialogFragment
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
                                           long arg3) {
                // TODO Auto-generated method stub
                cursor = dbHelper.fetchEvents();
                cursor.moveToPosition(arg2);
                showDialog(cursor.getInt(0));
                Log.i(TAG, "Position:" + arg2 + " Id:" + arg3 + " _ID:" + cursor.getInt(0));
                return true;
            }
        });
    }

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mAdapter.notifyDataSetChanged();
            mHandler.postDelayed(runnable,1000);
        }
    };

    void showDialog(int id) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DialogFragment newFragment = new EventDialogFragment().newInstance(id);
        newFragment.show(getFragmentManager(), "dialog");
    }
}