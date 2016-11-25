package com.icaynia.arimusic;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class Global extends Application {
    // 전반적인 기능들은 여기서 실행됩니다.
    // 全般的な機能はここでします。
    // Most function executes here.

    // The following variable must be the public variable.
    // 次の変数は公共変数になければなりません。
    // 다음 변수들은 공용 변수들이 되어야 합니다.

    //service
    public musicService musicSrv;
    private Intent playIntent;

    /* Important variables */
    public MusicBar musicBar;

    /* music play */
    public MusicDto nowPlaying = null;
    public int position;

    /* music list */
    public PlayList nowList = new PlayList();
    public ArrayList<MusicDto> mediaList;

    public void playMusic(int id) {
        this.position = id;
        this.nowPlaying = this.mediaList.get(id);
        this.musicBar.updatePlayingInfo();
        this.musicBar.setPlay();
        this.musicSrv.playMusic();
    }

    public void playMusicRand() {
        Random random = new Random();
        int randInt = random.nextInt(this.mediaList.size());

        this.playMusic(randInt);

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
