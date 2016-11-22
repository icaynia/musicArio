package com.icaynia.arimusic;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icaynia on 2016. 11. 22..
 */
public class PlayListAdapter extends BaseAdapter {
    ArrayList<PlayList> list;
    Activity activity;
    LayoutInflater inflater;

    /* View */

    public PlayListAdapter() {

    }

    public PlayListAdapter(Activity activity, ArrayList<PlayList> _list) {
        this.list = _list;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_playlist_row, parent, false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            convertView.setLayoutParams(layoutParams);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.view_playlist_row_titleView);
        textView.setText(list.get(position).listName.toString());

        return convertView;
    }

}
