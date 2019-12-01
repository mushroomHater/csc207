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

    private int health;

    private int score;

    private Iterator<String> riddleIterator;

    private Map<String, String> riddles;

    private int scoreAmount;

    private Map<String, String> riddlesEazy = new HashMap<String, String>(){{
        put("8/2(2+2) = ?", "16");
        put("0, 1, 3, 9, 33, ( ? )","153");
        put("2, 2, 8, -1, -2, 5, 1, 1, 2, -1, 1, (?) ", "2");
        put("204, 180, 12, 84, -36, (?) ", "60");
        put("2, 12, 30, (?)","56");
        put("-7, 0, 1, 2, 9","28");
        put("8 * ? = ? * 2 * ?, ? = ___", "4");
        put("20, 22, 25, 30, 37", "48");
        put("1, 3, 4, 8, 16, (?)", "32");
        put("1, 1, 3, 7, 17, 41, (?)", "99");
    }};

    private Map<String, String> riddlesMiddle = new HashMap<String, String>(){{
        put("1/2, 1, 1, (?), 9/11, 11/13","1");
        put("1, 7, 8, 57, (?)", "121");
        put("82, 98, 102, 118, 62, 138, (?)", "82");
        put("4, 12, 8, 10, (?)", "9");
        put("4, 2, 2, 3, 6, (?)", "15");
        put("12, 9 18, 33, 96, 21, 36, (?)", "51");
        put("3, 4, 8, 24, 88, (?）","344");

    }};

    private Map<String, String> riddlesHard = new HashMap<String, String>(){{
        put("1, 2, 8, 28, (?)", "100");
        put("0, 4, 18, (?), 100","48");
        put("1, 2, 5, 29, (?)","866");
        put("2, 5, 14, 29, 86, (?)", "173");
        put("2, 6, 13, 39, 15, 45, 23, (?)", "69");
        put("95, 88, 71, 61, 50, (?)","40");
        put("6, 15, 32, 77, (?)", "163");
        put("3, 10, 29, 66, (?)","127");
    }};
    private String currentRiddle;

    LevelThree(int score) {
        this.config = GameConfiguration.getConfig();
        if (config.getDifficulty() == 1){
            riddles = riddlesEazy;
            scoreAmount = 4;
        }
        else if (config.getDifficulty() == 2){
            riddles = riddlesMiddle;
            scoreAmount = 6;
        }
        else {
            riddles = riddlesHard;
            scoreAmount = 10;
        }
        this.score = score;
        List<String> riddleKeys = new ArrayList<>(riddles.keySet());
        Collections.shuffle(riddleKeys);
        riddleIterator = riddleKeys.iterator();
        health = 3;
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

    GameConfiguration getConfig() {
        return config;
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

    int getHealth() {
        return health;
    }

    /**
     *  add or delete the score of the player.
     */
    void updateScore(){
        score += scoreAmount;
    }

    int getScore(){
        return score;
    }

    void updateHealth(){
        health -= 1;
    }
}
