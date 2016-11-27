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

/**
 * Created by icaynia on 2016. 11. 26..
 */
public class PlayListPickerListAdapter extends BaseAdapter {

    private ArrayList<PlayList> list;
    private Activity activity;
    private LayoutInflater inflater;


    public PlayListPickerListAdapter ()  {

    }

    public PlayListPickerListAdapter (Context context, ArrayList<PlayList> list) {
        this.list = list;
        this.activity = activity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            convertView = inflater.inflate(R.layout.view_playlistpickerdialog_row, parent, false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            convertView.setLayoutParams(layoutParams);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.view_playlistpickerdialog_row_title);
        textView.setText(list.get(position).getName());

        /* for tag */
        textView.setTag(list.get(position).getFilename());

        return convertView;
    }

}
