package com.example.uoftlife;


class LevelOneState {

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
     * Creates a LevelOne instance.
     */
    LevelOneState() {

    }

    /**
     * @return the target number of clicks in this game level.
     */
    static int getTargetClick() {
        return targetClick;
    }

    /**
     * @return the number of clicks a user has entered.
     */
    int getClickAmount() {
        return clickAmount;
    }

    public static void setTargetClick(int t) {
        LevelOneState.targetClick = t;
    }

    /**
     * Adds the number of clicks.
     */
    void addClickAmount() {
        clickAmount++;
    }


    /**
     * @return the score of the user within the range of 0 - 100.
     * If the user passes the game level, return a score within the range 1 - 100,
     * depending on the performance/ click amount of the user.
     * If the user fails the game level, return 0.
     */

    int getScore() {
        if (isPassed()) {
            if (clickAmount >= targetClick &&
                    clickAmount < targetClick * 1.2) {
                score = 60;
            } else if (clickAmount >= targetClick * 1.2 &&
                    clickAmount < targetClick * 1.3) {
                score = 70;
            } else if (clickAmount >= targetClick * 1.3 &&
                    clickAmount < targetClick * 1.4) {
                score = 80;
            } else if (clickAmount >= targetClick * 1.4 &&
                    clickAmount < targetClick * 1.5) {
                score = 90;
            } else if (clickAmount >= targetClick * 1.5) {
                score = 100;
            }
        } else {
            score = 0;
        }
        return score;
    }

    /**
     * Checks if the user passes the game level.
     *
     * @return true if the player passes the game level, otherwise return false.
     */
    private boolean isPassed() {
        return this.getClickAmount() >= targetClick;
    }

}
