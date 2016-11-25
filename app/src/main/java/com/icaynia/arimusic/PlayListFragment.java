package com.icaynia.arimusic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 11. 20..
 */
public class PlayListFragment implements View.OnClickListener{

    public Context context;
    public View v;
    public Global global;

    private String TAG = "MyMusicFragment";

    /* view */
    private ListView nowListView;
    private TextView newListTv;

    /* back */
    private PlayListAllAdapter pla;
    private PlayListManager plm;

    public PlayListFragment(Context _context, View _v) {
        this.context = _context;
        this.v = _v;
        Log.e(TAG, TAG + " is created");
        initialize();
    }

    public void viewInitialize() {
        nowListView = (ListView) v.findViewById(R.id.fragment_playlist_listView);
        newListTv = (TextView) v.findViewById(R.id.fragment_playlist_newlist);
    }

    public void initialize() {
        viewInitialize();
        global = (Global) context.getApplicationContext();
        plm = new PlayListManager(context);

        /* listview initialize */
        pla = new PlayListAllAdapter((TabbedActivity)context, getPlayListAll());
        nowListView.setAdapter(pla);

        nowListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String filename = view.getTag(1).toString();
                Intent intent = new Intent(context, PlayListActivity.class);
                intent.putExtra("filename", filename);
                context.startActivity(intent);
            }
        });
    }

    public ArrayList<PlayList> getPlayListAll() {
        return plm.getPlayListAll();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fragment_playlist_newlist:
                onNewPlaylistDialog();
                break;
        }
    }

    public void onNewPlaylistDialog() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogV = inflater.inflate(R.layout.dialog_newplaylist, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final EditText nameV = (EditText) dialogV.findViewById(R.id.dialog_newplaylist_name);

        builder.setView(dialogV);
        builder.setTitle("Add PlayList");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameV.getText().toString();
                plm.savePlayList(new PlayList(), name+".plt");
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
}