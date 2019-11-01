package com.example.uoftlife;


class LevelOneState {

    /**
     * The target number of click to pass the game level.
     */
    private static int TARGETCLICK = 45;

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
    static int getTARGETCLICK() {
        return TARGETCLICK;
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
     * Sets the number of clicks.
     */
    void setClickAmount(int i) {
        clickAmount = i;
    }


    /**
     * @return the score of the user within the range of 0 - 100.
     * If the user passes the game level, return a score within the range 1 - 100,
     * depending on the performance/ click amount of the user.
     * If the user fails the game level, return 0.
     */

    public int getScore() {
        if (isPassed()) {
            if (clickAmount >= TARGETCLICK &&
                    clickAmount < TARGETCLICK * 1.2) {
                score = 60;
            } else if (clickAmount >= TARGETCLICK * 1.2 &&
                    clickAmount < TARGETCLICK * 1.3) {
                score = 70;
            } else if (clickAmount >= TARGETCLICK * 1.3 &&
                    clickAmount < TARGETCLICK * 1.4) {
                score = 80;
            } else if (clickAmount >= TARGETCLICK * 1.4 &&
                    clickAmount < TARGETCLICK * 1.5) {
                score = 90;
            } else if (clickAmount >= TARGETCLICK * 1.5) {
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
        return this.getClickAmount() >= TARGETCLICK;
    }

}
