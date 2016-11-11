package com.icaynia.musicario;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class MusicBar extends LinearLayout {

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


    }


}
