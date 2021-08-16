package com.kbd.projectrepository;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

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


    ArrayList<Button> LobbyButtonList = new ArrayList();
    public  ConstraintLayout layout;
    private LobbyDatabaseHelper LobbyDatabaseHelper;
    private LobbyButtonDatabaseHelper LobbyButtonDatabaseHelper;
    private SQLiteDatabase db;


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
        getMenuInflater().inflate(R.menu.lobbybar,menu);
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
            Intent intent = new Intent(this,TimeInputActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.lobby_mitem_Test1)
        {
            ChangeTimeTable1();
        }
        else if(item.getItemId()==R.id.lobby_mitem_Test2)
        {
            ChangeTimeTable2();
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
            int StartTime_pos = c.getColumnIndex("StartTime");
            int EndTime_pos = c.getColumnIndex("EndTime");
            int Professor_pos = c.getColumnIndex("Professor");
            int Class_pos = c.getColumnIndex("Class");
            int Classroom_pos = c.getColumnIndex("Classroom");
            int Week_pos = c.getColumnIndex("Week");

            double TstartTime = c.getDouble(StartTime_pos);
            double TendTime = c.getDouble(EndTime_pos);
            String Tprofessor = c.getString(Professor_pos);
            String Tclass = c.getString(Class_pos);
            String Tclassroom = c.getString(Classroom_pos);
            int Tweek = c.getInt(Week_pos);

            Button b = new Button(this);
            int t_width, t_height;

            if (Tweek == 1) {
                t_width = MonthTextView.getWidth();
                b.setX(MonthTextView.getX());
            }       //요일
            else if (Tweek == 2) {
                t_width = (TUESTextView.getWidth());
                b.setX(TUESTextView.getX());
            } else if (Tweek == 3) {
                t_width = WEDNESTextView.getWidth();
                b.setX(WEDNESTextView.getX());
            } else if (Tweek == 4) {
                t_width = (THURSTextView.getWidth());
                b.setX(THURSTextView.getX());
            } else {
                t_width = FRITextView.getWidth();
                b.setX(FRITextView.getX());
            }

            if (TstartTime == 9) {
                t_height = (int) (TableRowS09.getHeight() * (TendTime - TstartTime));
                b.setY(TableRowS09.getY());
            }       //시간
            else if (TstartTime == 10) {
                t_height = (int) (TableRowS10.getHeight() * (TendTime - TstartTime));
                b.setY(TableRowS10.getY());
            } else if (TstartTime == 11) {
                t_height = (int) (TableRowS11.getHeight() * (TendTime - TstartTime));
                b.setY(TableRowS11.getY());
            } else if (TstartTime == 12) {
                t_height = (int) (TableRowS12.getHeight() * (TendTime - TstartTime));
                b.setY(TableRowS12.getY());
            } else if (TstartTime == 13) {
                t_height = (int) (TableRowS13.getHeight() * (TendTime - TstartTime));
                b.setY(TableRowS13.getY());
            } else if (TstartTime == 14) {
                t_height = (int) (TableRowS14.getHeight() * (TendTime - TstartTime));
                b.setY(TableRowS14.getY());
            } else if (TstartTime == 15) {
                t_height = (int) (TableRowS15.getHeight() * (TendTime - TstartTime));
                b.setY(TableRowS15.getY());
            } else {
                t_height = (int) (TableRowS16.getHeight() * (TendTime - TstartTime));
                b.setY(TableRowS16.getY());
            }

            b.setLayoutParams(new ConstraintLayout.LayoutParams(t_width, t_height));
            b.setTextSize(11);
            b.setTextColor(Color.argb(255, 255, 255, 255));
            b.setText(Tclass + "\n" + Tprofessor + "\n" + Tclassroom);
            b.setBackgroundColor(Color.argb(255, 200, 200, 200));

            LobbyButtonList.add(b);

            LobbyButtonDatabaseHelper = new LobbyButtonDatabaseHelper(this);
            db = LobbyButtonDatabaseHelper.getWritableDatabase();

            sql = "insert into BTable (Width,Height,Rcolor,Gcolor,Bcolor,X,Y,Text) values (?,?,?,?,?,?,?,?)";

                String[] args = {Integer.toString(t_width),Integer.toString(t_height),Integer.toString(200)
                        ,Integer.toString(200),Integer.toString(200),Float.toString(b.getX())
                        ,Float.toString(b.getY()),b.getText().toString()};
                        db.execSQL(sql, args);

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

            LobbyButtonList.add(b);
        }
    }

    public void ChangeTimeTable1()
    {
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
        long count = DatabaseUtils.queryNumEntries(db,"Table1");
        if(count ==0)   //아예 비워있을때만 데이터 추가
        {

            String sql = "insert into Table1 (StartTime,EndTime,Professor,Class,Classroom,Week) values (?,?,?,?,?,?)";
            String[] args1 = {"10", "14", "조세형", "C언어", "Y9350", "1"};
            String[] args2 = {"12", "14", "조세형", "C언어", "Y9350", "3"};
            db.execSQL(sql, args1);
            db.execSQL(sql, args2);
        }
            addTimeButton("Table1");
            ShowTimeTable();
            System.out.println(LobbyButtonList.size());
    }
    public void ChangeTimeTable2()
    {
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
        if(count ==0)   //아예 비워있을때만 데이터 추가
        {
            String sql = "insert into Table2 (StartTime,EndTime,Professor,Class,Classroom,Week) values (?,?,?,?,?,?)";
            String[] args1 = {"11", "13", "이충기", "C언어", "Y9150", "2"};
            String[] args2 = {"11", "13.5", "이충기", "C언어", "Y9150", "4"};
            String[] args3 = {"13", "15", "심호진", "공수", "비대면", "3"};
            db.execSQL(sql, args1);
            db.execSQL(sql, args2);
            db.execSQL(sql, args3);
        }
            addTimeButton("Table2");
            ShowTimeTable();
            System.out.println(LobbyButtonList.size());

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
