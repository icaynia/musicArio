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
    String rootSD;
    Context context;

    public ObjectFileManager(Context _context) {
        this.context = _context;
        rootSD = Environment.getExternalStorageDirectory().toString();
    }

    public ArrayList<MusicDto> load(String filename) {
        try
        {
            FileInputStream fis = new FileInputStream (new File(rootSD + "/arimusic/" + filename));
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<MusicDto> data = (ArrayList<MusicDto>)ois.readObject();

            ois.close();
            fis.close();

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(ArrayList<MusicDto> data, String filename) {

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

    public ArrayList<PlayList> getFileList() {
        ArrayList<PlayList> al = new ArrayList<>();
        File file = new File( rootSD + "/arimusic" ) ;
        if( !file.exists() ) {
            file.mkdirs();
        }
        File list[] = file.listFiles();
        for( int i=0; i<list.length; i++)
        {
            Log.e("ofm", list[i].getName()+"" );
            PlayList pl = new PlayList(context);
            pl.listName = list[i].getName().toString();
            pl.load(list[i].getName());
            al.add(pl);

        }

        return al;
    }

}
