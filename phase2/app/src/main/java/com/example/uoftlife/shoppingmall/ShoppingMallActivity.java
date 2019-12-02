package com.example.uoftlife.shoppingmall;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.R;

public class ShoppingMallActivity extends AppCompatActivity {

    AllItem item;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_item);
        setItem1Button();
        setItem2Button();
        setItem3Button();
        setNextPageButton();

    }


    private void setNextPageButton(){
        findViewById(R.id.nextPage).setOnClickListener((view) -> {
        });
    }
    private void setItem1Button() {
        findViewById(R.id.button1).setOnClickListener((view) -> {
        });
    }
    private void setItem2Button() {
        findViewById(R.id.button2).setOnClickListener((view) -> {
        });
    }
    private void setItem3Button() {
        findViewById(R.id.button3).setOnClickListener((view) -> {
        });
    }


    // if click on the item button, the user purchases it immediately
    // and its status changes accordingly.

    }

