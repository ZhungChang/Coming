package com.example.yuchi.coming.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.yuchi.coming.R;
import com.example.yuchi.coming.TimerDbHelper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by choes_000 on 2015/3/16.
 * Connecting the TimeFragment.
 */
public class MainActivity extends Activity{

    private static final String TAG = "MainActivity";

    private TimerDbHelper timerdbhelper;
    private SQLiteDatabase db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (findViewById(R.id.sample_main_layout) != null) {
            if (savedInstanceState != null){
                return;
            }
            //Open the database, if null, create a database.
            openDatabase();

            EventFragment fragment = new EventFragment();
            createPage(fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_new:
                newEvent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void newEvent(){
        // Otherwise, we're in the one-pane layout and must swap frags...
        // Create fragment and give it an argument for the selected article
        NewFragment newFragment = new NewFragment();
        openPage(newFragment);
    }

    public SQLiteDatabase openDatabase(){
        timerdbhelper = new TimerDbHelper(this);
        db = timerdbhelper.getReadableDatabase();
        return db;
    }

    public void createPage(Fragment f){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, f);
        fragmentTransaction.commit();
    }

    public void openPage(Fragment f){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        fragmentTransaction.replace(R.id.fragment_container, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}