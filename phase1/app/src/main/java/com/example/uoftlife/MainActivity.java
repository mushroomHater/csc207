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

public class MainActivity extends AppCompatActivity {
    private static final String USER_SHARED = "user_shared";
    private static final String USER_NAME = "user_name";
    private static final String USER_PASSWORD = "user_password";
    private SharedPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserManager.loadUsers(this);
        userPreferences = getSharedPreferences(USER_SHARED, MODE_PRIVATE);
        String username = userPreferences.getString(USER_NAME, "");
        String password = userPreferences.getString(USER_PASSWORD, "");

        if (UserManager.authenticate(username, password) == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        ImageView homeView = findViewById(R.id.homeView);
        setLevelOneBtn();
        setLevelTwoBtn();
        setLevelThreeBtn();


    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.logout_info))
                .setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userPreferences.edit().putString(USER_NAME, "")
                                .putString(USER_PASSWORD, "").apply();
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
    protected void onDestroy() {
        UserManager.saveToFile(this);
        super.onDestroy();
    }

    private void setLevelOneBtn() {
        findViewById(R.id.btnLevelOne).setOnClickListener((view) -> {
            startActivity(new Intent(MainActivity.this, LevelOne.class));
        });
    }

    private void setLevelTwoBtn() {
        findViewById(R.id.levelTwoBtn).setOnClickListener((view) -> {
            startActivity(new Intent(MainActivity.this, LevelTwoPage.class));
        });
    }

    private void setLevelThreeBtn() {
        findViewById(R.id.LevelThreeBtn).setOnClickListener((view) -> {
            Intent i = new Intent(this, GameLevelThree.class);
            startActivity(i);
        });

    }

}

