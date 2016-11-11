package com.icaynia.musicario;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class Music extends LinearLayout {

    public Music(Context context) {
        super(context);
        viewInitialize();
    }

    public Music(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewInitialize();
    }

    public Music(Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs);
        viewInitialize();

    }

    private void viewInitialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.view_music, this, false);
        addView(v);


    }


}
