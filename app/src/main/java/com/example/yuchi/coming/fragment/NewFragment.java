package com.example.yuchi.coming.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.TimerDbHelper;
import com.example.yuchi.coming.TimerPack;

/**
 * Created by choes_000 on 2015/3/29.
 */
public class NewFragment extends Fragment {

    private static final String TAG = "NewFragment";
    //The saved data from the database.
    public static final String SAVED_ID = "Second";
    public static final String SAVED_SECOND = "Second";
    public static final String SAVED_EVENT = "Event";
    //Get view of activity.
    private View view;

    private Menu menu;
    //Numberpickers that sets up the alarm time
    private NumberPicker hrPicker,minPicker,secPicker;
    //Edittext mEdit that saves the content.
    private EditText mEdit;
    //Integer tmp that saves the time setting.
    private int Hr,Min,Sec, Total;

    private Bundle args;

    private boolean hasBundle;

    private TimerDbHelper timerdbhelper;

    public NewFragment() {
        // Required empty public constructor
    }

    public static NewFragment newInstance(int id, String event, int second){
        NewFragment newFragment = new NewFragment();
        Bundle args = new Bundle();
        args.putInt(SAVED_ID, id);
        args.putString(SAVED_EVENT, event);
        args.putInt(SAVED_SECOND, second);
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        timerdbhelper = new TimerDbHelper(getActivity());
        args = getArguments();
        hasBundle = false;

        //To decide that how the fragment to apply
        if (args == null) {
            //Initiate the countdown second.
            Hr = 0 ;
            Min = 0;
            Sec = 0;
            Total = 0;
        } else {
            hasBundle = true;
            Total = getArguments().getInt(SAVED_SECOND);
            Sec = Total%3600;
            Min = (Total-Sec)%60;
            Hr = (Total-Sec-Min*60)/3600;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the fragment_new layout
        view = inflater.inflate(R.layout.fragment_new, container, false);

        //Connect with the space of inputing event.
        mEdit = (EditText) view.findViewById(R.id.new_contentEdit);

        hrPicker = (NumberPicker) view.findViewById(R.id.hr);
        minPicker = (NumberPicker) view.findViewById(R.id.min);
        secPicker = (NumberPicker) view.findViewById(R.id.sec);

        hrPicker.setFormatter(new NumberPicker.Formatter(){
            @Override
            public String format(int value) {
                if (value < 10) {
                    return "0" + value;
                }
                else{
                    return "" + value;
                }
            }
        });
        minPicker.setFormatter(new NumberPicker.Formatter(){
            @Override
            public String format(int value) {
                if (value < 10) {
                    return "0" + value;
                }
                else{
                    return "" + value;
                }
            }
        });
        secPicker.setFormatter(new NumberPicker.Formatter(){
            @Override
            public String format(int value) {
                if (value < 10) {
                    return "0" + value;
                }
                else{
                    return "" + value;
                }
            }
        });

        hrPicker.setMaxValue(23);
        hrPicker.setMinValue(0);
        minPicker.setMaxValue(59);
        minPicker.setMinValue(0);
        secPicker.setMaxValue(59);
        secPicker.setMinValue(0);

        if( args != null){
            hrPicker.setValue(Hr);
            minPicker.setValue(Min);
            secPicker.setValue(Sec);
            mEdit.setText(getArguments().getString(SAVED_EVENT));
        }

        hrPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Hr = newVal * 3600;
            }
        });

        minPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Min = newVal * 60;
            }
        });

        secPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Sec = newVal;
            }
        });
        return view;
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
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
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
    public void onPause(){
        super.onPause();
        if(hasBundle == true){
            args.clear();
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

        String event = mEdit.getText().toString().trim();
        if (event.length() <= 0) {
            mEdit.setError("Blank field!");
            return;
        }

        Total = Sec + Min + Hr;
        long rowId = (hasBundle) ? timerdbhelper.updateFav(new TimerPack(getArguments().getInt(SAVED_ID) ,event ,Total))
                                  : timerdbhelper.add(new TimerPack(event, Total));
        if (rowId != -1) {
            Toast.makeText(getActivity(), R.string.msg_InsertSuccess,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), R.string.msg_InsertFail,
                    Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG, "ID: " + rowId + " Content:" + event + " Total second:" + Total);
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
                .replace(R.id.fragment_container, eventFragment)
                // Commit the transaction
                .commit();
    }
}
