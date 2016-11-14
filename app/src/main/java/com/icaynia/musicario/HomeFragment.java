package com.icaynia.musicario;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class HomeFragment {
    private Context context;
    public View v;



    private String TAG = "HomeFragment";


    public HomeFragment(Context _context, View _v) {
        context = _context;
        v = _v;
        Log.i(TAG, "HomeFragment is created.");

        viewInitialize();
    }

    public void viewInitialize() {
        
    }

    public void onMusicPlayerActivity() {
        Intent intent = new Intent(this.context, MusicPlayerActivity.class);
        context.startActivity(intent);
    }
}
