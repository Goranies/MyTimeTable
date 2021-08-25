package com.kbd.projectrepository;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link timechooseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class timechooseFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button WeekChoiceButton;
    private Button StartTimeButton;
    private Button EndTimeButton;

    public ImageView add_time;
    public ImageView remove_time;
    public TextView Week;
    public TextView StartTime;
    public TextView EndTime;
    public int SorE=0;
    public timechooseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment timechooseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static timechooseFragment newInstance(String param1, String param2) {
        timechooseFragment fragment = new timechooseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_timechoose, container, false);


        Week = v.findViewById(R.id.timechoose_TextView_Week);
        StartTime = v.findViewById(R.id.timechoose_TextView_StartTime);
        EndTime = v.findViewById(R.id.timechoose_TextView_EndTime);
        add_time = v.findViewById(R.id.Lobby_View_addTime);
        remove_time = v.findViewById(R.id.Lobby_View_removeTime);
        WeekChoiceButton= v.findViewById(R.id.Lobby_Button_ChoiceWeek);
        StartTimeButton= v.findViewById(R.id.Lobby_Button_StartTime);
        EndTimeButton= v.findViewById(R.id.Lobby_Button_EndTime);

        Week.setText("월");
        StartTime.setText("09:00");
        EndTime.setText("10:00");
        StartTimeButton.setOnClickListener(this);
        EndTimeButton.setOnClickListener(this);
        WeekChoiceButton.setOnClickListener(this);
        add_time.setOnClickListener(this);
        remove_time.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view)
    {
        addTimeFragment af =  (addTimeFragment)getParentFragment();
        switch (view.getId())
        {
            case R.id.Lobby_Button_ChoiceWeek:
                final String[] items = new String[]{"월", "화", "수", "목", "금"};
                final int[] selectedIndex = {0};

                AlertDialog.Builder Wdialog = new AlertDialog.Builder(LobbyActivity.layout.getContext());
                Wdialog.setTitle("요일을 선택하세요.").setSingleChoiceItems(items, 0,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedIndex[0] = which;
                                    }}) .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(selectedIndex[0] ==0){ Week.setText("월");}
                        else if(selectedIndex[0] ==1){ Week.setText("화");}
                        else if(selectedIndex[0] ==2){ Week.setText("수");}
                        else if(selectedIndex[0] ==3){ Week.setText("목");}
                        else if(selectedIndex[0] ==4){ Week.setText("금");}
                    }
                }).create().show();
                break;

            case R.id.Lobby_Button_StartTime:
                SorE=0;
                TimePickerDialog STdialog = new TimePickerDialog(LobbyActivity.layout.getContext(),android.R.style.Theme_Holo_Light_Dialog_NoActionBar, listener, 9, 00, false);
                STdialog.setTitle("시작시간");
                STdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                STdialog.show();
                break;

            case R.id.Lobby_Button_EndTime:
                SorE=1;
                TimePickerDialog ETdialog = new TimePickerDialog(LobbyActivity.layout.getContext(),android.R.style.Theme_Holo_Light_Dialog_NoActionBar, listener, 10, 00, false);
                ETdialog.setTitle("종료시간");
                ETdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                ETdialog.show();
                break;

            case R.id.Lobby_View_addTime:
                af.addTime();
                break;

            case R.id.Lobby_View_removeTime:
                af.removeTime();
                break;
        }
    }

    private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String Hour,Minute;

            if (minute >= 10) {Minute = minute + "";}
            else { Minute = "0" + minute;}
            if (hourOfDay >= 10) {Hour = hourOfDay + "";}
            else { Hour = "0" + hourOfDay;}

            if(SorE==0){StartTime.setText(Hour+":"+Minute);}
            else if(SorE==1){EndTime.setText(Hour+":"+Minute);}
        }
    };

}