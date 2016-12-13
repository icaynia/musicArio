package com.icaynia.arimusic.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.icaynia.arimusic.R;

/**
 * Created by icaynia on 2016. 12. 13..
 */

public class AppreciationActivityView extends LinearLayout {
    // 감상 활동
    public AppreciationActivityView(Context context) {
        super(context);
        initialize();
    }

    public AppreciationActivityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public AppreciationActivityView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initialize();
    }


    public void initialize() {
        viewInitialize();
    }

    public void viewInitialize() {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.view_appreciationactivity, this, false);
        addView(v);

    }

}
