package com.icaynia.arimusic;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 21..
 */
public class PlayList {
    public String listName = "";
    public ArrayList<MusicDto> list;

    private Context context;
    private ObjectFileManager ofm;

    public PlayList (Context _context) {
        this.context = _context;
        ofm = new ObjectFileManager(context);

        list = new ArrayList<>();
        MusicDto ms = new MusicDto();
        ms.title="22";
        list.add(ms);
    }

    public void load(String filename) {
        list = ofm.load(filename);
    }

    public void save(String filename) {
        ofm.save(list, filename);
        ofm.getFileList();
        Log.e("WW", "W");
    }

}
