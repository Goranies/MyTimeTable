package com.kbd.projectrepository;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickButton(View view) {
        Log.d("Test", "Test");
        if(view.getId() == R.id.button) {
            Log.d("Test", "Test2");
            Toast toast = Toast.makeText(this.getApplicationContext(), "Hello", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}