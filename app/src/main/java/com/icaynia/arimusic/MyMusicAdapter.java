package com.icaynia.arimusic;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by icaynia on 2016. 11. 14..
 */
public class MyMusicAdapter extends BaseAdapter {
    List<MusicDto> list;
    Activity activity;
    LayoutInflater inflater;
    public ImageView album;

    public MyMusicAdapter() {

    }

    public MyMusicAdapter(Activity activity, List<MusicDto> list) {
        this.list = list;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_musiclist_row, parent, false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            convertView.setLayoutParams(layoutParams);
        }

        album = (ImageView) convertView.findViewById(R.id.view_musiclist_row_album);
        album.setImageDrawable(activity.getResources().getDrawable(android.R.drawable.ic_menu_report_image));
        TextView title = (TextView) convertView.findViewById(R.id.view_musiclist_row_title);
        TextView artist = (TextView) convertView.findViewById(R.id.view_musiclist_row_artist);

        title.setText(list.get(position).title);
        artist.setText(list.get(position).artist);

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.setImgView(album);
        myAsyncTask.execute(position+"");


        return convertView;


    }

    public class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private static final String TAG = "AlbumImageTask";
        private int position;
        private ImageView imgView;

        public void setImgView(ImageView _imgView) {
            this.imgView = _imgView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "PreExecute");
        }

        @Override
        protected Bitmap doInBackground(String... id) {
            Bitmap bitmap = null;
            for (String i : id) {
                this.position = Integer.parseInt(i);
                bitmap = getAlbumImage(activity, Integer.parseInt(list.get(position).albumid), 170);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            imgView.setImageBitmap(result);
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


    }

    private static final BitmapFactory.Options options = new BitmapFactory.Options();


}
