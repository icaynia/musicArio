package com.icaynia.arisong;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 21..
 */
public class PlayList {
    public ArrayList<MusicDto> list;

    private Context context;
    private ObjectFileManager ofm;

    public PlayList (Context _context) {
        this.context = _context;
        ofm = new ObjectFileManager(context);

    }

    public void load(String filename) {
        list = ofm.load(filename);
    }

    public void save(ArrayList<MusicDto> list, String filename) {
        ofm.save(list, filename);
    }
}
