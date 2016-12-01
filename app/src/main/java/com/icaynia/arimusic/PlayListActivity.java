package com.icaynia.arimusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by icaynia on 2016. 11. 22..
 */
public class PlayListActivity extends AppCompatActivity{
    private String TAG = "PlayListActivity";

    /* view */
    private ListView listView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Log.e(TAG, TAG + " is created");
        initialize();
    }

    public void initialize() {
        viewInitialize();

        Intent intent = getIntent();
        String filename = intent.getStringExtra("filename");

        Log.e("PlayListActivity", filename);

        PlayList playList = getPlayList(filename);
        PlayListAdapter pla = new PlayListAdapter(this, playList);
        listView.setAdapter(pla);

    }

    public void viewInitialize() {
        listView = (ListView) findViewById(R.id.activity_playlist_listView);
    }

    public PlayList getPlayList(String filename) {
        PlayListManager plm = new PlayListManager(this);
        PlayList playList = plm.getPlayList(filename);
        return playList;
    }

}
