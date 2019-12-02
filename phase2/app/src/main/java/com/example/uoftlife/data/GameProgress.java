package com.example.uoftlife.data;

import android.content.Context;

import com.example.uoftlife.util.DataPersistence;

import java.util.HashMap;
import java.util.Map;


// singleton class
class GameProgress implements GameData {
    private static final GameProgress progress = new GameProgress();

    private HashMap<String, Integer> progressData;

    private HashMap<String, String> tempData = new HashMap<>();

    private String fileName = GameConstants.STATUS_FILE;

    static GameProgress getProgress() {
        return progress;
    }

    private GameProgress() {
        initialize();
    }

    void setUserName(String userName) {
        fileName = GameConstants.CONFIG_FILE + "_" + userName;
    }

    @Override
    public void initialize() {
        progressData = new HashMap<>();
        for (Map.Entry<String, Integer> kvEntry : GameConstants.GAME_STATUS_INIT.entrySet()) {
            progressData.put(kvEntry.getKey(), kvEntry.getValue());
        }
    }

    @Override
    public boolean setValue(String key, int value) {
        if (value >= 0 && key != null &&
                GameConstants.GAME_STATUS_INIT.keySet().contains(key)) {
            progressData.put(key, value);
            return true;
        } else {
            return false;
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
        return DataPersistence.saveMapData(progressData, context, fileName);
    }

    @Override
    public void load(Context context) {
        progressData = DataPersistence.readMapData(context, fileName);
    }

    @Override
    public void clearFile(Context context) {
        DataPersistence.clearSPData(context, fileName);
    }

    void setTempData(String key, String value) {
        tempData.put(key, value);
    }

    String getTempData(String key) {
        return tempData.get(key);
    }
}
