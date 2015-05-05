package com.example.yuchi.coming.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuchi.coming.R;

/**
 * Created by choes_000 on 2015/3/29.
 */
public class NewFragment extends Fragment {

    //EditText that sets up the alarm time.
    private EditText mTimeDialog;

    public NewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the fragment_new layout
        View v = inflater.inflate(R.layout.fragment_new, container, false);

        //

        return inflater.inflate(R.layout.fragment_new, container, false);
    }
}
