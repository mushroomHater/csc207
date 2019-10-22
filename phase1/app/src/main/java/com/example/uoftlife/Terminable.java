package com.example.uoftlife;

import android.content.res.Configuration;

interface Terminable {

    /**
     * Gets the score in each game level according to the performance of the user and the
     * difficulty setting.
     * Will only be called after the game ends.
     *
     * @return the score of the user within the range of 0 - 100.
     * If the user passes the game level, return a score within the range 1 - 100.
     * If the user fails the game level, return 0.
     */
    int getScore();


    /**
     * Checks if the user passes the game level.
     * Will only be called after the game ends.
     *
     * @return true if the player passes the game level, otherwise return false.
     */
    boolean isPassed();


    /**
     * Updates the game level.
     *
     * @return true if the game level ends, otherwise return false.
     */
    boolean update();


    /**
     * Passes the configurations in GameConfiguration into the game level.
     * The classes that implement this interface should have a field to store it.
     */
    void initialize(GameConfiguration configure);

    /**
     * Clears the data and objects displayed on the screen of the game level.
     * It will be called once the game ended.
     */
    void clear();


}


