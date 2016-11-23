package com.icaynia.arimusic;


import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 21..
 */
public class PlayList {
    private int index;
    private String filename;
    private String name;
    private String listExplanation;
    private ArrayList<MusicDto> list;

    public PlayList() {

    }

    public int getIndex() {
        return this.index;
    }

    public String getFilename() {
        return this.filename;
    }

    public String getName() {
        return this.name;
    }

    public String getListExplanation() {
        return this.listExplanation;
    }

    public MusicDto get(int index) {
        return this.list.get(index);
    }

    public void add(MusicDto data) {
        this.list.add(data);
    }

    public int getSize() {
        return this.list.size();
    }



}
