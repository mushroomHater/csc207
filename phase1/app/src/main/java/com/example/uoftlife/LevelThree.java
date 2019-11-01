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

    //TODO: change is back to private
    Map<String, String> riddles = new HashMap<String, String>(){{
        put("The more you take, the more you leave behind. What am I?", "footsteps");
        put("What has a head, a tail, is brown, and has no legs?", "penny");
        put("David's father has three sons : Snap, Crackle and _____ ?", "David");
        put("What room do ghosts avoid", "living room");
        put("What belongs to you, but other people use it more than you", "name");
        put("What is more useful when it is broken", "egg");

    }};

    private String currentRiddle;

    // TODO: Add parameter config later
    public LevelThree(Score score) {
//        initialize(config);
        this.score = score;
        mapIterator = riddles.entrySet().iterator();
    }

    String getCurrentRiddle() {
        if (mapIterator.hasNext())
            currentRiddle = (String) ((Map.Entry) mapIterator.next()).getKey();
        return currentRiddle;
    }

    @Override
    public int getScore() {
        return score.getScore();
    }

    @Override
    public boolean isPassed() {
        return false;
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

    boolean checkIfMatch(String answer){
        if ((riddles.get(currentRiddle)).equals(answer)){
            return true;
        }
        else return false;
    }
    void updateScore(int amount){
        score.addScore(amount);
    }
}
