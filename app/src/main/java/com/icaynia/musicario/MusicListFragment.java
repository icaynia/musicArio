package com.icaynia.musicario;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Created by icaynia on 2016. 11. 14..
 */
public class MusicListFragment {
    private Context context;
    private View v;
    private String TAG = "MusicListFragment";
    private ListView listView;
    private Global global;


    public MusicListFragment(Context _context, View _v) {
        this.context = _context;
        this.v = _v;
        Log.i(TAG, "MusicListFragment is created.");
        viewInitialize();
    }

    private void viewInitialize() {
        global = ((TabbedActivity)context).global;
        listView = (ListView) v.findViewById(R.id.fragment_musiclist_listView);

        MusicListAdapter adapter = new MusicListAdapter(((TabbedActivity)context), global.mediaList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                global.playMusic(position);
            }
        });


    }






}
