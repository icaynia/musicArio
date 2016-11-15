package com.icaynia.musicario;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class Global extends Application {
    // The following variable must be the public variable.
    // 次の変数は公共変数になければなりません。
    // 다음 변수들은 공용 변수들이 되어야 합니다.

    //service
    public musicService musicSrv;
    private Intent playIntent;



    /* Important variables */
    public MusicBar musicBar;


    /* music play */
    public MediaPlayer mediaPlayer = new MediaPlayer();
    public MusicDto nowPlaying = null;


    public ArrayList<MusicDto> mediaList;

    public void playMusic(int id) {
        this.nowPlaying = this.mediaList.get(id);
        this.musicBar.updatePlayingInfo();
        this.musicBar.setPlay();
        this.musicSrv.playMusic();


    }

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

    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService.MusicBinder binder = (musicService.MusicBinder)service;
            musicSrv = binder.getService();
            musicSrv.logTest();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    //액티비티 시작시 세팅 불러오기.
    public void initSetting() {
        //서비스 바인딩
        if (playIntent == null) {
            playIntent = new Intent(this, musicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }


}
