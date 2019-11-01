package com.example.uoftlife;

import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class LevelThree{

    private GameConfiguration config;

    private int score;

    private Iterator<String> riddleIterator;

    private List<String> riddleKeys;

    private Map<String, String> riddles = new HashMap<String, String>(){{
        put("The more you take, the more you leave behind. What am I?", "footsteps");
        put("What has a head, a tail, is brown, and has no legs?", "penny");
        put("David's father has three sons : Snap, Crackle and _____ ?", "David");
        put("What room do ghosts avoid", "living room");
        put("What belongs to you, but other people use it more than you", "name");
        put("What is more useful when it is broken", "egg");

    }};

    private String currentRiddle;

    // TODO: Add parameter config later
    LevelThree(int score) {
//        initialize(config);
        this.score = score;
        riddleKeys = new ArrayList<>(riddles.keySet());
        Collections.shuffle(riddleKeys);
        riddleIterator = riddleKeys.iterator();
    }

    /**
     * get which riddle is the player gueesting right now
     * @return the current riddle
     */
    String getCurrentRiddle() {
        if (riddleIterator.hasNext())
            currentRiddle = riddleIterator.next();
        return currentRiddle;
    }


    /**
     * check if the player's guess matches the actual answer.
     * @param answer
     * @return true if matches false otherwise.
     */
    boolean checkIfMatch(String answer){
        if ((riddles.get(currentRiddle)).equals(answer)){
            return true;
        }
        else return false;
    }

    /**
     *  add or delete the score of the player.
     * @param amount
     */
    void updateScore(int amount){
        score += amount;
    }

    int getScore(){
        return score;
    }
}
