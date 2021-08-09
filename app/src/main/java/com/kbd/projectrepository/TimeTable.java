package com.kbd.projectrepository;

import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

public class TimeTable {
    protected ArrayList<Time>[] time = new ArrayList[15];
    protected int Num_Time = 0;


    public TimeTable() { }

    public void get_time(Time Ttime)
    {
        int check = CheckSame(Ttime);
        if(check == -1)    //아예 없던 강의라면 다음배열에 강의 추가하고 강의갯수 1 증가
        {
            time[Num_Time] = new ArrayList<Time>();
            time[Num_Time].add(Ttime);
            Num_Time++;
        }
        else    //같은 강의라면 그 강의에 추가
        {
            time[check].add(Ttime);
        }
    }

    public int CheckSame(Time Ttime) //같은 강의인지 확
    {
        for(int i=0;i<Num_Time;i++)
        {
            if(Ttime.Name_Professor.equals(time[i].get(0).Name_Professor) && Ttime.Name_Class.equals(time[i].get(0).Name_Class))        //교수와 강의명이 같으면 강의 번호 리턴
            {return i;}
        }
        return -1;      //아니라면 -1리턴
    }

    public int getNum_Time() {
        return Num_Time;
    }
}
