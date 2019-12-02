package com.example.uoftlife.gamework;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.uoftlife.GameBaseActivity;
import com.example.uoftlife.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CollectCoinActivity extends GameBaseActivity {

    private int bagLocation = 150;
    private int bagHeight = 1560;
    private int bagLastLocation;
    private int maxNumItem = 20;
    private int health;
    private int score;
    private TextView scoreText;
    private ImageView bag;
    private ViewGroup root;
    private ProgressBar time;
    private Handler handler;
    private CollectCoinPresenter presenter;
    long lastTime;

    private ArrayList<ImageView> dropItems;
    private ArrayList<ImageView> healths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dropItems = new ArrayList<>();
        healths = new ArrayList<>();
        handler = new Handler();
        score = 0;
        health = 3;
        lastTime = System.currentTimeMillis();
        presenter = new CollectCoinPresenter(this);
        initView();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        presenter.updateGraph();
                    }
                });
            }
        }, 0, 10);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_collect_coin;
    }

    @Override
    protected boolean setSavable() {
        return false;
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    bagLastLocation = (int) event.getRawX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int now = (int) event.getRawX();
                    bagLocation += now - bagLastLocation;
                    bagLastLocation = now;
                    break;
                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;
            }
            return true;
        }
    }


    public int getBagLocation() {
        return bagLocation;
    }

    public int getBagHeight() {
        return bagHeight;
    }

    public int getMaxNumItem() {
        return maxNumItem;
    }

    private void initView() {
        root = findViewById(R.id.root);
        bag = findViewById(R.id.bag);
        root.setOnTouchListener(new ChoiceTouchListener());
        for (int i = 0; i < maxNumItem; i++) {
            ImageView imageView = new ImageView(this);
            dropItems.add(imageView);
            imageView.setImageResource(R.drawable.coin1);
            imageView.setVisibility(View.GONE);
            root.addView(imageView);
        }
        for (int i = 0; i < health; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.heart1);
            imageView.setX(0);
            imageView.setX(650 + 130 * i);
            healths.add(imageView);
            root.addView(imageView);
        }
        bag.setX(bagLastLocation);
        bag.setY(bagHeight);
        time = findViewById(R.id.progressBar);
        time.setX(10);
        time.setY(10);
        scoreText = new TextView(this);
        scoreText.setX(0);
        scoreText.setY(0);
        scoreText.setTextColor(Color.BLACK);
        scoreText.setTextSize(30);
        root.addView(scoreText);
        String scoreDisplay = "Score: ";
        scoreText.setText(scoreDisplay.concat(Integer.toString(score)));
    }

    public void updateGraph(ArrayList<DropItems> items) {
        updateProgressBar();
        bag.setX(bagLocation);
        int i = 0;
        for (DropItems item : items) {

            ImageView itemView = dropItems.get(i);
            itemView.setX(item.getLocationX());
            itemView.setY(item.getLocationY());

            if (item.getType().equals("coin")) {
                itemView.setImageResource(R.drawable.coin1);
            } else if (item.getType().equals("bomb")) {
                itemView.setImageResource(R.drawable.shit);
            } else if (item.getType().equals("health")) {
                itemView.setImageResource(R.drawable.health);
            }
            itemView.setVisibility(View.VISIBLE);
            i++;
        }
        while (i < maxNumItem) {
            dropItems.get(i).setVisibility(View.GONE);
            i++;
        }
    }

    public void updateProgressBar() {
        long curr = System.currentTimeMillis();
        time.setProgress((int) ((curr - lastTime) / 300));
    }

    public void increaseHealth() {
        if (health < 3) {
            healths.get(health).setImageResource(R.drawable.heart1);
            health += 1;
        }
    }

    public void decreaseHealth() {
        if (health > 0) {
            healths.get(health - 1).setImageResource(R.drawable.heart2);
            health -= 1;
        }
    }

    public void increaseScore() {
        score += 1;
        String scoreDisplay = "Score: ";
        scoreText.setText(scoreDisplay.concat(Integer.toString(score)));
    }


}
