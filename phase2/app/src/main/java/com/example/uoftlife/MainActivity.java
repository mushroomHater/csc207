package com.example.uoftlife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.data.DataFacade;
import com.example.uoftlife.data.GameConstants;
import com.example.uoftlife.floating.DifficultySelectActivity;
import com.example.uoftlife.gamemap.MapActivity;
import com.example.uoftlife.transpage.InstructionPageActivity;
import com.example.uoftlife.util.GameMessenger;
import com.example.uoftlife.util.TransitionPageBuilder;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeApplication();
        setListeners();


        UserManager.loadUsers(this);
        SharedPreferences myPreference = getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean flag = myPreference.getBoolean("login", false);
        if (!flag) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        String name = myPreference.getString("name", null);
        UserManager.setCurrentUser(UserManager.getUsers().get(name));


    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.logout_info))
                .setPositiveButton(R.string.logout, (dialog, which) -> {
                    SharedPreferences myPreference = getSharedPreferences(GameConstants.USER_FILE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = myPreference.edit();
                    editor.putBoolean("login", false);
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("no", (dialog, which) -> dialog.dismiss()).show();
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


    /**
     * Initialize the background data of whole application. Important for future functionality.
     * If don't execute this step, will cause some nullPointerExceptions (although many of them are
     * caught, he behavior will still be undesirable)
     */
    private void initializeApplication() {
        DataFacade.setContext(getApplicationContext());
        DataFacade.initialize();
        GameMessenger.getMessenger().initialize();
        DataFacade.setTempData("status", String.format(getString(R.string.status_has_due), "username"));
    }

    private void setListeners() {
        findViewById(R.id.start).setOnClickListener((view) ->
                startActivity(new Intent(this, DifficultySelectActivity.class)));
        findViewById(R.id.load).setOnClickListener((view) -> {
            DataFacade.loadConfig();
            DataFacade.loadProgress();
            if (DataFacade.getValue("started") == 1) {
                startActivity(new Intent(this, MapActivity.class));
            } else {
                GameMessenger.getMessenger().toastMessage(getString(R.string.load_alert));
            }
        });
        findViewById(R.id.help).setOnClickListener((view) ->
                startActivity(new Intent(this, InstructionPageActivity.class)));
        findViewById(R.id.clear).setOnClickListener((view) -> {
            DataFacade.clearFile();
            GameMessenger.getMessenger().toastMessage(getString(R.string.clear_success));
        });
        findViewById(R.id.logout).setOnClickListener((view) -> {
            new TransitionPageBuilder(this).setTitle("title")
                    .setDescription("description")
                    .setShowingTime(30)
                    .addValueChange("money", +300)
                    .addValueChange("repletion", -20)
                    .start();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GameMessenger.getMessenger().clearAll();
    }
}








