package com.example.uoftlife;
import java.util.Scanner;
public class LevelTwoPlayerAI extends LevelTwoPlayer {

    /**
     how many steps a character moves each round, when the min_step is always 1
     */
    protected void move(int min_step, int max_step, int curr_step) {

    }


    static int leveleasy(int min_step, int max_step, int curr_step) {
        int i = min_step + (int) (Math.random() * ((
                max_step - min_step) + 1));
        return i;

    }

    static int levelmid(int min_step, int max_step, int curr_step, int goal) {
        double i = Math.random();
        int strategic_step = levelhard(min_step,max_step,curr_step,goal);
        int random_step = leveleasy(min_step,max_step,curr_step);
        if (i <= 0.5) {
            return random_step;
        }
        else{
            return strategic_step;
        }
    }

    static int levelhard(int min_step, int max_step, int curr_step, int goal) {
        if ((goal - curr_step) % (max_step + min_step) == 0){
            return leveleasy(min_step, max_step, curr_step);}
        else return (goal - curr_step) % (max_step + min_step);
    }
}





