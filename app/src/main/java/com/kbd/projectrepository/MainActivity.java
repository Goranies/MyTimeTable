package com.kbd.projectrepository;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_ProjectRepository);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] colleage = getResources().getStringArray(R.array.colleage);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        // AutoCompleteTextView 에 아답터를 연결한다.
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, colleage));
    }

    public void clickButton(View view) {

        // 버튼을 클릭했을 때, 빈칸일 경우
        if (view.getId() == R.id.main_btn_button && TextUtils.isEmpty(autoCompleteTextView.getText())) {
            Toast.makeText(getApplicationContext(), "대학명을 입력해주세요.", Toast.LENGTH_LONG).show();
        } else if (view.getId() == R.id.main_btn_button) {
            // 버튼을 클릭하면 키보드 내리기
            view = this.getCurrentFocus();
            if(view != null){
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            // 대화상자 출력
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("학교 선택");
            builder.setMessage("해당 학교가 맞습니까?");
            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"강의 시간표 페이지로 넘어갑니다.", Toast.LENGTH_LONG).show();

                    Intent i1 = new Intent(MainActivity.this, LobbyActivity.class);
                    startActivity(i1);
                }
            });
            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                }
            });
            builder.show();
        }
    }
}