package com.icaynia.musicario;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by icaynia on 2016. 11. 17..
 */
public class ViewTodaylistened extends LinearLayout {
    public Global global;


    public ViewTodaylistened(Context _context) {
        super(_context);
        initialize();
    }

    public ViewTodaylistened(Context _context, AttributeSet attrs) {
        super(_context, attrs);
        initialize();
    }

    public void initialize() {
        viewInitialize();
    }

    public void viewInitialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.view_main_todaylistened, this, false);
        addView(v);

        global = (Global) getContext().getApplicationContext();

    }
}
