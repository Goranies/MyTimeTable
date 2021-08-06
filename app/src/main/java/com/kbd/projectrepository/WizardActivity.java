package com.kbd.projectrepository;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class WizardActivity extends AppCompatActivity {
    private InputMethodManager imm;
    private EditText groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        groupName = findViewById(R.id.wizard_card_editText_groupName);

        groupName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    imm.showSoftInput(v, 0);
                } else {
                    groupName.setFocusable(false);
                }
            }
        });

        groupName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    public void clickButton(View view) {
        if(view.getId() == R.id.wizard_card_button_editName) {
            groupName.setFocusable(true);
            groupName.setFocusableInTouchMode(true);
            groupName.requestFocus();
        }
    }
}