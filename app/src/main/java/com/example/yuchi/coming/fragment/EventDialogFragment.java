package com.example.yuchi.coming.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.TimerDbHelper;

/**
 * Created by choes on 2015/9/1.
 */
public class EventDialogFragment extends DialogFragment{
    private TimerDbHelper timerdbhelper;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.event_changed_array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
                switch (which){
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        });
        return builder.create();
    }
}
