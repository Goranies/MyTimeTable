package com.kbd.projectrepository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class WizardDatabaseHelper extends SQLiteOpenHelper {
    //wizard.db라는 이름으로 DB를 생성, DB버전은 1
    public WizardDatabaseHelper(@Nullable Context context) {
        super(context, "wizard.db", null, 1);
    }

    //Test.db가 있는지 확인해보고 이미 있으면 onCreate를 호출하지 않는다
    //없다면, Test.db라는 이름의 데이터베이스를 생성하고 onCreate를 호출한다
    //테이블 생성, 기타 필요한 작업
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "데이터베이스가 생성되었습니다");

        String sql1 = "create table GroupTable("
                + "ID integer primary key autoincrement, "
                + "Name text not null"
                +")";

        String sql2 = "create table TimeTable("
                + "ID integer primary key autoincrement, "
                + "Professor text not null,"
                + "Class text not null,"
                + "Classroom text not null,"
                + "Time text not null,"
                + "LinkGroup text not null"
                +")";

        db.execSQL(sql1);
        db.execSQL(sql2);
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
