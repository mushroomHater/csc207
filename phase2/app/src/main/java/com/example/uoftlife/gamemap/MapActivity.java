package com.example.uoftlife.gamemap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.uoftlife.R;

import java.util.ArrayList;
import java.util.List;

public abstract class MapActivity extends AppCompatActivity {

    public MapActivity(Activity a) {
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        System.out.println("in MapActivity");

        setSchoolButton();
        setShoppingMallButton();
        setHomeButton();


    }

    //we create the home button here, when this activity is been called
    private void setHomeButton() {
        findViewById(R.id.home).setOnClickListener((view) -> {
        });
    }

    private void setSchoolButton() {
        findViewById(R.id.school).setOnClickListener((view) -> {
        });
    }

    private void setShoppingMallButton() {
        findViewById(R.id.shoppingmall).setOnClickListener((view) -> {
        });
    }

}
