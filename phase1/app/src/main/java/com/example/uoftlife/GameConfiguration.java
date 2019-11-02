package com.example.uoftlife;

class GameConfiguration {
    /**
     * The difficulty level of the game ranging from 1 - 3
     * 1 represents easy, 2 represents medium, 3 represents hard
     */
    private byte difficulty;

    /**
     * The language setting of the game,
     * can only be "English" or "Chinese".
     */
    private String language;

    private static GameConfiguration config = null;

    /**
     * Generates a game configuration.
     *
     * @param difficulty the difficulty of the game
     * @param language   the language setting of the game
     */
    private GameConfiguration(byte difficulty, String language) {
        this.difficulty = difficulty;
        this.language = language;
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

    void setDifficulty(byte d){
        this.difficulty = d;
    }

    void setLanguage(String l){
        this.language = l;
    }

    static GameConfiguration getConfig(){
        if(config==null){
            config = new GameConfiguration((byte)2,"English");
        }
        return config;
    }
}
