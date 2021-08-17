package com.kbd.projectrepository;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TimeInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_input);
    }

    public void clickButton(View view) {
        if (view.getId() == R.id.button) {
            Intent i2 = new Intent(TimeInputActivity.this, WizardActivity.class);
            startActivity(i2);
        }
    }
}