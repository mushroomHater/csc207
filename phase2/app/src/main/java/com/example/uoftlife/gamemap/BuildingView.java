package com.example.uoftlife.gamemap;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.uoftlife.R;

import java.util.ArrayList;
import java.util.List;

public class BuildingView extends View {

    public List<Rect> allRectangle = new ArrayList<Rect>();
    public Paint buildingPaint;
    protected float shoppingMallLocationX;
    protected float shoppingMallLocationY;
    protected float schoolLocationX;
    protected float schoolLocationY;

    private final int gridsHorizontal = 5;
    private final int gridsVertical = 6;
    private final float streetWidth = 0.2f;
    private final int reservedHeight = 250;


    public BuildingView(Activity activity) {
        super(activity);
        buildingPaint = new Paint();
        buildingPaint.setColor(Color.BLUE);


        DisplayMetrics metrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        }
        int windowWidth = metrics.widthPixels;
        int windowHeight = metrics.heightPixels - reservedHeight;

        System.out.println(metrics.heightPixels+"hhh");
        System.out.println(windowHeight);

        int gridWidth = ((int) (windowWidth / (gridsHorizontal + streetWidth * (gridsHorizontal + 1))));
        int gridHeight = ((int) (windowHeight / (gridsVertical + streetWidth * (gridsVertical + 1))));


        int drawX = 0;
        int drawY = reservedHeight;
        for (int x = 1; x <= gridsHorizontal; x++) {
            drawX += gridWidth * streetWidth;
            for (int y = 1; y <= gridsVertical; y++) {
                drawY += gridHeight * streetWidth;
                allRectangle.add(new Rect(drawX, drawY, drawX + gridWidth, drawY + gridHeight));
                drawY += gridHeight;
            }
            drawY = reservedHeight;
            drawX += gridWidth;
        }
}

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        int mallIndex = (int) (Math.random() * allRectangle.size());
        int schoolIndex = 0;
        while (schoolIndex == mallIndex) schoolIndex = (int) (Math.random() * allRectangle.size());
        for (int i = 0; i < allRectangle.size(); i++) {
            if (i == mallIndex) {
                //change the shoppingmall button's location and save it
                Button shoppingmall = findViewById(R.id.shoppingmall);
                changeButtonLocation(shoppingmall,i);
                saveButtonLocationX(shoppingMallLocationX, i);
                saveButtonLocationY(shoppingMallLocationY, i);
            } else if (i == schoolIndex) {
                // change the school button's location and save it
                Button school = findViewById(R.id.school);
                changeButtonLocation(school,i);
                saveButtonLocationX(schoolLocationX, i);
                saveButtonLocationY(schoolLocationY, i);
            } else {
                canvas.drawRect(allRectangle.get(i), buildingPaint);
            }
        }
    }
    //helper function to set the X coordinate of a button
    private void saveButtonLocationX(float savedLocationX, int index){
        savedLocationX = allRectangle.get(index).centerX();
    }

    //helper function to set the X coordinate of a button
    private void saveButtonLocationY(float savedLocationY, int index) {
        savedLocationY = allRectangle.get(index).centerY();
    }


    //helper function to change the button location
    private void changeButtonLocation(Button button, int index){
        button.setX(allRectangle.get(index).centerX());
        button.setY(allRectangle.get(index).centerY());
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}





