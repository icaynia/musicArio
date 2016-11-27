package com.icaynia.arimusic;

import android.content.Context;
import android.widget.Toast;

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

    public void savePlayList(PlayList data) {
        if (!ofm.isAvailable(data.getFilename())) {
            ofm.save(data, data.getFilename());
        } else {
            Toast.makeText(context, "This name already used", Toast.LENGTH_SHORT).show();
        }
    }

    public void deletePlayList(PlayList data) {
        ofm.delete(data.getFilename());
    }

    public void deletePlayList(String filename) {
        ofm.delete(filename);
    }


}
