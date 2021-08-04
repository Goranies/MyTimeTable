package com.kbd.projectrepository;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_ProjectRepository);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}