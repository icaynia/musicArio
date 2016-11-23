package com.icaynia.arimusic;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 22..
 */
public class PlayListManager {

    private Context context;
    private ObjectFileManager ofm;

    public PlayListManager(Context _context) {
        this.context = _context;
        initialize();
    }

    public void initialize() {
        ofm = new ObjectFileManager(context);
    }

    public PlayList getPlayList(String filename) {
        // load playlist from file.

        PlayList data = ofm.load(filename);
        return data;
    }

    public ArrayList<PlayList> getPlayListAll() {
        return ofm.getPlayList();
    }





}
