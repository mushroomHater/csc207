package com.example.uoftlife;

import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class LevelThree implements Terminable{

    private GameConfiguration config;

    private Score score;

    private Iterator<String> riddleIterator;

    private List<String> riddleKeys;

    private Map<String, String> riddles = new HashMap<String, String>(){{
        put("The more you take, the more you leave behind. What am I?qweqweqweqweqweqweqwewqeqweqweqweqweqw", "footsteps");
        put("What has a head, a tail, is brown, and has no legs?", "penny");
        put("David's father has three sons : Snap, Crackle and _____ ?", "David");
        put("What room do ghosts avoid", "living room");
        put("What belongs to you, but other people use it more than you", "name");
        put("What is more useful when it is broken", "egg");
        put("8/2(2+2) = ?", "16");

    }};

    private String currentRiddle;

    // TODO: Add parameter config later
    LevelThree(Score score) {
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
     * get how many score does player have right now
     * @return the score
     */
    @Override
    public int getScore() {
        return score.getScore();
    }

    /**
     * check if the player passes this level
     * @return always return true.
     */
    @Override
    public boolean isPassed() {
        return true;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public void initialize(GameConfiguration configure) {
        this.config = configure;
    }

    @Override
    public void clear() {
    }


    /**
     * check if the player's guess matches the actual answer.
     * @param answer
     * @return true if matches false otherwise.
     */
    boolean checkIfMatch(String answer){
        if (getAnswer().equals(answer)){
            return true;
        }
        else return false;
    }

    String getAnswer(){
        return riddles.get(currentRiddle);
    }

    /**
     *  add or delete the score of the player.
     * @param amount
     */
    void updateScore(int amount){
        score.addScore(amount);
    }
}
