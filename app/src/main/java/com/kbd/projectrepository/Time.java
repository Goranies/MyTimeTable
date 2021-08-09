package com.kbd.projectrepository;

public class Time {
    protected double Start_Time;
    protected double End_Time;
    protected int Week;
    protected String Name_Professor;
    protected String Name_Class;
    protected String Name_Classroom;


    public Time(double TStart_Time,double TEnd_Time,String TName_Professor,String TName_Class,int TWeek,String TName_Classroom)
    {
        Start_Time = TStart_Time;
        End_Time = TEnd_Time;
        Week = TWeek;
        Name_Professor = TName_Professor;
        Name_Class = TName_Class;
        Name_Classroom = TName_Classroom;
    }

    public double getEnd_Time() {return End_Time; }
    public double getStart_Time() {return Start_Time; }
    public int getWeek(){return Week;}
    public String getName_Professor() {return Name_Professor; }
    public String getName_Class() {return Name_Class; }
    public String getName_Classroom() {return Name_Classroom; }
    public void setEnd_Time(double TEnd_Time){End_Time = TEnd_Time;}
    public void setStart_Time(double TStart_Time){Start_Time = TStart_Time;}
    public void setName_Professor(String TName_Professor){Name_Professor = TName_Professor;}
    public void setName_Class(String TName_Class){Name_Class = TName_Class;}
    public void setName_Classroom(String TName_Classroom){Name_Class = TName_Classroom;}
    public void setWeek(int TWeek){Week = TWeek;}
}
