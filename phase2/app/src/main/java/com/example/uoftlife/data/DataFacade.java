package com.example.uoftlife.data;

import android.content.Context;

public class DataFacade {
    // this is a tool with no fields and no need to instantiate

    /**
     * Private constructor that avoids being instantiated
     */
    private DataFacade() {
    }

    static public boolean addToValue(String key, int value) {
        int i = getValue(key);
        if (i != -1) {
            return setValue(key, i + value);
        } else {
            return false;
        }
    }

    static public boolean setValue(String key, int value) {
        if (!GameProgress.getProgress().setValue(key, value)) {
            return GameConfiguration.configure().setValue(key, value);
        }
        return true;
    }

    static public void setUserName(String userName) {
        GameProgress.getProgress().setUserName(userName);
        GameConfiguration.configure().setUserName(userName);
    }

    static public int getValue(String key) {
        int v = GameProgress.getProgress().getValue(key);
        if (v == -1) {
            v = GameConfiguration.configure().getValue(key);
        }
        return v;
    }

    static private boolean save(GameData data) {
        try {
            return data.save(getContext());
        } catch (NullPointerException e) {
            return false;
        }
    }

    static private boolean load(GameData data) {
        try {
            data.load(getContext());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static public void setTempData(String key, String value) {
        GameProgress.getProgress().setTempData(key, value);
    }

    static public String getTempData(String key) {
        return GameProgress.getProgress().getTempData(key);
    }

    static public boolean saveConfig() {
        return save(GameConfiguration.configure());
    }

    static public boolean saveProgress() {
        return save(GameProgress.getProgress());
    }

    static public boolean loadConfig() {
        return load(GameConfiguration.configure());
    }

    static public boolean loadProgress() {
        return load(GameProgress.getProgress());
    }

    static public void clearFile() {
        GameConfiguration.configure().clearFile(getContext());
        GameProgress.getProgress().clearFile(getContext());
    }

    static public void initialize() {
        GameConfiguration.configure().initialize();
        GameProgress.getProgress().initialize();
    }

    static public void setContext(Context context) {
        GameConfiguration.configure().setAppContext(context);
    }

    static public Context getContext() {
        return GameConfiguration.configure().getAppContext();
    }

}
