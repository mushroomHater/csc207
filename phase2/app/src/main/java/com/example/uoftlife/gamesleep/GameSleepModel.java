package com.example.uoftlife.gamesleep;

import android.os.CountDownTimer;

import java.util.Random;

class GameSleepModel {
    /**
     * The target number of click to pass the game level.
     */
    private int targetClick = 45;

    /**
     * The tag of the game level.
     */
    private static final String TAG = "LevelOne";

    /**
     * The number of clicks entered by the user.
     */
    private int clickAmount = 0;

    /**
     * The score the user gets in this game level
     */
    private int score;

    /**
     * The timer of the game.
     */
    private CountDownTimer timer;

    /**
     * Indicates if the timer is running.
     */
    private boolean timing;


    /**
     * The time left in this game level in milliseconds.
     */
    private long timeLeftInMilliseconds = 16000; // 16 seconds

    /**
     * The x coordinate of the alarm button.
     */
    private float windowWidth;

    /**
     * The y coordinate of the alarm button.
     */
    private float windowHeight;

    /**
     * The time interval for the button to change position.
     */
    private int alarmChangePositionInterval = 4000;


    /**
     * Creates a LevelOne instance.
     */
    GameSleepModel(float windowWidth, float windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    /**
     * @return the target number of clicks in this game level.
     */
    int getTargetClick() {
        return targetClick;
    }

    /**
     * Sets the target number of clicks in this game level.
     */
    void setTargetClick(int targetClick) {
        this.targetClick = targetClick;
    }

    /**
     * @return the number of clicks a user has entered.
     */
    int getClickAmount() {
        return clickAmount;
    }

    /**
     * Adds the number of clicks.
     */
    void addClickAmount() {
        clickAmount++;
    }

    /**
     * @return the score of the game level.
     */
    int getScore() {
        return score;
    }

    /**
     * Sets the score of the game level.
     */
    void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the if the timer is timing.
     */
    boolean isTiming() {
        return timing;
    }

    /**
     * Sets if the timer should be timing.
     */
    void setTiming(boolean timing) {
        this.timing = timing;
    }

    void setTimer(CountDownTimer timer) {
        this.timer = timer;
    }

    void cancelTimer() {
        timer.cancel();

    }

    /**
     * @return the time left for the game level in milliseconds.
     */
    long getTimeLeftInMilliseconds() {
        return timeLeftInMilliseconds;
    }

    /**
     * Sets the time left for the game level in milliseconds.
     */
    void setTimeLeftInMilliseconds(long timeLeftInMilliseconds) {
        this.timeLeftInMilliseconds = timeLeftInMilliseconds;
    }

    int getAlarmChangePositionInterval() {
        return alarmChangePositionInterval;
    }

    void setAlarmChangePositionInterval(int alarmChangePositionInterval) {
        this.alarmChangePositionInterval = alarmChangePositionInterval;
    }

    /**
     * @return random location for the alarm button.
     */
    float[] generateRandomLocation() {
        Random R = new Random();
        final float windowWidth = R.nextFloat() * this.windowWidth;
        final float windowHeight = R.nextFloat() * this.windowHeight;
        return new float[]{windowWidth, windowHeight};
    }


}
