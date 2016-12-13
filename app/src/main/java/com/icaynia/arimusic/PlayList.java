package com.icaynia.arimusic;


import com.icaynia.arimusic.Model.MusicDto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 21..
 */
public class PlayList implements Serializable {
    private int index;
    private String filename;
    private String name = "";
    private String listExplanation;
    private ArrayList<MusicDto> list = new ArrayList<MusicDto>();

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

    public void setIndex(int index) {
        this.index = index;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(ArrayList<MusicDto> list) {
        this.list = list;
    }

    public void setListExplanation(String listExplanation) {
        this.listExplanation = listExplanation;
    }

}
