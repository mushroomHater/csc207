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
     * The click amount of the user
     */
    private int clickAmount = 0;

    /**
     * The GameConfiguration instance passed into the game level.
     */
    private GameConfiguration config;


    /**
     * Creates a LevelOne instance.
     */
    LevelOneState() {

    }

    /**
     * @return the number of clicks a user has entered.
     */
    int getClickAmount() {
        return clickAmount;
    }

    /**
     * Sets the number of clicks.
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

    public int getScore() {
        if (isPassed()) {
            if (clickAmount >= TARGETCLICK &&
                    clickAmount < TARGETCLICK * 1.2) {
                return 60;
            } else if (clickAmount >= TARGETCLICK * 1.2 &&
                    clickAmount < TARGETCLICK * 1.3) {
                return 70;
            } else if (clickAmount >= TARGETCLICK * 1.3 &&
                    clickAmount < TARGETCLICK * 1.4) {
                return 80;
            } else if (clickAmount >= TARGETCLICK * 1.4 &&
                    clickAmount < TARGETCLICK * 1.5) {
                return 90;
            } else if (clickAmount >= TARGETCLICK * 1.5) {
                return 100;
            }
        }
        return 0;
    }

    /**
     * Checks if the user passes the game level.
     *
     * @return true if the player passes the game level, otherwise return false.
     */
    public boolean isPassed() {
        return this.getClickAmount() >= TARGETCLICK;
    }

}
