package com.example.uoftlife.data;

import com.example.uoftlife.util.calculator.GameProcessorStrategy;

import java.util.HashMap;
import java.util.Map;

public interface GameConstants {

    /**
     * Setting parameters
     */
    int HARD_POINTS = 20;
    int MEDIUM_POINTS = 32;
    int EASY_POINTS = 50;

    /**
     * Map drawer parameters
     */
    int HORIZONTAL_GRIDS = 4;
    int VERTICAL_GRIDS = 6;
    double ROAD_WIDTH = 0.2;


    /**
     * Max values for the below attributes
     */
    int MAX_TIME = 5000;
    int MAX_GENERAL = 100;


    /* Clarification:
     * Below String keys are the keys to save data, and the keys to read the texts that represents
     * on GUI from R.string file*/
    /**
     * The innate attributes that is assigned/set at the beginning of each turn of game
     */
    String[] INNATE_ATTRIBUTES = {
            "wealth", "stamina", "aspiration", "intellect", "fortune"
    };
    String[] MAP_BUILDINGS = {
            "school","home","mall"
    };
    String CONFIG_FILE = "game_config";

    /**
     * These sources key words are added in displaying order
     */
    String[] GAME_STATUS = {
            "money",
            "split line",
            "understanding","practice","mark",
            "split line",
            "repletion","vitality"
    };

    /**
     * The attributes that changes during game
     */
    Map<String, Integer> GAME_STATUS_INIT = new HashMap<String, Integer>() {{
        put("time", 5000);
        put("money", 52000);
        put("mark", 100);

        put("understanding", 0);
        put("practice", 0);
        put("vitality", 100);
        put("repletion", 100);
        put("health", 100);
        put("mood", 100);
        put("GPA", 4);
    }};
    String STATUS_FILE = "game_progress";




    GameProcessorStrategy<String, Integer> virtualPlaceholder = null;
    HashMap<String, GameProcessorStrategy<String, Integer>> GAME_CALCULATOR = new HashMap<String, GameProcessorStrategy<String, Integer>>() {{
        put("health", virtualPlaceholder);
        put("mood", virtualPlaceholder);
        put("GPA", virtualPlaceholder);
    }};

    // todo description of these characteristics effects and implement corresponding calculator/decorator
    // ..... to think, design, and improve it as more rigorous structure
    HashMap<String, GameProcessorStrategy<String, Integer>> CHARACTERISTICS = new HashMap<String, GameProcessorStrategy<String, Integer>>() {{
        put("procrastinator", virtualPlaceholder);
        put("optimist", virtualPlaceholder);
        put("unstable mood", virtualPlaceholder);
        put("insomnia", virtualPlaceholder);
        put("money-grubber", virtualPlaceholder);
    }};


    /**
     * Game channel configuration
     */
    String CHANNEL_ID = "gameEvents";
    String CHANNEL_NAME = "Assignment";
}
