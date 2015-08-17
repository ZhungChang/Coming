package com.example.yuchi.coming.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.common.database.TimerDbHelper;
import com.example.yuchi.coming.common.database.TimerPack;

/**
 * Created by choes_000 on 2015/3/29.
 */
public class NewFragment extends Fragment {

    private SQLiteDatabase mDb;
    Menu menu;

    //Numberpickers that sets up the alarm time
    private NumberPicker hrPicker,minPicker,secPicker;

    //Edittext mEdit that saves the content.
    private EditText mEdit;

    //Integer tmp that saves the time setting.
    private int Sec;

    private TimerDbHelper timerdbhelper;

    public NewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        timerdbhelper = new TimerDbHelper(getActivity());

        // END_INCLUDE (inflate_set_custom_view)
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the fragment_new layout
        View v = inflater.inflate(R.layout.fragment_new, container, false);

        Sec = 0;

        //Connect with the space of inputing event.
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
                Sec = Sec + newVal * 60 * 60;
            }
        });

        minPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Sec = Sec + newVal * 60;
            }
        });

        secPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Sec = Sec + newVal;
            }
        });
        return v;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_add:
                //Add a new event to the database.
                InsertClick();

                return true;
            case android.R.id.home:
                getFragmentManager().popBackStack();
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {

        //Show the menu menu_new.
        this.menu = menu;
        inflater.inflate(R.menu.menu_new, menu);

        //Hide the menu  menu_main.
        menu.setGroupVisible(R.id.main_menu_group, false);
    }

    public void InsertClick() {
        Context context = getActivity().getApplicationContext();
        String event = mEdit.getText().toString().trim();
        if (event.length() <= 0) {
            Toast.makeText(context, R.string.msg_NameIsInvalid,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        TimerPack timerPack = new TimerPack(event,Sec);

        long rowId = timerdbhelper.add(mDb);
        if (rowId != -1) {
            Toast.makeText(context, R.string.msg_InsertSuccess,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.msg_InsertSuccess,
                    Toast.LENGTH_SHORT).show();
        }

        changeFragment();
    }

    public void changeFragment(){
        // Otherwise, we're in the one-pane layout and must swap frags...

        // Create fragment and give it an argument for the selected article
        EventFragment eventFragment = new EventFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        getFragmentManager().beginTransaction()
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                .add(R.id.fragment_container, eventFragment)
                // Commit the transaction
                .commit();
    }
}
