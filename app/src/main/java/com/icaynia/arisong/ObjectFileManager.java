package com.icaynia.arisong;

import android.content.Context;
import android.os.Environment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 21..
 */
public class ObjectFileManager {
    String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/arisong";
    private Context context;

    public ObjectFileManager(Context _context) {
        this.context = _context;
    }

    public ArrayList<MusicDto> load(String filename) {
        try
        {
            FileInputStream fis = context.openFileInput(sdPath+filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<MusicDto> data = (ArrayList<MusicDto>)ois.readObject();

            ois.close();

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(ArrayList<MusicDto> data, String filename) {
        if (data == null || data.isEmpty() == true) {
            return;
        }

        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        try
        {
            fos = context.openFileOutput(sdPath+filename ,context.MODE_PRIVATE);

            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

}
