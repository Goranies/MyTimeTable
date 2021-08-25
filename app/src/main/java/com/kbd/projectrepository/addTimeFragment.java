package com.kbd.projectrepository;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addTimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addTimeFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static EditText name_class;
    public static EditText name_classroom;
    public static EditText name_professor;

    public static ArrayList<timechooseFragment> timechoosefragment_list = new ArrayList();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_add_time, container, false);
        timechoosefragment_list.clear();

        name_class = v.findViewById(R.id.AddTime_editText_Class);
        name_classroom = v.findViewById(R.id.AddTime_editText_Classroom);
        name_professor = v.findViewById(R.id.AddTime_editTexte_Professor);


        timechooseFragment fragment = new timechooseFragment();
        FragmentTransaction mFragmentTransaction = getChildFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.addTimeFragment_Layout,fragment);
        mFragmentTransaction.commit();
        timechoosefragment_list.add(fragment);
        return v;
    }
    public void addTime()
    {
        if(timechoosefragment_list.size() == 5)
        {
         Toast.makeText(this.getContext(), "더 이상 추가할 수 없습니다.", Toast.LENGTH_LONG).show();
        }
        else {
            FragmentTransaction mFragmentTransaction = getChildFragmentManager().beginTransaction();
            timechoosefragment_list.get(timechoosefragment_list.size() - 1).add_time.setVisibility(View.INVISIBLE);
            timechoosefragment_list.get(timechoosefragment_list.size() - 1).remove_time.setVisibility(View.INVISIBLE);

            timechooseFragment fragment = new timechooseFragment();
            mFragmentTransaction.add(R.id.addTimeFragment_Layout, fragment);
            mFragmentTransaction.commit();
            timechoosefragment_list.add(fragment);
        }
    }
    public void removeTime()
    {
        if(timechoosefragment_list.size() == 1)
        {
            Toast.makeText(this.getContext(), "시간이 한개밖에 존재하지 않습니다.", Toast.LENGTH_LONG).show();
        }
        else {
            FragmentTransaction mFragmentTransaction = getChildFragmentManager().beginTransaction();
            addTimeFragment.timechoosefragment_list.get(timechoosefragment_list.size() - 2).add_time.setVisibility(View.VISIBLE);
            addTimeFragment.timechoosefragment_list.get(timechoosefragment_list.size() - 2).remove_time.setVisibility(View.VISIBLE);
            mFragmentTransaction.remove(addTimeFragment.timechoosefragment_list.get(addTimeFragment.timechoosefragment_list.size()-1));

            mFragmentTransaction.commit();
            timechoosefragment_list.remove(addTimeFragment.timechoosefragment_list.size() - 1);
        }
    }
}
