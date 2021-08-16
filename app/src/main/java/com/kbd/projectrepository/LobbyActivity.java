package com.kbd.projectrepository;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LobbyActivity extends AppCompatActivity {

    protected TimeTable timetable;      //주 시간표
    protected TextView MonthTextView;   //요일 View들
    protected TextView TUESTextView;
    protected TextView WEDNESTextView;
    protected TextView THURSTextView;
    protected TextView FRITextView;

    protected TableRow TableRowS09;     //시간View들
    protected TableRow TableRowS10;
    protected TableRow TableRowS11;
    protected TableRow TableRowS12;
    protected TableRow TableRowS13;
    protected TableRow TableRowS14;
    protected TableRow TableRowS15;
    protected TableRow TableRowS16;


    ArrayList<Button> LobbyButtonList = new ArrayList();
    protected ConstraintLayout layout;
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timetable = new TimeTable();        //타임테이블 생성

        /*mDbOpenHelper.insertColumn(10,14,1,"조세형","Y9350","C언어");

        Cursor iCursor = mDbOpenHelper.selectColumns();
        while(iCursor.moveToNext()){
            String tempst = iCursor.getString(iCursor.getColumnIndex("Start_Time"));
            String tempet = iCursor.getString(iCursor.getColumnIndex("End_Time"));
            String tempw = iCursor.getString(iCursor.getColumnIndex("Week"));
            String tempnp = iCursor.getString(iCursor.getColumnIndex("Name_Professor"));
            String tempnr = iCursor.getString(iCursor.getColumnIndex("Name_Room"));
            String tempnc = iCursor.getString(iCursor.getColumnIndex("Name_Class"));
            String Result = tempst + "," +tempet + "," + tempw + "," + tempnp+ "," +tempnr + "," + tempnc;
            System.out.println(Result);

        }*/

        Time clang = new Time(10,14,"조세형","C언어",1,"Y9350"); //시간표 직접추가 test
        timetable.get_time(clang);

        Time korean = new Time(12,13,"조세형","C언어",3,"Y9350"); //시간표 직접추가 test
        timetable.get_time(korean);

        Time math = new Time(9,12.5,"좆호진","공수",2,"Y1230"); //시간표 직접추가 test
        timetable.get_time(math);

        Time java = new Time(14,16,"충커","설계왕프로젝트",4,"Y2345"); //시간표 직접추가 test
        timetable.get_time(java);

        Time talk = new Time(15,16,"오은엽","발토",5,"Y1130"); //시간표 직접추가 test
        timetable.get_time(talk);
        setContentView(R.layout.activity_lobby);    // 콘텐트 뷰 그리기

        MonthTextView = findViewById(R.id.LobbyTextViewMon);// 뷰 추가
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
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);
        layout = findViewById((R.id.LobbyConstraintLayout));
        addTimeButton();
        ShowTimeTable();
        LobbyButtonList.clear();

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
        return true;
    }


    public void addTimeButton()           //버튼추가메소드
    {
        for(int i = 0; i< timetable.getNum_Time(); i++)             //생성
        {
            for(int j = 0; j<timetable.time[i].size(); j++)
            {
                double start_time,end_time,week;
                String name_professor,name_class,name_classroom;

              start_time = timetable.time[i].get(j).getStart_Time();
                end_time = timetable.time[i].get(j).getEnd_Time();
                week = timetable.time[i].get(j).getWeek();
                name_professor = timetable.time[i].get(j).getName_Professor();
                name_class = timetable.time[i].get(j).getName_Class();
                name_classroom = timetable.time[i].get(j).getName_Classroom();


                Button b = new Button(this);
                int t_width,t_height;

               if(week == 1){ t_width =MonthTextView.getWidth();b.setX(MonthTextView.getX());}       //요일
               else if(week == 2){t_width =(TUESTextView.getWidth());b.setX(TUESTextView.getX());}
               else if(week == 3){t_width =WEDNESTextView.getWidth();b.setX(WEDNESTextView.getX());}
               else if(week == 4){t_width =(THURSTextView.getWidth());b.setX(THURSTextView.getX());}
               else{t_width =FRITextView.getWidth();b.setX(FRITextView.getX());}

                if(start_time == 9){t_height=(int) (TableRowS09.getHeight() * (end_time-start_time));b.setY(TableRowS09.getY());}       //시간
                else if(start_time == 10){t_height=(int) (TableRowS10.getHeight() * (end_time-start_time));b.setY(TableRowS10.getY());}
                else if(start_time == 11){t_height=(int) (TableRowS11.getHeight() * (end_time-start_time));b.setY(TableRowS11.getY());}
                else if(start_time == 12){t_height=(int) (TableRowS12.getHeight() * (end_time-start_time));b.setY(TableRowS12.getY());}
                else if(start_time == 13){t_height=(int) (TableRowS13.getHeight() * (end_time-start_time));b.setY(TableRowS13.getY());}
                else if(start_time == 14){t_height=(int) (TableRowS14.getHeight() * (end_time-start_time));b.setY(TableRowS14.getY());}
                else if(start_time == 15){t_height=(int) (TableRowS15.getHeight() * (end_time-start_time));b.setY(TableRowS15.getY());}
                else {t_height=(int) (TableRowS16.getHeight() * (end_time-start_time));b.setY(TableRowS16.getY());}

                b.setLayoutParams(new ConstraintLayout.LayoutParams(t_width,t_height));
                b.setTextSize(11);
                b.setTextColor(Color.argb(255,255,255,255));
                b.setText(name_class+"\n"+name_professor+"\n"+name_classroom);
                setTimeColor(b);

                LobbyButtonList.add(b);
            }
        }
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

    public void setTimeColor(Button b)
    {
        Random random = new Random();
        int color = Color.argb(255,random.nextInt(100)+100,random.nextInt(100)+100,random.nextInt(100)+100);
        for(int i = 0; i< LobbyButtonList.size(); i++)             //생성
        {
            if(LobbyButtonList.get(i).getText().equals(b.getText()))
            {
                ColorDrawable viewColor = (ColorDrawable) LobbyButtonList.get(i).getBackground();
                color = viewColor.getColor();
                b.setBackgroundColor(color);
                return;
            }
        }
        b.setBackgroundColor(color);
    }

}
