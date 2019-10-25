package com.example.uoftlife;

class GameConfiguration {
    /**
     * The difficulty level of the game ranging from 1 - 3
     * 1 represents easy, 2 represents medium, 3 represents hard
     */
    private byte difficulty;

    // test comment


    /**
     * The language setting of the game,
     * can only be "English" or "Chinese".
     */
    private String language;


    /**
     * The display mode of the game.
     * true represents nightMode on, false represents nightMode off.
     */
    private boolean nightMode;


    /**
     * Generates a game configuration.
     *
     * @param difficulty the difficulty of the game
     * @param language   the language setting of the game
     * @param nightMode  the display mode of the game
     */
    public GameConfiguration(byte difficulty, String language, boolean nightMode) {
        this.difficulty = difficulty;
        this.language = language;
        this.nightMode = nightMode;
    }


    /**
     * @return the difficulty level of the game.
     */
    byte getDifficulty() {
        return difficulty;
    }


    /**
     * @return the language setting of the game
     */
    String getLanguage() {
        return language;
    }


    /**
     * @return true if the game is on nightMode, otherwise return false
     */
    boolean isNightMode() {
        return nightMode;
    }

}
