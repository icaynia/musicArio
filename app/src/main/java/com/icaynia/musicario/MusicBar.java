package com.icaynia.musicario;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class MusicBar extends LinearLayout implements View.OnClickListener {

    private LinearLayout albumView;
    private LinearLayout titleView;
    private LinearLayout backButton;
    private LinearLayout playButton;
    private LinearLayout pauseButton;
    private LinearLayout nextButton;
    private Global global;

    private TextView title;

    public MusicBar(Context context) {
        super(context);
        viewInitialize();
    }

    public MusicBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewInitialize();
    }

    public MusicBar(Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs);
        viewInitialize();

    }

    private void viewInitialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.view_musicbar, this, false);
        addView(v);

        albumView = (LinearLayout) findViewById(R.id.musicbar_album_view);
        albumView.setOnClickListener(this);
        title = (TextView) findViewById(R.id.musicbar_name_view);
        title.setOnClickListener(this);
        backButton = (LinearLayout) findViewById(R.id.musicbar_backbutton);
        backButton.setOnClickListener(this);
        playButton = (LinearLayout) findViewById(R.id.musicbar_playbutton);
        playButton.setOnClickListener(this);
        pauseButton = (LinearLayout) findViewById(R.id.musicbar_pausebutton);
        pauseButton.setOnClickListener(this);
        nextButton = (LinearLayout) findViewById(R.id.musicbar_nextbutton);
        nextButton.setOnClickListener(this);

        global = (Global) getContext().getApplicationContext();

        updatePlayingInfo();
    }

    private void onMusicPlayerActivity() {
        Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
        getContext().startActivity(intent);
    }

    public void updatePlayingInfo() {
        if (global.nowPlaying == null) {
            title.setText("No Music");
        } else {
            title.setText(global.nowPlaying.artist + " - " + global.nowPlaying.title);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.musicbar_album_view:
                onMusicPlayerActivity();
                break;
            case R.id.musicbar_pausebutton:
                v.setVisibility(View.GONE);
                playButton.setVisibility(View.VISIBLE);
                break;
            case R.id.musicbar_playbutton:
                v.setVisibility(View.GONE);
                pauseButton.setVisibility(View.VISIBLE);
                break;

        }
    }

}
