package com.example.uoftlife.LevelTwo;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.GameConfiguration;
import com.example.uoftlife.Terminable;

public class LevelTwo extends AppCompatActivity implements Terminable {

    /**
     * The goal of this game
     */
    private int Goal;

    /**
     * the tag of this game level
     */
    private static String TAG = "LevelTwo";

    /**
     * the current number textview of this level
     */
    private TextView goalText;

    /**
     * The GameConfiguration instance passed into the game level.
     */
    private GameConfiguration config;

    /**
     * the minimum step for each round
     */
    private static int min_step = 1;

    /**
     * the maximum step for each round
     */
    private static int max_step = 2;

    /**
     * current number of this game
     */
    private static int curr_num = 0;

    /**
     * player 1 (computer)  of this game
     */
    private PlayerAI AI;

    /**
     * player 2 (user) of this game
     */
    private PlayerUser User;

    /**
     * indicate whose turn it is
     */
    private static int turn = 0;



    protected void play(){
        if (curr_num < Goal){

        }
    }

}
