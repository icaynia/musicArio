package com.icaynia.arimusic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 26..
 */
public class PlayListPickerDialog {
    Context context;
    private PlayListManager plm;
    private PlayListPickEventListener mListener;
    public PlayListPickerDialog(Context context) {
        this.context = context;
    }

    public void show() {
        plm = new PlayListManager(context);

        final ArrayList<PlayList> playLists = plm.getPlayListAll();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogV = inflater.inflate(R.layout.dialog_playlistpicker, null);


        ListView playListVu = (ListView) dialogV.findViewById(R.id.dialog_playlistpicker);
        playListVu.setAdapter(new PlayListPickerListAdapter(context, playLists));


        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final EditText nameV = (EditText) dialogV.findViewById(R.id.dialog_newplaylist_name);

        builder.setView(dialogV);
        builder.setTitle("Add PlayList");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameV.getText().toString();

                mListener.onPlayListPickEvent(playLists.get(which));
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT);

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        final AlertDialog alert = builder.create();
        alert.show();


    }

    public void setMyEventListener(PlayListPickEventListener listener) {
        mListener = listener;
    }
}
