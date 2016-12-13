package com.icaynia.arimusic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.icaynia.arimusic.View.NowListeningView;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class HomeFragment {
    private Context context;
    public View v;
    private String TAG = "HomeFragment";
    private LinearLayout contentBox;
    private NowListeningView item_nowlistening;
    private Global global;

    public HomeFragment(Context _context, View _v) {
        context = _context;
        v = _v;
        Log.i(TAG, "HomeFragment is created.");

        viewInitialize();
        if (global.nowPlaying != null) {
            addNowlistening();
        }
    }

    public void viewInitialize() {
        global = (Global) context.getApplicationContext();
        contentBox = (LinearLayout) v.findViewById(R.id.fragment_home);
        item_nowlistening = new NowListeningView(context.getApplicationContext());
    }

    public void onMusicPlayerActivity() {
        Intent intent = new Intent(this.context, MusicPlayerActivity.class);
        context.startActivity(intent);
    }

    public void addNowlistening() {
        contentBox.addView(item_nowlistening);
    }

    public void updateNowlistening() {
        item_nowlistening.initialize();
    }

}
