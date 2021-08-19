package com.kbd.projectrepository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class LobbyButtonDatabaseHelper extends SQLiteOpenHelper {


    public LobbyButtonDatabaseHelper(@Nullable Context context) {
        super(context, "lobbyb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "데이터베이스가 생성되었습니다");

        String sql = "create table BTable("
                + "ID integer primary key autoincrement, "
                + "Width integer not null,"
                + "Height integer not null,"
                + "Rcolor integer not null,"
                + "Gcolor integer not null,"
                + "Bcolor integer not null,"
                + "X real not null,"
                + "Y real not null,"
                + "Text text not null"
                +")";
        db.execSQL(sql);
    }

    //DB버전이 구식이라면, 업데이트 한다
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DB", "old : " + oldVersion);
        Log.d("DB", "new : " + newVersion);
        switch (oldVersion) {
            case 1 :
                //1에서 최신 버전 형태로 테이블 구조를 변경
            case 2 :
                //2에서 최신 버전 형태로
            case 3 :
                //3에서 최신 버전 형태로
        }

        //switch를 쓰고, break를 쓰지 않는 이유?
        //1->2 / 2->3 / 3->4 순서대로 올릴 수 있음
    }
}
