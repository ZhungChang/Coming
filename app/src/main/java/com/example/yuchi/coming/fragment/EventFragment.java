package com.example.yuchi.coming.fragment;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by choes_000 on 2015/3/23.
 */
public class EventFragment extends Fragment {

    protected EventAdapter mAdapter;
    protected String[] mDataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
    }
}
