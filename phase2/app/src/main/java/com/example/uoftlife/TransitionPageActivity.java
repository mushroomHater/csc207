package com.example.uoftlife;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class TransitionPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_page);
    }
}
