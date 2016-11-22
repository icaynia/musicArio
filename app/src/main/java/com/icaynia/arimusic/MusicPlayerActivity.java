package com.icaynia.arimusic;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by icaynia on 2016. 11. 12..
 */
public class MusicPlayerActivity extends AppCompatActivity {
    private LinearLayout titleBox;
    private LinearLayout albumBox;
    private LinearLayout controllerView;
    private ImageView albumImageView;
    private TextView titleText;
    private TextView artistText;
    private Global global;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);

        viewInitialize();
    }

    public void viewInitialize() {
        titleBox = (LinearLayout) findViewById(R.id.musicplayer_titleBox);
        titleText = (TextView) findViewById(R.id.musicplayer_titleText);
        artistText = (TextView) findViewById(R.id.musicplayer_artistText);

        albumBox = (LinearLayout) findViewById(R.id.musicplayer_albumBox);
        albumImageView = (ImageView) findViewById(R.id.musicplayer_albumImage);

        controllerView = (LinearLayout) findViewById(R.id.musicplayer_controller);

        global = (Global) getApplication();
        updateSongInfo();
    }

    public void updateSongInfo() {
        if (global.nowPlaying != null) {
            titleText.setText(global.nowPlaying.title);
            artistText.setText(global.nowPlaying.artist);
            Bitmap albumImage = getAlbumImage(this, Integer.parseInt(global.nowPlaying.albumid), 400);
            albumImageView.setImageBitmap(albumImage);
        }
    }
    private static final BitmapFactory.Options options = new BitmapFactory.Options();

    public Bitmap getAlbumImage(Context context, int album_id, int MAX_IMAGE_SIZE) {
        // NOTE: There is in fact a 1 pixel frame in the ImageView used to
        // display this drawable. Take it into account now, so we don't have to
        // scale later.
        ContentResolver res = context.getContentResolver();
        Uri uri = Uri.parse("content://media/external/audio/albumart/" + album_id);
        if (uri != null) {
            ParcelFileDescriptor fd = null;
            try {
                fd = res.openFileDescriptor(uri, "r");


                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(
                        fd.getFileDescriptor(), null, options);
                int scale = 0;
                if (options.outHeight > MAX_IMAGE_SIZE || options.outWidth > MAX_IMAGE_SIZE) {
                    scale = (int) Math.pow(2, (int) Math.round(Math.log(MAX_IMAGE_SIZE / (double) Math.max(options.outHeight, options.outWidth)) / Math.log(0.5)));
                }
                options.inJustDecodeBounds = false;
                options.inSampleSize = scale;



                Bitmap b = BitmapFactory.decodeFileDescriptor(
                        fd.getFileDescriptor(), null, options);

                if (b != null) {
                    // finally rescale to exactly the size we need
                    if (options.outWidth != MAX_IMAGE_SIZE || options.outHeight != MAX_IMAGE_SIZE) {
                        Bitmap tmp = Bitmap.createScaledBitmap(b, MAX_IMAGE_SIZE, MAX_IMAGE_SIZE, true);
                        b.recycle();
                        b = tmp;
                    }
                }

                return b;
            } catch (FileNotFoundException e) {
            } finally {
                try {
                    if (fd != null)
                        fd.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
}
