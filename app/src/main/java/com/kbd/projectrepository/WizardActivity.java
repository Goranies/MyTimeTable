package com.kbd.projectrepository;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WizardActivity extends AppCompatActivity {
//    private InputMethodManager imm;
//    private EditText groupName;

    private RecyclerView groupRecycler;
    private ArrayList<Group> groupArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        initGroupRecycler();
//        initGroup();
    }

    public void initGroupRecycler() {
        groupRecycler = findViewById(R.id.wizard_recycler);

        groupArrayList = new ArrayList<>();
        groupArrayList.add(new Group("Group 1"));
        groupArrayList.add(new Group("Group 2"));
        groupArrayList.add(new Group("Group 3"));
        groupArrayList.add(new Group("Group 4"));
        groupArrayList.add(new Group("Group 5"));
        groupArrayList.add(new Group("Group 6"));
        groupArrayList.add(new Group("Group 7"));
        groupArrayList.add(new Group("Group 8"));

        GroupAdapter groupAdapter = new GroupAdapter(this, groupArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        groupRecycler.setLayoutManager(linearLayoutManager);
        groupRecycler.setAdapter(groupAdapter);
    }

    public void initGroup() {
//        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        groupName = findViewById(R.id.wizard_card_editText_groupName);
//
//        groupName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus) {
//                    imm.showSoftInput(v, 0);
//                } else {
//                    groupName.setFocusable(false);
//                }
//            }
//        });
//
//        groupName.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
//                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    public void clickButton(View view) {
//        if(view.getId() == R.id.wizard_card_button_editName) {
//            groupName.setFocusable(true);
//            groupName.setFocusableInTouchMode(true);
//            groupName.requestFocus();
//        }
    }
}