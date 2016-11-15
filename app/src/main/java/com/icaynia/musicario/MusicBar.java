package com.icaynia.musicario;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
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
 * Created by icaynia on 2016. 11. 12..
 */
public class MusicBar extends LinearLayout implements View.OnClickListener {
    private Activity activity;

    private ImageView albumView;
    private LinearLayout titleView;
    private LinearLayout backButton;
    private LinearLayout playButton;
    private LinearLayout pauseButton;
    private LinearLayout nextButton;
    private Global global;

    private TextView title;

    public MusicBar(Context context) {
        super(context);
        viewInitialize();
    }

    public MusicBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewInitialize();
    }

    public MusicBar(Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs);
        viewInitialize();

    }

    public void setActivity(Activity _activity) {
        activity = _activity;
    }

    public void viewInitialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.view_musicbar, this, false);
        addView(v);

        albumView = (ImageView) findViewById(R.id.musicbar_album_view);
        albumView.setOnClickListener(this);
        title = (TextView) findViewById(R.id.musicbar_name_view);
        title.setOnClickListener(this);
        backButton = (LinearLayout) findViewById(R.id.musicbar_backbutton);
        backButton.setOnClickListener(this);
        playButton = (LinearLayout) findViewById(R.id.musicbar_playbutton);
        playButton.setOnClickListener(this);
        pauseButton = (LinearLayout) findViewById(R.id.musicbar_pausebutton);
        pauseButton.setOnClickListener(this);
        nextButton = (LinearLayout) findViewById(R.id.musicbar_nextbutton);
        nextButton.setOnClickListener(this);

        global = (Global) getContext().getApplicationContext();
    }

    private void onMusicPlayerActivity() {
        Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
        getContext().startActivity(intent);
    }

    public void updatePlayingInfo() {
        if (global.nowPlaying == null) {
            title.setText("No Music");
            setPause();
        } else {
            title.setText(global.nowPlaying.artist + " - " + global.nowPlaying.title);
            Bitmap bitmap = getAlbumImage(activity, Integer.parseInt(global.nowPlaying.albumid), 170);
            if (bitmap != null)
                albumView.setImageBitmap(bitmap);
            setPlay();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.musicbar_album_view:
                onMusicPlayerActivity();
                break;
            case R.id.musicbar_pausebutton:
                setPause();
                global.mediaPlayer.pause();
                break;
            case R.id.musicbar_playbutton:
                setPlay();
                global.mediaPlayer.start();
                break;

        }
    }

    public void setPlay() {
        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
    }

    public void setPause() {
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
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

}
