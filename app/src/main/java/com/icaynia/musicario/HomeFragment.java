package com.icaynia.musicario;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class HomeFragment {
    private Context context;
    public View v;
    private String TAG = "HomeFragment";
    private LinearLayout contentBox;
    private ViewNowlistening item_nowlistening;

    public HomeFragment(Context _context, View _v) {
        context = _context;
        v = _v;
        Log.i(TAG, "HomeFragment is created.");

        viewInitialize();
        addNowlistening();
    }

    public void viewInitialize() {
        contentBox = (LinearLayout) v.findViewById(R.id.fragment_home);
        item_nowlistening = new ViewNowlistening(context.getApplicationContext());
    }

    public void onMusicPlayerActivity() {
        Intent intent = new Intent(this.context, MusicPlayerActivity.class);
        context.startActivity(intent);
    }

    public void addNowlistening() {
        contentBox.addView(item_nowlistening);
    }
}
