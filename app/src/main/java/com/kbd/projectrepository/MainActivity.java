package com.kbd.projectrepository;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<String> list;          // 데이터를 넣은 리스트변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_ProjectRepository);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 리스트에 검색될 데이터(단어)를 추가한다.
        settingList();

        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        // AutoCompleteTextView 에 아답터를 연결한다.
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list));
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList() {
        list.add("채수빈");
        list.add("박지현");
        list.add("수지");
        list.add("남태현");
        list.add("하성운");
        list.add("크리스탈");
        list.add("강승윤");
        list.add("손나은");
        list.add("남주혁");
        list.add("루이");
        list.add("진영");
        list.add("슬기");
        list.add("이해인");
        list.add("고원희");
        list.add("설리");
        list.add("공명");
        list.add("김예림");
        list.add("혜리");
        list.add("웬디");
        list.add("박혜수");
        list.add("카이");
        list.add("진세연");
        list.add("동호");
        list.add("박세완");
        list.add("도희");
        list.add("창모");
        list.add("허영지");
    }

    public void clickButton(View view) {
        if (view.getId() == R.id.main_btn_button) {
            Intent i1 = new Intent(this, LobbyActivity.class);
            startActivity(i1);
        }
    }
}

