package com.icaynia.arisong;

import java.io.Serializable;

/**
 * Created by icaynia on 2016. 11. 14..
 */
public class MusicDto implements Serializable{
    public String id;
    public String albumid;
    public String title;
    public String artist;

    public MusicDto () {

    }

    public MusicDto (String id, String albumid, String title, String artist) {
        this.id = id;
        this.albumid = albumid;
        this.title = title;
        this.artist = artist;
    }


}
