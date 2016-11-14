package com.icaynia.musicario;

import android.app.Application;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
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

    /* Important variables */
    public MusicBar musicBar;

    /* music play */
    public MediaPlayer mediaPlayer = new MediaPlayer();

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

    public void playMusic(MusicDto musicDto) {
        try {
            //seekBar.setProgress(0);
            //title.setText(musicDto.getArtist()+" - "+musicDto.getTitle());
            Uri musicURI = Uri.withAppendedPath(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, ""+musicDto.id);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, musicURI);
            mediaPlayer.prepare();
            mediaPlayer.start();
            //seekBar.setMax(mediaPlayer.getDuration());
            /*
            if(mediaPlayer.isPlaying()){
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
            }else{
                play.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);
            }
            */


            //Bitmap bitmap = BitmapFactory.decodeFile(getCoverArtPath(Long.parseLong(musicDto.getAlbumId()),getApplication()));
            //album.setImageBitmap(bitmap);

        }
        catch (Exception e) {
            Log.e("SimplePlayer", e.getMessage());
        }
    }

    public void stopMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

}
