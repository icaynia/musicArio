package com.icaynia.musicario;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * Created by icaynia on 2016. 11. 20..
 */
public class NowListFragment {

    public Context context;
    public View v;
    public Global global;

    private String TAG = "NowListFragment";

    /* view */
    private ListView nowListView;


    public NowListFragment (Context _context, View _v) {
        this.context = _context;
        this.v = _v;
        Log.e(TAG, TAG + " is created");
        initialize();
    }

    public void initialize() {
        viewInitialize();
        global = (Global) context.getApplicationContext();
 
    }

    public void viewInitialize() {


    }

}
