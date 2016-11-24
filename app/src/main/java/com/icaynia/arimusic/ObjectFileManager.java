package com.icaynia.arimusic;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by icaynia on 2016. 11. 21..
 */
public class ObjectFileManager {
    private Context context;
    private String rootSD;

    public ObjectFileManager(Context _context) {
        this.context = _context;
        rootSD = Environment.getExternalStorageDirectory().toString();
    }

    public PlayList load(String filename) {
        try
        {
            FileInputStream fis = new FileInputStream (new File(rootSD + "/arimusic/" + filename));
            ObjectInputStream ois = new ObjectInputStream(fis);

            PlayList data = (PlayList)ois.readObject();

            ois.close();
            fis.close();

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(PlayList data, String filename) {

        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        String r = rootSD +"/arimusic/"+filename;
        try
        {
            oos = new ObjectOutputStream(new FileOutputStream(rootSD +"/arimusic/"+filename));
            oos.writeObject(data);
            oos.close();
            Log.e("Ww", "save completed  : "+r);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    /* function that execute when there is no argument */
    public ArrayList<PlayList> getPlayList() {
        ArrayList<PlayList> al = new ArrayList<PlayList>();
        File file = new File( rootSD + "/arimusic" ) ;
        if( !file.exists() ) {
            file.mkdirs();
        }
        File list[] = file.listFiles();
        for( int i=0; i<list.length; i++)
        {
            Log.e("ofm", list[i].getName()+"" );
            al.add(this.load(list[i].getName()+""));
        }

        return al;
    }

}
