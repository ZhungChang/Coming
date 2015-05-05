package com.example.yuchi.coming.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.yuchi.coming.R;

import java.text.SimpleDateFormat;

/**
 * Created by choes_000 on 2015/4/3.
 */
public class TimeSettingDialogFragment extends DialogFragment {
    private NumberPicker HR,MIN,SEC;
    private EditText time_setting;

    public static TimeSettingDialogFragment newInstance(int title) {
        TimeSettingDialogFragment frag = new TimeSettingDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View v = mInflater.inflate(R.layout.timepicker,null);
        time_setting = (EditText) v.findViewById(R.id.new_timeDialog);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setView(v)
                .setPositiveButton(R.string.enter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                                String time = String.valueOf(HR.getValue()) + ":" + String.valueOf(MIN.getValue()) + ":" + String.valueOf(SEC.getValue());
                                time_setting.setText(df.format(time));
                            }
                        }
                )
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .create();
    }

}
