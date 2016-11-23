package com.icaynia.arimusic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 20..
 */
public class NowListFragment {

    public Context context;
    public View v;
    public Global global;

    private String TAG = "MyMusicFragment";

    /* view */
    private ListView nowListView;
    private TextView noListTv;

    /* back */
    private PlayListAdapter pla;
    private PlayListManager plm;

    public NowListFragment (Context _context, View _v) {
        this.context = _context;
        this.v = _v;
        Log.e(TAG, TAG + " is created");
        initialize();
    }

    public void viewInitialize() {
        nowListView = (ListView) v.findViewById(R.id.fragment_playlist_listView);
        noListTv = (TextView) v.findViewById(R.id.fragment_playlist_nolist);
    }

    public void initialize() {
        viewInitialize();
        global = (Global) context.getApplicationContext();
        plm = new PlayListManager(context);

        /* listview initialize */
        pla = new PlayListAdapter((TabbedActivity)context, getPlayListAll());
        nowListView.setAdapter(pla);


    }

    public ArrayList<PlayList> getPlayListAll() {
        return plm.getPlayListAll();
    }


}
