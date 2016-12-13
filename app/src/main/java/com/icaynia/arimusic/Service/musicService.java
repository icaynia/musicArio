package com.icaynia.arimusic.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import com.icaynia.arimusic.Global;

/**
 * Created by icaynia on 2016. 11. 15..
 */
public class musicService extends Service {
    public MediaPlayer mediaPlayer = new MediaPlayer();
    public boolean playing = false;
    public int position;

    //binder
    private final IBinder musicBind = new MusicBinder();

    private Global global;

    Runnable task = new Runnable() {
        public void run(){
            try {
                Uri musicURI = Uri.withAppendedPath(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, global.nowPlaying.id);
                mediaPlayer.reset();
                mediaPlayer.setDataSource(getApplicationContext(), musicURI);
                mediaPlayer.prepare();
                mediaPlayer.start();


                //global.musicBar.updatePlayingInfo();

            }
            catch (Exception e) {
                Log.e("SimplePlayer", e.getMessage());
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        global = (Global)getApplication();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // 노래가 끝나면
                // TODO Auto-generated method stub
                if (global.position+1 < global.mediaList.size()) {
                    global.playMusic(global.position+1);
                } else {
                    stopSelf();
                }

            }
        });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("test", "onStartCommand");
        position = intent.getIntExtra("position", 0);

        return super.onStartCommand(intent, flags, startId);
    }

    public void logTest() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.stop();
        mediaPlayer.release();
        return false;
}

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //binder
    public class MusicBinder extends Binder {
        musicService getService() {
            return musicService.this;
        }
    }

    public void playMusic() {
        new Thread(task).start();
        playing = true;
    }


    public void start() {
        mediaPlayer.start();
        playing = true;
    }

    public void pause() {
        mediaPlayer.pause();
        playing = false;
    }

    public boolean isPlaying() {
        return playing;
    }
}
