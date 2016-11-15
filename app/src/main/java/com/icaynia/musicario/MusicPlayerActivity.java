package com.icaynia.musicario;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class MusicPlayerActivity extends AppCompatActivity {
    private LinearLayout titleBox;
    private LinearLayout albumBox;
    private LinearLayout controllerView;
    private ImageView albumImageView;
    private TextView titleText;
    private TextView artistText;
    private Global global;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);

        viewInitialize();
    }

    public void viewInitialize() {
        titleBox = (LinearLayout) findViewById(R.id.musicplayer_titleBox);
        titleText = (TextView) findViewById(R.id.musicplayer_titleText);
        artistText = (TextView) findViewById(R.id.musicplayer_artistText);

        albumBox = (LinearLayout) findViewById(R.id.musicplayer_albumBox);
        albumImageView = (ImageView) findViewById(R.id.musicplayer_albumImage);

        controllerView = (LinearLayout) findViewById(R.id.musicplayer_controller);

        global = (Global) getApplication();
        updateSongInfo();
    }

    public void updateSongInfo() {
        if (global.nowPlaying != null) {
            titleText.setText(global.nowPlaying.title);
            artistText.setText(global.nowPlaying.artist);
        }

    }
}
