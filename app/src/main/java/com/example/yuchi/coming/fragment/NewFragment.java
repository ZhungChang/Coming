package com.example.yuchi.coming.fragment;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.common.database.TimerDbHelper;
import com.example.yuchi.coming.common.database.TimerPack;

import java.util.Timer;

/**
 * Created by choes_000 on 2015/3/29.
 */
public class NewFragment extends Fragment {

    //Numberpickers that sets up the alarm time
    private NumberPicker hrPicker,minPicker,secPicker;

    //Button mEnter that saves the data into database
    private Button mEnter;

    //Edittext mEdit that saves the content.
    private EditText mEdit;

    //Integer tmp that saves the time setting.
    private int tmp;

    private TimerDbHelper timerdbhelper;

    public NewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        timerdbhelper = new TimerDbHelper(getActivity());

        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        // END_INCLUDE (inflate_set_custom_view)
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the fragment_new layout
        View v = inflater.inflate(R.layout.fragment_new, container, false);

        tmp = 0;

        mEdit = (EditText) v.findViewById(R.id.new_contentEdit);

        hrPicker = (NumberPicker) v.findViewById(R.id.hr);
        minPicker = (NumberPicker) v.findViewById(R.id.min);
        secPicker = (NumberPicker) v.findViewById(R.id.sec);

        hrPicker.setMaxValue(23);
        hrPicker.setMinValue(0);
        minPicker.setMaxValue(59);
        minPicker.setMinValue(0);
        secPicker.setMaxValue(59);
        secPicker.setMinValue(0);

        hrPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tmp = tmp + newVal * 60 * 60;
            }
        });

        minPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tmp = tmp + newVal * 60;
            }
        });

        secPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tmp = tmp + newVal;
            }
        });

        mEnter = (Button) v.findViewById(R.id.enter);
        mEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new insertDataInBackground();
            }
        });
        return v;
    }

    private class insertDataInBackground extends AsyncTask<TimerPack, Void,Void>{

        @Override
        protected Void doInBackground(TimerPack... params) {
            timerdbhelper.add(timerdbhelper,mEdit.getText().toString(),tmp);
            return null;
        }

    }
}
