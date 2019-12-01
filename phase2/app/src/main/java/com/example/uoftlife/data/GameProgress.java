package com.example.uoftlife.data;

import android.content.Context;

import com.example.uoftlife.util.DataPersistence;

import java.util.HashMap;
import java.util.Map;


// singleton class
class GameProgress implements GameData {
    private static final GameProgress progress = new GameProgress();

    private HashMap<String, Integer> progressData;


    static GameProgress getProgress() {
        return progress;
    }

    private GameProgress() {
        initialize();
    }

    @Override
    public void initialize() {
        progressData = new HashMap<>();
        for (Map.Entry<String, Integer> kvEntry : GameConstants.GAME_STATUS_INIT.entrySet()) {
            progressData.put(kvEntry.getKey(), kvEntry.getValue());
        }
    }

    @Override
    public void setValue(String key, int value) throws IllegalArgumentException {
        if (value >= 0 && key != null &&
                GameConstants.GAME_STATUS_INIT.keySet().contains(key)) {
            progressData.put(key, value);
        } else {
            throw new IllegalArgumentException();
        }
    }


    // if doesn't exist then return -1
    @Override
    public int getValue(String key) {
        Integer i = progressData.getOrDefault(key, -1);
        return i == null ? -1 : i;
    }

    @Override
    public boolean save(Context context) {
        return DataPersistence.saveMapData(progressData, context, GameConstants.STATUS_FILE);
    }

    @Override
    public void load(Context context) {
        progressData = DataPersistence.readMapData(context, GameConstants.STATUS_FILE);
    }
}