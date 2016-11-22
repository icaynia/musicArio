package com.icaynia.arimusic;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by icaynia on 2016. 11. 16..
 */
public class ViewNowlistening extends LinearLayout {
    public Global global;
    public ImageView albumImage;
    public TextView musicNameView;
    public TextView artistNameView;

    public ViewNowlistening(Context _context) {
        super(_context);
        initialize();

    }

    public ViewNowlistening(Context _context, AttributeSet attrs) {
        super(_context, attrs);
        initialize();

    }

    public ViewNowlistening(Context _context, AttributeSet attrs, int defStyle) {

        super(_context, attrs);
        initialize();
    }


    public void initialize() {
        viewInitialize();
        if (getNowPlaying() != null) {
            albumImage.setImageBitmap(getAlbumImage(getContext(), Integer.parseInt(getNowPlaying().albumid), 300));
            musicNameView.setText(getNowPlaying().title);
            artistNameView.setText(getNowPlaying().artist);
        }
    }

    private void viewInitialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.view_main_nowlistening, this, false);
        addView(v);
        global = (Global) getContext().getApplicationContext();

        albumImage = (ImageView) findViewById(R.id.view_main_nowlistening_albumImageView);
        musicNameView = (TextView) findViewById(R.id.view_main_nowlistening_musicNameView);
        artistNameView = (TextView) findViewById(R.id.view_main_nowlistening_artistNameView);
    }

    public MusicDto getNowPlaying() {
        return global.nowPlaying;
    }

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


                // Compute the closest power-of-two scale factor
                // and pass that to sBitmapOptionsCache.inSampleSize, which will
                // result in faster decoding and better quality

                //크기를 얻어오기 위한옵션 ,
                //inJustDecodeBounds값이 true로 설정되면 decoder가 bitmap object에 대해 메모리를 할당하지 않고, 따라서 bitmap을 반환하지도 않는다.
                // 다만 options fields는 값이 채워지기 때문에 Load 하려는 이미지의 크기를 포함한 정보들을 얻어올 수 있다.
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




    private static final BitmapFactory.Options options = new BitmapFactory.Options();

}
