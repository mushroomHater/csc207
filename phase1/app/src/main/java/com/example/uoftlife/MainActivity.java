package com.example.uoftlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        UserManager.loadUsers(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        ImageView homeView = findViewById(R.id.homeView);

        setBtnStart();
        setBtnSetting();
        setBtnRanking();

    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.logout_info))
                .setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        if (GameConfiguration.getConfig().getLanguage().equals("English")){
            ((Button) findViewById(R.id.btnStart)).setText(R.string.start);
            ((Button) findViewById(R.id.btnSetting)).setText(R.string.setting);
            ((Button) findViewById(R.id.btnRanking)).setText(R.string.ranking);
        }else{
            ((Button) findViewById(R.id.btnStart)).setText(R.string.start_cn);
            ((Button) findViewById(R.id.btnSetting)).setText(R.string.setting_cn);
            ((Button) findViewById(R.id.btnRanking)).setText(R.string.ranking_cn);
        }

    }
    private void setBtnStart() {
        findViewById(R.id.btnStart).setOnClickListener((view) -> {
            startActivity(new Intent(MainActivity.this, StageBetweenGames.class));
        });

    }


    private void setBtnSetting() {
        findViewById(R.id.btnSetting).setOnClickListener((view) -> {
            Intent intent = new Intent(this, PauseDialogConfig.class);
            startActivity(intent);
        });
    }

    private void setBtnRanking() {

    }


}


