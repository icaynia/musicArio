package com.icaynia.arimusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by icaynia on 2016. 11. 22..
 */
public class PlayListActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Intent intent = getIntent();

        long id = intent.getIntExtra("listid", 0);

        Log.e("playlist", id+"");
    }

    public PlayList getPlayList(String filename) {
        PlayList playList = new PlayList();
        PlayListManager plm = new PlayListManager(this);
        playList = getPlayList(filename);
        return playList;
    }

}
