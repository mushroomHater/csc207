package com.example.uoftlife.gamemap;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uoftlife.GameBaseActivity;
import com.example.uoftlife.R;
import com.example.uoftlife.data.DataFacade;
import com.example.uoftlife.data.GameConstants;
import com.example.uoftlife.floating.HomeEventActivity;
import com.example.uoftlife.floating.MallEventActivity;
import com.example.uoftlife.floating.SchoolEventActivity;
import com.example.uoftlife.util.calculator.GameProcessorStrategy;

public class MapActivity extends GameBaseActivity {

    private int gridWidth;
    private int gridHeight;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo set find road
        setSchoolButton();
        setShoppingMallButton();
        setHomeButton();
    }

    private void setBackground() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int windowWidth = metrics.widthPixels;
        int windowHeight = metrics.heightPixels - 220;
        gridWidth = ((int) (windowWidth / (GameConstants.HORIZONTAL_GRIDS + GameConstants.ROAD_WIDTH * (GameConstants.HORIZONTAL_GRIDS + 1))));
        gridHeight = ((int) (windowHeight / (GameConstants.VERTICAL_GRIDS + GameConstants.ROAD_WIDTH * (GameConstants.HORIZONTAL_GRIDS + 1))));
        ((ViewGroup) findViewById(R.id.base)).addView(new BuildingView(this, gridWidth, gridHeight));
    }

    @Override
    protected int setContentLayout() {
        setBackground();
        return R.layout.activity_map;
    }

    @Override
    protected boolean setSavable() {
        return true;
    }

    @Override
    protected GameProcessorStrategy setSceneStrategy() {
        return null;
    }


    private void setRealCoordinates(int x, int y, Button button) {
        button.setX(((int) (x * gridWidth * (1 + GameConstants.ROAD_WIDTH) + gridWidth * GameConstants.ROAD_WIDTH)));
        button.setY(((int) (y * gridHeight * (1 + GameConstants.ROAD_WIDTH) + gridHeight * GameConstants.ROAD_WIDTH)));
    }

    //we create the home button here, when this activity is been called
    private void setHomeButton() {
        Button button = findViewById(R.id.home);
        setRealCoordinates(DataFacade.getValue("home_x"),
                DataFacade.getValue("home_y"), button);
        button.setOnClickListener((view) -> {
            startActivity(new Intent(this, HomeEventActivity.class));
        });
    }

    private void setSchoolButton() {
        Button button = findViewById(R.id.school);
        setRealCoordinates(DataFacade.getValue("school_x"),
                DataFacade.getValue("school_y"), button);
        button.setOnClickListener((view) -> {
            startActivity(new Intent(this, SchoolEventActivity.class));
        });
    }

    private void setShoppingMallButton() {
        Button button = findViewById(R.id.mall);
        setRealCoordinates(DataFacade.getValue("mall_x"),
                DataFacade.getValue("mall_y"), button);
        button.setOnClickListener((view) -> {
            startActivity(new Intent(this, MallEventActivity.class));
        });
    }

}
