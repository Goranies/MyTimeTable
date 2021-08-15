package com.kbd.projectrepository;

import android.os.Bundle;
import android.telephony.mbms.GroupCall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WizardActivity extends AppCompatActivity {
    private RecyclerView groupRecycler;
    private GroupAdapter groupAdapter;
    private ArrayList<Group> groupArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        initGroupRecycler();
    }

    public void initGroupRecycler() {
        groupRecycler = findViewById(R.id.wizard_recycler);

        groupArrayList = new ArrayList<>();
        groupArrayList.add(new Group());
        groupArrayList.add(new Group());
        groupArrayList.add(new Group());
        groupArrayList.add(new Group());
        groupArrayList.add(new Group());
        groupArrayList.add(new Group());
        groupArrayList.add(new Group());

        groupAdapter = new GroupAdapter(this, groupArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        groupRecycler.setLayoutManager(linearLayoutManager);
        groupRecycler.setAdapter(groupAdapter);
    }
}