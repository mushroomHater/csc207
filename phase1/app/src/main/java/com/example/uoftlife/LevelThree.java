package com.example.uoftlife;

import android.os.CountDownTimer;

import java.util.List;

class LevelThree implements Terminable{
    private int health;

    private GameConfiguration config;

    private Score score;

    private List<String> wordList;

    private int currentIndex;

    private int timeLimitForTyping;

    // TODO: Add parameter config later
    public LevelThree(Score score) {
//        initialize(config);
//        if (config.getDifficulty() == 0) health = 100;
//        else if (config.getDifficulty() == 1)  health = 90;
//        else health = 80;
        health = 60;
        this.score = score;
        timeLimitForTyping = 5;
        wordList.add("one");
        wordList.add("two");
        wordList.add("three");
        wordList.add("four");
        wordList.add("five");
        wordList.add("six");
        wordList.add("seven");
        wordList.add("eight");
        wordList.add("nine");
        wordList.add("ten");
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

    void startHealthCouting() {
        new CountDownTimer(health * 1000, 2000) {

            public void onTick(long l) {
                health = (int) l / 1000;
            }

            public void onFinish() {
            }
        }.start();
    }

    void startTimeCountingForWord(){
        new CountDownTimer(timeLimitForTyping * 1000, 1000) {
            public void onTick(long l) {
                timeLimitForTyping = (int) l / 1000;
            }

            public void onFinish() {
            }
        }.start();
    }

    void restartTimeLimit(){
        currentIndex += 1;
        timeLimitForTyping = 5000;
    }

    void updateHealth(int amount){
        health += amount;
    }

    void cancelTimer(CountDownTimer timer) {
        if(timer!=null)
            timer.cancel();
    }

    String getCurrentWord(){
        return wordList.get(currentIndex);
    }

    void nextWord(){
        currentIndex += 1;
    }

    boolean checkIfMatch(String word){
        if (word.equals(this.getCurrentWord())){
            updateHealth(5);
            return true;
        }
        else return false;
    }
}
