package com.icaynia.arimusic.DB;

/**
 * Created by icaynia on 16. 10. 5..
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    // 안드로이드에서 SQLite 데이터 베이스를 쉽게 사용할 수 있도록 도와주는 클래스
    public MySQLiteOpenHelper(Context context, String name,
                              CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 최초에 데이터베이스가 없을경우, 데이터베이스 생성을 위해 호출됨
        // 테이블 생성하는 코드를 작성한다

        String sql = "create table PlayLog(" +
                " no integer primary key autoincrement," +    // no
                " music_name text," +
                " music_artist text," +
                " music_album text," +
                " isUploaded integer," +  // 서버에 저장되었는가? = 1:true, 0:false
                " regdate date" +
                "  );";
        db.execSQL(sql);

        sql = "create table musicLibrary(" +
                " no integer primary key autoincrement," +    // no
                " music_name text," +
                " music_artist text," +
                " music_album text," +
                " isLike text," + // 좋아하는가? = 1:true, 0:false
                " isUploaded integer," +  // 서버에 저장되었는가? = 1:true, 0:false
                " regdate date" +
                "  );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1 :
        }
    }
}