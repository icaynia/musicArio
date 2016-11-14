package com.icaynia.musicario;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Created by icaynia on 2016. 11. 14..
 */
public class MusicListFragment {
    private Context context;
    private View v;
    private String TAG = "MusicListFragment";
    private ListView list;
    private Global global;


    public MusicListFragment(Context _context, View _v) {
        this.context = _context;
        this.v = _v;
        Log.d(TAG, "MusicListFragment is created.");
        viewInitialize();
    }

    private void viewInitialize() {
        global = ((TabbedActivity)context).global;
        list = (ListView) v.findViewById(R.id.fragment_musiclist_listView);

    }






}
