package com.example.uoftlife.shoppingmall;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.R;
import com.example.uoftlife.util.TransitionPageBuilder;

public class ShoppingMallActivity extends AppCompatActivity {

    AllItem item;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_item);
        setBreadButton();
        setCoffeeButton();
        setLipstickButton();
        setBubbleTeaButton();
        setBookButton();
    }

    private void setBreadButton(){
        Button bread = findViewById(R.id.bread);
        bread.setOnClickListener((view) -> new TransitionPageBuilder(this).setTitle("bread")
                .setDescription("item bread, costs you 5 dollars and helps you to gain 20 repletion")
                .setShowingTime(30)
                .addValueChange("money", -5)
                .addValueChange("repletion", 20)
                .start());
    }

    private void setCoffeeButton(){
        Button coffee = findViewById(R.id.coffee);
        coffee.setOnClickListener((view) -> new TransitionPageBuilder(this).setTitle("coffee")
                .setDescription("item coffee, costs 3 dollars and helps you to gain 10 vitality")
                .setShowingTime(30)
                .addValueChange("money", -3)
                .addValueChange("vitality", 20)
                .start());
    }

    private void setBubbleTeaButton(){
        Button bubbleTea = findViewById(R.id.bubbleTea);
        bubbleTea.setOnClickListener((view) -> new TransitionPageBuilder(this).setTitle("bubbleTea")
                .setDescription("item bubble tea, costs 8 dollars and helps you to gain 10 happiness")
                .setShowingTime(30)
                .addValueChange("money", -8)
                .addValueChange("mood", 10)
                .start());
    }

    private void setBookButton(){
        Button book = findViewById(R.id.book);
        book.setOnClickListener((view) -> new TransitionPageBuilder(this).setTitle("book")
                .setDescription("item book, costs you 100, helps you to gain 5 knowledge but loses 20 happiness")
                .setShowingTime(30)
                .addValueChange("money", -100)
                .addValueChange("mood", -20)
                .addValueChange("understanding", 5)
                .start());
    }

    private void setLipstickButton(){
        Button lipstick = findViewById(R.id.lipstick);
        lipstick.setOnClickListener((view) -> new TransitionPageBuilder(this).setTitle("lipstick")
                .setDescription("item lipstick, costs 50 dollars and gains 15 happiness")
                .setShowingTime(30)
                .addValueChange("money", -50)
                .addValueChange("mood", 10)
                .start());
    }

}

