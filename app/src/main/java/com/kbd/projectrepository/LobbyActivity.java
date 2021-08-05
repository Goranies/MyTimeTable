package com.kbd.projectrepository;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.lobbybar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==R.id.lobby_mitem_GotoWizard)
        {
            Intent intent = new Intent(this,WizardActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.lobby_mitem_GotoInput)
        {
            Intent intent = new Intent(this,TimeInputActivity.class);
            startActivity(intent);
        }
        return true;
    }

}