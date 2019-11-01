package com.example.uoftlife;

import android.os.CountDownTimer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class LevelThree implements Terminable{

    private GameConfiguration config;

    private Score score;

    private Iterator mapIterator;

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
    LevelThree(Score score) {
//        initialize(config);
        this.score = score;
        mapIterator = riddles.entrySet().iterator();
    }

    /**
     * get which riddle is the player gueesting right now
     * @return the current riddle
     */
    String getCurrentRiddle() {
        if (mapIterator.hasNext())
            currentRiddle = (String) ((Map.Entry) mapIterator.next()).getKey();
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
        score.addScore(amount);
    }
}
