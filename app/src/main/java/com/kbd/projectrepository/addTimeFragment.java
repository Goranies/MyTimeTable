package com.kbd.projectrepository;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addTimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addTimeFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText name_class;
    private EditText name_classroom;
    private EditText name_professor;
    private EditText name_time;
    private Button addbutton;
    public addTimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addTimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addTimeFragment newInstance(String param1, String param2) {
        addTimeFragment fragment = new addTimeFragment();
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
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.addTime_Button:
                onClick_add();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_add_time, container, false);


        name_class = v.findViewById(R.id.AddTime_editText_Class);
        name_classroom = v.findViewById(R.id.AddTime_editText_Classroom);
        name_professor = v.findViewById(R.id.AddTime_editTexte_Professor);
        name_time = v.findViewById(R.id.AddTime_editTexte_Time);
        addbutton = v.findViewById(R.id.addTime_Button);
        addbutton.setOnClickListener(this);

        return v;
    }


    public void onClick_add()
    {
        String tclass = name_class.getText().toString();
        String tclassroom = name_classroom.getText().toString();
        String tprofessor = name_professor.getText().toString();
        String ttime = name_time.getText().toString();

        //버튼 리스트 초기화,레이아웃에서 뷰 삭제
        for(int i=0;i<LobbyActivity.LobbyButtonList.size();i++) {
            LobbyActivity.layout.removeView(LobbyActivity.LobbyButtonList.get(i));
        }
        LobbyActivity.LobbyButtonList.clear();

        LobbyDatabaseHelper LobbyDatabaseHelper = new LobbyDatabaseHelper(LobbyActivity.layout.getContext());
        SQLiteDatabase db = LobbyDatabaseHelper.getWritableDatabase();

        System.out.println("?");
        String sql = "insert into "+LobbyActivity.MainTableName+" (Professor,Class,Classroom,Time) values (?,?,?,?)";
        String[] args = {tprofessor,tclass,tclassroom,ttime};
        db.execSQL(sql, args);

        ((LobbyActivity) getActivity()).addTimeButton(LobbyActivity.MainTableName);
        ((LobbyActivity)getActivity()).ShowTimeTable();


        FragmentTransaction fragmentTransaction = ((LobbyActivity)getActivity()).fragmentManager.beginTransaction();
        fragmentTransaction.remove( ((LobbyActivity)getActivity()).fragment);
        fragmentTransaction.commit();
        ((LobbyActivity)getActivity()).fragment = null;

       MenuItem mi;
        mi =((LobbyActivity)getActivity()).Mymenu.findItem(R.id.lobby_mitem_Test1);
        mi.setVisible(true);
        mi =((LobbyActivity)getActivity()). Mymenu.findItem(R.id.lobby_mitem_Test2);
        mi.setVisible(true);

    }

}
