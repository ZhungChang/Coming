package com.example.yuchi.coming.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.yuchi.coming.R;

/**
 * Created by choes_000 on 2015/3/29.
 */
public class NewFragment extends Fragment {

    //Numberpickers that sets up the alarm time
    private NumberPicker hrPicker,minPicker,secPicker;

    //Button mEnter that saves the data into database
    private Button mEnter;


    public NewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        mEnter = (Button) findViewById
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the fragment_new layout
        View v = inflater.inflate(R.layout.fragment_new, container, false);

        hrPicker = (NumberPicker) v.findViewById(R.id.hr);
        minPicker = (NumberPicker) v.findViewById(R.id.min);
        secPicker = (NumberPicker) v.findViewById(R.id.sec);

        hrPicker.setMaxValue(23);
        hrPicker.setMinValue(0);
        minPicker.setMaxValue(59);
        minPicker.setMinValue(0);
        secPicker.setMaxValue(59);
        secPicker.setMinValue(0);

        return v;
    }

}
