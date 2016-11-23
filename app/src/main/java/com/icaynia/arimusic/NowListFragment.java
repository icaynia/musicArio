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
    private PlayListAdapter musicListAdapter;

    private ObjectFileManager ofm;



    public NowListFragment (Context _context, View _v) {
        this.context = _context;
        this.v = _v;
        Log.e(TAG, TAG + " is created");
        initialize();
    }

    public void initialize() {
        viewInitialize();
        global = (Global) context.getApplicationContext();
        ofm = new ObjectFileManager(context);

        ArrayList<PlayList> pl = ofm.getFileList();
        if (pl.size() != 0) {
            musicListAdapter = new PlayListAdapter(((TabbedActivity) context), pl);
            nowListView.setAdapter(musicListAdapter);
        } else {
        }

        nowListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(((TabbedActivity)context), PlayListActivity.class);
                intent.putExtra("listid", id);

                context.startActivity(intent);
            }
        });
        //nowListView.setAdapter(musicListAdapter);
    }

    public void viewInitialize() {
        nowListView = (ListView) v.findViewById(R.id.fragment_playlist_listView);
        noListTv = (TextView) v.findViewById(R.id.fragment_playlist_nolist);

    }

}
