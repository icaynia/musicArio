package com.icaynia.musicario;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by icaynia on 2016. 11. 14..
 */
public class MusicListAdapter extends BaseAdapter {
    List<MusicDto> list;
    Activity activity;
    LayoutInflater inflater;

    public MusicListAdapter() {

    }

    public MusicListAdapter(Activity activity, List<MusicDto> list) {
        this.list = list;
        this.activity = activity;
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


    }
}
