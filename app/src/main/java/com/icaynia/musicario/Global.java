package com.icaynia.musicario;

import android.app.Application;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class Global extends Application {
    // The following variable must be the public variable.
    // 次の変数は公共変数になければなりません。
    // 다음 변수들은 공용 변수들이 되어야 합니다.

    /* Important variables */
    public MusicBar musicBar;
    public MediaPlayer mediaPlayer;
    public ArrayList<MusicDto> mediaList;

    public void getMusicList(){
        mediaList = new ArrayList<>();
        //가져오고 싶은 컬럼 명을 나열합니다. 음악의 아이디, 앰블럼 아이디, 제목, 아스티스트 정보를 가져옵니다.
        String[] projection = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST
        };

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);

        while(cursor.moveToNext()){
            MusicDto musicDto = new MusicDto();
            musicDto.id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            musicDto.albumid = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            musicDto.title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            musicDto.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            mediaList.add(musicDto);
        }
        cursor.close();
    }

}
