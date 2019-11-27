package com.example.uoftlife;

class GameSleepModel {
    /**
     * The target number of click to pass the game level.
     */
    private static int targetClick = 45;

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
     * Creates a GameSleepModel instance.
     */
    GameSleepModel() {

    }

    /**
     * @return the target number of clicks in this game level.
     */
    static int getTargetClick() {
        return targetClick;
    }

    /**
     * Sets the target number of clicks in this game level.
     */
    static void setTargetClick(int t) {
        GameSleepModel.targetClick = t;
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

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }
}


