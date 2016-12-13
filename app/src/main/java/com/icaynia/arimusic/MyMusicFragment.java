package com.icaynia.arimusic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.icaynia.arimusic.Model.MusicDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icaynia on 2016. 11. 14..
 */
public class MyMusicFragment {
    private Context context;
    private View v;
    private String TAG = "MyMusicFragment";
    private ListView listView;
    private Global global;


    public MyMusicFragment(Context _context, View _v) {
        this.context = _context;
        this.v = _v;
        Log.i(TAG, "MyMusicFragment is created.");
        viewInitialize();
    }

    private void viewInitialize() {
        global = ((TabbedActivity)context).global;
        listView = (ListView) v.findViewById(R.id.fragment_musiclist_listView);

        MyMusicAdapter adapter = new MyMusicAdapter(((TabbedActivity)context), global.mediaList);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                global.playMusic(position);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.view_musiclist_row_title);
                int ids = Integer.parseInt(title.getTag().toString());

                // menu
                List<String> liste = new ArrayList<String>();
                liste.add("재생목록에 추가");
                liste.add("다음 재생에 추가");
                onLongClick(liste, global.mediaList.get(position));
                return false;
            }
        });
    }

    public void onLongClick(final List<String> listItems, final MusicDto music) {
        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);
        listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                listItems.get(item).toString();

                switch (item) {
                    case 0:
                        Log.e("frg", "add to playlist");
                        final PlayListPickerDialog plpd = new PlayListPickerDialog(context);
                        plpd.setPlayListPickEventListener(new PlayListPickEventListener() {
                            @Override
                            public void onPlayListPickEvent(PlayList playList) {
                                Log.e("rr", "click : "+playList.getName() );
                                PlayListManager plm = new PlayListManager(context);
                                playList.add(music);
                                plm.savePlayList(playList);

                            }
                        });
                        plpd.show();

                        break;
                    case 1:

                        break;
                }
            }

        });

        //buider.setCancelable(false);  // 뒤로 가기 버튼 사용 금지.
        AlertDialog alert = builder.create();
        alert.show();
    }


}
