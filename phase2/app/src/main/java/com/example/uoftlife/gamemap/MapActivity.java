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
import com.example.uoftlife.util.TransitionPageBuilder;

import java.util.Timer;
import java.util.TimerTask;

public class MapActivity extends GameBaseActivity {

    private int gridWidth;
    private int gridHeight;

    private Timer timer;

    private void startTask() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            private TransitionPageBuilder builder = new TransitionPageBuilder(MapActivity.this).setShowingTime(8);
            private int frequencySpliter = 5;

            @Override
            public void run() {
                checkEndding();
                if (DataFacade.getValue("repletion") <= 20 || DataFacade.getValue("vitality") <= 20) {
                    DataFacade.addToValue("health", -1);
                }
                DataFacade.addToValue("time", -1);
                frequencySpliter--;
                if (frequencySpliter == 0) {
                    frequencySpliter = 5;
                    DataFacade.addToValue("practice", -1);
                }
                if (DataFacade.getValue("mood") <= 20) {
                    DataFacade.setTempData("status", String.format(getString(R.string.status_bad_mood), "username"));
                } else if (DataFacade.getValue("mood") >= 90) {
                    DataFacade.setTempData("status", String.format(getString(R.string.status_good_mood), "username"));
                }

            }

            private void checkEndding() {
                if (DataFacade.getValue("time") <= 0) {
                    double mark = DataFacade.getValue("mark");
                    int diff = (100 - DataFacade.getValue("practice")) + (100 - DataFacade.getValue("understanding"));
                    if (diff < 0) {
                        diff = 0;
                    }
                    mark -= diff * 0.4;

                    builder.setDescription(DataFacade
                            .getTempData("name") +
                            " have to take final exam! According to the body condition and mastering of knowledge, get final exam "
                            + (100 - diff) + ". And Overall mark is " + mark + ".");

                    if (mark >= 60) {
                        builder.setTitle("The semester is over...Congratulations!");
                    } else {
                        builder.setTitle("The semester is over...You failed.");
                    }
                    builder.start();
                    finish();
                }
                if (DataFacade.getValue("health") <= 0) {
                    builder.setTitle("You died").setDescription("Because starving or staying up too long... please try again.").start();
                    finish();
                }
                if (DataFacade.getValue("mood") <= 0) {
                    builder.setTitle("You suicide.").setDescription("Your feel so frustrated... please try again.").start();
                    finish();
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        startTask();
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }


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
        int windowHeight = metrics.heightPixels - GameConstants.RESERVE_HEIGHT;
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
