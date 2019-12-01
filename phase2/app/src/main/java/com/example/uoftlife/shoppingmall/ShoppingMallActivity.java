package com.example.uoftlife.shoppingmall;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.R;

public class ShoppingMallActivity extends AppCompatActivity {

    AllItem item;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_item);
        setBookButton();
        setBreadButton();
        setBubbleTeaButton();
        setCoffeeButton();
        setLipstickButton();
    }

    private void setBookButton() {
        findViewById(R.id.book).setOnClickListener((view) -> {
        });
    }

    private void setBubbleTeaButton() {
        findViewById(R.id.bubbleTea).setOnClickListener((view) -> {
        });
    }
    private void setCoffeeButton() {
        findViewById(R.id.coffee).setOnClickListener((view) -> {
        });
    }
    private void setBreadButton() {
        findViewById(R.id.bread).setOnClickListener((view) -> {
        });
    }
    private void setLipstickButton() {
        findViewById(R.id.lipstick).setOnClickListener((view) -> {
        });
    }

    // if click on the item button, the user purchases it immediately
    // and its status changes accordingly.

    }

