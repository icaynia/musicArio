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
public class MusicBar extends LinearLayout {

    private LinearLayout albumView;
    private LinearLayout titleView;
    private LinearLayout backButton;
    private LinearLayout playButton;
    private LinearLayout nextButton;

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
        title = (TextView) findViewById(R.id.musicbar_name_view);
        backButton = (LinearLayout) findViewById(R.id.musicbar_backbutton);
        playButton = (LinearLayout) findViewById(R.id.musicbar_playbutton);
        nextButton = (LinearLayout) findViewById(R.id.musicbar_nextbutton);

        albumView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onMusicPlayerActivity();
            }
        });
    }

    private void onMusicPlayerActivity() {
        Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
        getContext().startActivity(intent);
    }


}