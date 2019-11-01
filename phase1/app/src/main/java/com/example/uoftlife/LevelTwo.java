package com.example.uoftlife;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public abstract class LevelTwo extends AppCompatActivity {

    /**
     * The goal of this game
     */
    static private int goal = 20;

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
    static int min_step = 1;

    /**
     * the maximum step for each round
     */
    static int max_step = 2;

    /**
     * current number of this game
     */
    static int curr_step = 0;

    /**
     * current round number
     */
    private int round_num;

    /**
     * player 1 (computer)  of this game
     */
    private LevelTwoPlayerAI AI;

    /**
     * the list of players in this game
     */

    /**
     * indicate whose turn it is
     */
    private int turn;

    static int get_min(){return min_step;}
    static int get_max(){return max_step;}
    static int get_goal(){return goal;}

    public LevelTwo(){
        this.curr_step = 0;
        this.round_num = 0;
    }


    static void addstep(int n){
        curr_step += n;
    }

    static int AI_move(){
        int i = min_step + (int) (Math.random() * ((
                max_step - min_step) + 1));
        return i;
    }




}
