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
import java.util.List;

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

    /* temp val */



    public PlayListFragment(Context _context, View _v) {
        this.context = _context;
        this.v = _v;
        Log.e(TAG, TAG + " is created");
        initialize();
    }

    public void viewInitialize() {
        nowListView = (ListView) v.findViewById(R.id.fragment_playlist_listView);
        newListTv = (TextView) v.findViewById(R.id.fragment_playlist_newlist);
        newListTv.setOnClickListener(this);
    }

    public void initialize() {
        viewInitialize();
        global = (Global) context.getApplicationContext();
        plm = new PlayListManager(context);

        /* listview initialize */
        updateListView();

        nowListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView listnm = (TextView) view.findViewById(R.id.view_playlistall_row_titleView);
                String filename = listnm.getTag().toString();

                Intent intent = new Intent(context, PlayListActivity.class);
                intent.putExtra("filename", filename);
                context.startActivity(intent);
            }
        });

        nowListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView listnm = (TextView) view.findViewById(R.id.view_playlistall_row_titleView);
                String filename = listnm.getTag().toString();

                // menu
                List<String> liste = new ArrayList<String>();
                liste.add("열기");
                liste.add("편집");
                liste.add("삭제");
                //liste.add("공유");
                onLongClick(liste, filename);
                return false;
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

                PlayList pl = new PlayList();
                pl.setFilename(name + ".plt");
                pl.setName(name);

                plm.savePlayList(pl);
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT);

                updateListView();

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

    public void onLongClick(final List<String> listItems, final String filename) {
        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);
        listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                listItems.get(item).toString();

                switch (item) {
                    case 0:
                        PlayListPickerDialog plpd = new PlayListPickerDialog(context);
                        
                        break;
                    case 1:

                        break;
                    case 2:
                        Log.e("frg", "delete");
                        plm.deletePlayList(filename);

                        updateListView();
                        break;
                }
            }

        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void updateListView() {
        pla = new PlayListAllAdapter((TabbedActivity)context, getPlayListAll());
        nowListView.setAdapter(pla);
    }


}
