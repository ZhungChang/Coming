package com.example.yuchi.coming.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.yuchi.coming.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by choes_000 on 2015/4/3.
 */
public class TimeSettingDialogFragment extends DialogFragment {
    private NumberPicker HR,MIN,SEC;
    private Button time_setting;
    private View v,v1;
    private LayoutInflater mInflater;
    private int title;

    public TimeSettingDialogFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getArguments().getInt("title");
        mInflater = LayoutInflater.from(getActivity());
        v = mInflater.inflate(R.layout.timepicker, null);
        time_setting = (Button) getActivity().findViewById(R.id.new_timeDialog);

        HR = (NumberPicker) v.findViewById(R.id.hr);
        MIN = (NumberPicker) v.findViewById(R.id.min);
        SEC = (NumberPicker) v.findViewById(R.id.sec);

        HR.setMaxValue(23);
        HR.setMinValue(0);
        MIN.setMaxValue(59);
        MIN.setMinValue(0);
        SEC.setMaxValue(59);
        SEC.setMinValue(0);
    }

    public static TimeSettingDialogFragment newInstance(int title) {
        TimeSettingDialogFragment frag = new TimeSettingDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setView(v)
                .setPositiveButton(R.string.enter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                                Date date = new Date();
                                String time = Integer.toString(HR.getValue()) + ":" + Integer.toString(MIN.getValue()) + ":" + Integer.toString(SEC.getValue());
                                time_setting.setText(time);
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
