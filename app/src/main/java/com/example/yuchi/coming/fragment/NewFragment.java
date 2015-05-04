package com.example.yuchi.coming.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.yuchi.coming.R;

/**
 * Created by choes_000 on 2015/3/29.
 */
public class NewFragment extends Fragment {
    private EditText editEvent;
    private View v;
    private String event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_new, container, false);
        //This is the adapter we use to populate the list.
        editEvent = (EditText) v.findViewById(R.id.new_contentEdit);
        event = editEvent.getText().toString();
        return inflater.inflate(R.layout.fragment_new, container, false);
    }
}
