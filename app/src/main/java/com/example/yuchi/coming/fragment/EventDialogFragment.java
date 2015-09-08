package com.example.yuchi.coming.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.TimerDbHelper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by choes on 2015/9/7.
 */
public class EventDialogFragment extends DialogFragment {
    private int index;
    private EventAdapter mAdapter;
    private List<HashMap<String, Object>> list;
    private TimerDbHelper dbHelper;
    private Cursor cursor;
    public static final String TAG = "EventDialogFragment";

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    public static EventDialogFragment newInstance(int num) {
        EventDialogFragment f = new EventDialogFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }

    public EventDialogFragment(){};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dbHelper = new TimerDbHelper(getActivity());
        index = getArguments().getInt("num");
        Log.i(TAG, "EventDialog: " + index);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.event_changed_array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
                switch (which) {
                    case 0:
                        doEdit(index);
                        break;
                    case 1:
                        doDelete(index);
                        break;
                }
            }
        });
        //Cancel the AlertDialog.
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Do nothing.
            }
        });
        return builder.create();
    }

    //Edit the selected item.
    public void doEdit(int id){
        cursor = dbHelper.fetchTheEvent(id);
        cursor.moveToFirst();
        Log.i(TAG, "saved_ID:" + cursor.getInt(0) + " savedContent:" + cursor.getString(1) + " savedSecond:" + cursor.getInt(2));
        NewFragment newFragment = new NewFragment().newInstance(cursor.getInt(0),
                cursor.getString(1),cursor.getInt(2));
        applyNewFragment(newFragment);
    }

    //Delete the selected item.
    public void doDelete(int id){
        Log.i(TAG, "doDelete ID: "+ id);
        dbHelper.removeFav(id);

        EventFragment f = new EventFragment();
        list = dbHelper.getData();
        mAdapter = new EventAdapter(getActivity(), list);
        mAdapter.notifyDataSetChanged();
        f.setListAdapter(mAdapter);
        applyNewFragment(f);
    }

    public void applyNewFragment(Fragment f){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        fragmentTransaction.replace(R.id.fragment_container, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
