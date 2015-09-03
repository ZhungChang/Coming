package com.example.yuchi.coming.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

import com.example.yuchi.coming.R;

/**
 * Created by choes on 2015/9/1.
 */
public class EventDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Delete the title of the AlertDialog.
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
            }
        });
        return builder.create();
    }
}
