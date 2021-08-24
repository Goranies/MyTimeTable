package com.kbd.projectrepository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;

public class LobbyActivity extends AppCompatActivity {

    public TextView MonthTextView;   //요일 View들
    public TextView TUESTextView;
    public TextView WEDNESTextView;
    public  TextView THURSTextView;
    public TextView FRITextView;

    public  TableRow TableRowS09;     //시간 View들
    public  TableRow TableRowS10;
    public  TableRow TableRowS11;
    public  TableRow TableRowS12;
    public  TableRow TableRowS13;
    public  TableRow TableRowS14;
    public  TableRow TableRowS15;
    public  TableRow TableRowS16;


    public static ArrayList<Button> LobbyButtonList = new ArrayList();
    public static  ConstraintLayout layout;
    public static String MainTableName = "Table1";
    public addTimeFragment fragment;
    public  FragmentManager fragmentManager;
    public Menu Mymenu;

    public  LobbyDatabaseHelper LobbyDatabaseHelper = new LobbyDatabaseHelper(this);
    public  LobbyButtonDatabaseHelper LobbyButtonDatabaseHelper;
    public  SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);    // 콘텐트 뷰 그리기

        //시간표 틀 생성
        MonthTextView = findViewById(R.id.LobbyTextViewMon);
        TUESTextView = findViewById(R.id.LobbyTextViewTues);
        WEDNESTextView = findViewById(R.id.LobbyTextViewWednes);
        THURSTextView = findViewById(R.id.LobbyTextViewThurs);
        FRITextView = findViewById(R.id.LobbyTextViewFri);

        TableRowS09 = findViewById((R.id.LobbyTableRow09));
        TableRowS10 = findViewById((R.id.LobbyTableRow10));
        TableRowS11 = findViewById((R.id.LobbyTableRow11));
        TableRowS12 = findViewById((R.id.LobbyTableRow12));
        TableRowS13 = findViewById((R.id.LobbyTableRow13));
        TableRowS14 = findViewById((R.id.LobbyTableRow14));
        TableRowS15 = findViewById((R.id.LobbyTableRow15));
        TableRowS16 = findViewById((R.id.LobbyTableRow16));
        layout = findViewById((R.id.LobbyConstraintLayout));


        //저장된 시간표 버튼데이터베이스가 있는지 확인
        LobbyButtonDatabaseHelper = new LobbyButtonDatabaseHelper(this);
        db = LobbyButtonDatabaseHelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,"BTable");

        if(count !=0)       //저장된 버튼데이터베이스가 있었다면 그대로 버튼을 불러옴
        {
            LoadSavedButton();
        }


        //불러온 버튼을 타임테이블에 넣고 보여주기
        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ShowTimeTable();
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)               //옵션바 생성
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.lobbybar,menu);
        Mymenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)    //옵션바 설정
    {
        if(item.getItemId()==R.id.lobby_mitem_GotoWizard)
        {
            Intent intent = new Intent(this,WizardActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.lobby_mitem_GotoInput)
        {
            fragmentManager = getSupportFragmentManager();

        if(fragment == null)
            {

                fragment = new addTimeFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.LobbyConstraintLayout, fragment);
                fragmentTransaction.commit();

                MenuItem mi;
                mi = Mymenu.findItem(R.id.lobby_mitem_Test1);
                mi.setVisible(false);
                mi = Mymenu.findItem(R.id.lobby_mitem_Test2);
                mi.setVisible(false);
                mi =Mymenu.findItem(R.id.lobby_mitem_GotoWizard);
                mi.setVisible(false);
                mi = Mymenu.findItem(R.id.lobby_mitem_add);
                mi.setVisible(true);
            }
            else
                {

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment);
                fragmentTransaction.commit();
                fragment = null;

            MenuItem mi;
            mi = Mymenu.findItem(R.id.lobby_mitem_Test1);
            mi.setVisible(true);
            mi = Mymenu.findItem(R.id.lobby_mitem_Test2);
            mi.setVisible(true);
            mi =Mymenu.findItem(R.id.lobby_mitem_GotoWizard);
            mi.setVisible(true);
            mi = Mymenu.findItem(R.id.lobby_mitem_add);
            mi.setVisible(false);

                }

        }
        else if(item.getItemId()==R.id.lobby_mitem_Test1)
        {
            ChangeTimeTable1();
        }
        else if(item.getItemId()==R.id.lobby_mitem_Test2)
        {
            ChangeTimeTable2();
        }
        else if(item.getItemId()==R.id.lobby_mitem_add)
        {
            String tclass = addTimeFragment.name_class.getText().toString();
            String tclassroom = addTimeFragment.name_classroom.getText().toString();
            String tprofessor = addTimeFragment.name_professor.getText().toString();
            String ttime="";
            for(int i=0;i<addTimeFragment.timechoosefragment_list.size();i++)
            {

                if(i>0){ttime += "/";}
               String Week = addTimeFragment.timechoosefragment_list.get(i).Week.getText().toString();
               String StartTime = addTimeFragment.timechoosefragment_list.get(i).StartTime.getText().toString();
               String EndTime = addTimeFragment.timechoosefragment_list.get(i).EndTime.getText().toString();

               StartTime = StartTime.replace(":","");
               EndTime = EndTime.replace(":","");
               ttime += Week;
               ttime += "_" +  StartTime;
               ttime +="_" +  EndTime;
            }

            //버튼 리스트 초기화,레이아웃에서 뷰 삭제
            for(int i=0;i<LobbyActivity.LobbyButtonList.size();i++) {
                LobbyActivity.layout.removeView(LobbyActivity.LobbyButtonList.get(i));
            }
            LobbyActivity.LobbyButtonList.clear();

            LobbyDatabaseHelper LobbyDatabaseHelper = new LobbyDatabaseHelper(LobbyActivity.layout.getContext());
            SQLiteDatabase db = LobbyDatabaseHelper.getWritableDatabase();

            String sql = "insert into "+MainTableName+" (Professor,Class,Classroom,Time) values (?,?,?,?)";
            String[] args = {tprofessor,tclass,tclassroom,ttime};
            db.execSQL(sql, args);
            addTimeButton(MainTableName);
            ShowTimeTable();


            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
            fragment = null;

            MenuItem mi;
            mi =Mymenu.findItem(R.id.lobby_mitem_Test1);
            mi.setVisible(true);
            mi =Mymenu.findItem(R.id.lobby_mitem_Test2);
            mi.setVisible(true);
            mi =Mymenu.findItem(R.id.lobby_mitem_GotoWizard);
            mi.setVisible(true);
            mi =Mymenu.findItem(R.id.lobby_mitem_add);
            mi.setVisible(false);
        }
        return true;
    }

    //생성한 시간표를 버튼리스트에 넣기
    public void addTimeButton(String Table)
    {

        LobbyDatabaseHelper = new LobbyDatabaseHelper(this);
        db = LobbyDatabaseHelper.getWritableDatabase();

        String sql = "select * from "+Table;

        Cursor c = db.rawQuery(sql,null);

        while(c.moveToNext()) {

            int Professor_pos = c.getColumnIndex("Professor");
            int Class_pos = c.getColumnIndex("Class");
            int Classroom_pos = c.getColumnIndex("Classroom");
            int Time_pos = c.getColumnIndex("Time");

            String Tprofessor = c.getString(Professor_pos);
            String Tclass = c.getString(Class_pos);
            String Tclassroom = c.getString(Classroom_pos);
            String TTime = c.getString(Time_pos);
            String TimeList[] = TTime.split("/");
            for(int i=0;i<TimeList.length;i++) {

                Button b = new Button(this);
                int t_width =0, t_height=0;

                String tempTime[] = TimeList[i].split("_");

                for(int j=0;j<tempTime.length;j++) {
                    if (tempTime[0].equals("월")) {
                        t_width = MonthTextView.getWidth();
                        b.setX(MonthTextView.getX());
                    }       //요일
                    else if (tempTime[0].equals("화")) {
                        t_width = (TUESTextView.getWidth());
                        b.setX(TUESTextView.getX());
                    } else if (tempTime[0].equals("수")) {
                        t_width = WEDNESTextView.getWidth();
                        b.setX(WEDNESTextView.getX());
                    } else if (tempTime[0].equals("목")) {
                        t_width = (THURSTextView.getWidth());
                        b.setX(THURSTextView.getX());
                    } else {
                        t_width = FRITextView.getWidth();
                        b.setX(FRITextView.getX());
                    }
                }


                    float StartHour = Float.parseFloat(tempTime[1].substring(0,2));
                    float StartMinute = Float.parseFloat(tempTime[1].substring(2,4));
                    float EndHour = Float.parseFloat(tempTime[2].substring(0,2));
                    float EndMinute = Float.parseFloat(tempTime[2].substring(2,4));


                    if (StartHour == 9) {
                        t_height = (int) (TableRowS09.getHeight() * ((EndHour-StartHour)+(EndMinute-StartMinute)/60));
                        b.setY(TableRowS09.getY()+TableRowS09.getHeight()*(StartMinute/60));
                    }       //시간
                    else if (StartHour == 10) {
                        t_height = (int) (TableRowS10.getHeight() * ((EndHour-StartHour)+(EndMinute-StartMinute)/60));
                        b.setY(TableRowS10.getY()+TableRowS10.getHeight()*(StartMinute/60));
                    } else if (StartHour == 11) {
                        t_height = (int) (TableRowS11.getHeight() * ((EndHour-StartHour)+(EndMinute-StartMinute)/60));
                        b.setY(TableRowS11.getY()+TableRowS11.getHeight()*(StartMinute/60));
                    } else if (StartHour == 12) {
                        t_height = (int) (TableRowS12.getHeight() * ((EndHour-StartHour)+(EndMinute-StartMinute)/60));
                        b.setY(TableRowS12.getY()+TableRowS12.getHeight()*(StartMinute/60));
                    } else if (StartHour == 13) {
                        t_height = (int) (TableRowS13.getHeight() * ((EndHour-StartHour)+(EndMinute-StartMinute)/60));
                        b.setY(TableRowS13.getY()+TableRowS13.getHeight()*(StartMinute/60));
                    } else if (StartHour == 14) {
                        t_height = (int) (TableRowS14.getHeight() * ((EndHour-StartHour)+(EndMinute-StartMinute)/60));
                        b.setY(TableRowS14.getY()+TableRowS14.getHeight()*(StartMinute/60));
                    } else if (StartHour == 15) {
                        t_height = (int) (TableRowS15.getHeight() * ((EndHour-StartHour)+(EndMinute-StartMinute)/60));
                        b.setY(TableRowS15.getY()+TableRowS15.getHeight()*(StartMinute/60));
                    } else {
                        t_height = (int) (TableRowS16.getHeight() * ((EndHour-StartHour)+(EndMinute-StartMinute)/60));
                        b.setY(TableRowS16.getY()+TableRowS16.getHeight()*(StartMinute/60));
                    }
                    b.setLayoutParams(new ConstraintLayout.LayoutParams(t_width, t_height));
                    b.setTextSize(11);
                    b.setTextColor(Color.argb(255, 255, 255, 255));
                    b.setText(Tclass + "\n" + Tprofessor + "\n" + Tclassroom);
                    b.setStateListAnimator(null);
                    int bgcolor;
                    if(i>0)
                    {
                        ColorDrawable draw = (ColorDrawable) LobbyButtonList.get(LobbyButtonList.size()-1).getBackground();
                        bgcolor = draw.getColor();
                    }
                    else {
                        Random random = new Random();
                        bgcolor = Color.argb(255,random.nextInt(75)+125,random.nextInt(75)+125,random.nextInt(75)+125);
                    }
                    b.setBackgroundColor(bgcolor);
                    LobbyButtonList.add(b);

                    LobbyButtonDatabaseHelper = new LobbyButtonDatabaseHelper(this);
                    db = LobbyButtonDatabaseHelper.getWritableDatabase();

                    sql = "insert into BTable (Width,Height,Rcolor,Gcolor,Bcolor,X,Y,Text) values (?,?,?,?,?,?,?,?)";

                    String[] args = {Integer.toString(t_width), Integer.toString(t_height), Integer.toString(Color.red(bgcolor))
                            , Integer.toString(Color.green(bgcolor)), Integer.toString(Color.blue(bgcolor)), Float.toString(b.getX())
                            , Float.toString(b.getY()), b.getText().toString()};
                    db.execSQL(sql, args);
                }
            }
        }


    public void LoadSavedButton()   //저장된 버튼 불러오기
    {
        LobbyButtonDatabaseHelper = new LobbyButtonDatabaseHelper(this);
        db = LobbyButtonDatabaseHelper.getWritableDatabase();

        String sql = "select * from BTable";

        Cursor c = db.rawQuery(sql,null);

        while(c.moveToNext()) {
            int Width_pos = c.getColumnIndex("Width");
            int Height_pos = c.getColumnIndex("Height");
            int Rcolor_pos = c.getColumnIndex("Rcolor");
            int Gcolor_pos = c.getColumnIndex("Gcolor");
            int Bcolor_pos = c.getColumnIndex("Bcolor");
            int X_pos = c.getColumnIndex("X");
            int Y_pos = c.getColumnIndex("Y");
            int Text_pos = c.getColumnIndex("Text");

            int TWidth = c.getInt(Width_pos);
            int THeight = c.getInt(Height_pos);
            int TRcolor = c.getInt(Rcolor_pos);
            int TGcolor = c.getInt(Gcolor_pos);
            int TBcolor = c.getInt(Bcolor_pos);
            float TX = c.getFloat(X_pos);
            float TY = c.getFloat(Y_pos);
            String TText = c.getString(Text_pos);

            Button b = new Button(this);

            b.setLayoutParams(new ConstraintLayout.LayoutParams(TWidth, THeight));
            b.setX(TX);
            b.setY(TY);
            b.setTextSize(11);
            b.setTextColor(Color.argb(255, 255, 255, 255));
            b.setText(TText);
            b.setBackgroundColor(Color.argb(255, TRcolor, TGcolor, TBcolor));
            b.setStateListAnimator(null);
            LobbyButtonList.add(b);
        }
    }

    public void ChangeTimeTable1()
    {
        MainTableName = "Table1";
        //버튼 리스트 초기화,레이아웃에서 뷰 삭제
        for(int i=0;i<LobbyButtonList.size();i++) {
            layout.removeView(LobbyButtonList.get(i));
        }
        LobbyButtonList.clear();

        //새로운 시간표 받기위해 현재의 버튼테이블 초기화
        LobbyButtonDatabaseHelper = new LobbyButtonDatabaseHelper(this);
        db = LobbyButtonDatabaseHelper.getWritableDatabase();
        db.execSQL("delete from BTable");

        LobbyDatabaseHelper = new LobbyDatabaseHelper(this);
        db = LobbyDatabaseHelper.getWritableDatabase();

        addTimeButton("Table1");
        ShowTimeTable();
        System.out.println(LobbyButtonList.size());
    }
    public void ChangeTimeTable2()
    {
        MainTableName = "Table2";
        //버튼 리스트 초기화,레이아웃에서 뷰 삭제
        for(int i=0;i<LobbyButtonList.size();i++) {
            layout.removeView(LobbyButtonList.get(i));
        }
        LobbyButtonList.clear();

        //새로운 시간표 받기위해 현재의 버튼테이블 초기화
        LobbyButtonDatabaseHelper = new LobbyButtonDatabaseHelper(this);
        db = LobbyButtonDatabaseHelper.getWritableDatabase();
        db.execSQL("delete from BTable");

        LobbyDatabaseHelper = new LobbyDatabaseHelper(this);
        db = LobbyDatabaseHelper.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,"Table2");

        addTimeButton("Table2");
        ShowTimeTable();

    }

    public void ShowTimeTable() //시간표 모양 보이게 하는 매소드
    {
        for(int i=0;i<LobbyButtonList.size();i++) {
            if(LobbyButtonList.get(i).getParent()!=null)
            {
                ((ViewGroup)LobbyButtonList.get(i).getParent()).removeView(LobbyButtonList.get(i));
            }
            layout.addView(LobbyButtonList.get(i));
        }
    }
}
