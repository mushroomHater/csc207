package com.example.uoftlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLevelOne = (Button) findViewById(R.id.btnLevelOne);
        ImageView homeView = (ImageView) findViewById(R.id.homeView);

        btnLevelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LevelOne.class));
            }
        });
    }

    private void setLevelThreeBtn() {
        findViewById(R.id.LevelThreeBtn).setOnClickListener((view) -> {
            Intent i = new Intent(this, GameLevelThree.class);
            startActivity(i);
        });
    }
}
