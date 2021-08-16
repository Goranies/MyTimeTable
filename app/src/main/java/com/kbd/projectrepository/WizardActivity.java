package com.kbd.projectrepository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.telephony.mbms.GroupCall;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WizardActivity extends AppCompatActivity {
    private RecyclerView groupRecycler;
    private GroupAdapter groupAdapter;
    private ArrayList<Group> groupArrayList;

    private WizardDatabaseHelper wizardDatabaseHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        wizardDatabaseHelper = new WizardDatabaseHelper(this);
        db = wizardDatabaseHelper.getWritableDatabase();

        initGroupRecycler();
    }

    public void initGroupRecycler() {
        groupRecycler = findViewById(R.id.wizard_recycler);
        groupArrayList = new ArrayList<>();

        //쿼리문
        String sql = "select * from GroupTable";

        //쿼리 실행
        Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()) {
            int id_position = c.getColumnIndex("ID");
            int name_position = c.getColumnIndex("Name");

            int ID = c.getInt(id_position);
            String name = c.getString(name_position);

            groupArrayList.add(new Group(name));
        }
        //close 꼭 안해도 된다??
        //db.close();

        //만약 데이터베이스에 그룹이 없다면
        if(groupArrayList.isEmpty()) {
            Group g = new Group(1);
            insertGroup(g);
            groupArrayList.add(g);
        }

        groupAdapter = new GroupAdapter(this, groupArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        groupRecycler.setLayoutManager(linearLayoutManager);
        groupRecycler.setAdapter(groupAdapter);
    }

    public void insertGroup(String name) {
        String sql = "insert into GroupTable (Name) values (?)";

        //?에 바인딩될 값 배열
        String[] arg = {name};

        Log.d("Group", name);

        //저장한다
        db.execSQL(sql, arg);

        if(groupAdapter != null) {
            groupArrayList.add(new Group(groupArrayList.size() + 1));
            groupAdapter.notifyDataSetChanged();
        }
        //db.close();
    }

    public void insertGroup(Group group) {
        insertGroup(group.getGroupName());
    }

    public void deleteGroup(int ID) {
        String sql = "delete from GroupTable where ID = ?";
        String[] args = {Integer.toString(ID)};

        db.execSQL(sql, args);
    }

    public void renameGroup(int ID, String groupName) {
        String sql = "update TestTable set Name = ? where ID = ?";
        String[] args = {groupName, Integer.toString(ID)};

        db.execSQL(sql, args);
    }
}