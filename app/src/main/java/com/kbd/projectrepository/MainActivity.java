package com.kbd.projectrepository;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_ProjectRepository);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickButton(View view) {
        if(view.getId() == R.id.main_btn_button) {
            Intent i1 = new Intent(this, LobbyActivity.class);
            startActivity(i1);
        }
    }
}