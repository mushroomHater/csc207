package com.example.uoftlife.data;

import android.content.Context;

import com.example.uoftlife.util.DataPersistence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


// store a context reference in this class that the context can be reached everywhere outside activity
// always need to initialize this context at start of app

class GameConfiguration implements GameData {
    private static final GameConfiguration configuration = new GameConfiguration();

    private HashMap<String, Integer> innatePointsAllocation;

    private Context appContext;

    private GameConfiguration() {
        initialize();
    }

    private String fileName = GameConstants.CONFIG_FILE;

    static GameConfiguration configure() {
        return configuration;
    }

    void setAppContext(Context appContext) {
        this.appContext = appContext;
    }

    Context getAppContext() {
        return appContext;
    }

    @Override
    public void initialize() {
        innatePointsAllocation = new HashMap<>();
        for (String key : GameConstants.INNATE_ATTRIBUTES) {
            innatePointsAllocation.put(key, 0);
        }
        for (String key : GameConstants.MAP_BUILDINGS) {
            int x, y;
            do {
                x = (int) (GameConstants.HORIZONTAL_GRIDS * Math.random());
                y = (int) (GameConstants.VERTICAL_GRIDS * Math.random());
            } while (isRepeatedCoordinates(x, y));
            innatePointsAllocation.put(key + "_x", x);
            innatePointsAllocation.put(key + "_y", y);
        }
    }

    void setUserName(String userName) {
        fileName = GameConstants.CONFIG_FILE + "_" + userName;
    }

    private boolean isRepeatedCoordinates(int x, int y) {
        for (String key : GameConstants.MAP_BUILDINGS) {
            if (getValue(key + "_x") == x && getValue(key + "_y") == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setValue(String key, int value) {
        if (key != null && value >= 0) {
            List<String> lst = Arrays.asList(GameConstants.INNATE_ATTRIBUTES);
            if (lst.contains(key)) {
                innatePointsAllocation.put(key, value);
                return true;
            }
        }
        return false;
    }

    @Override
    public int getValue(String key) {
        if (key != null) {
            Integer i = innatePointsAllocation.get(key);
            return i == null ? -1 : i;
        }
        return -1;
    }

    @Override
    public boolean save(Context context) throws NullPointerException {
        return DataPersistence.saveMapData(innatePointsAllocation, context, fileName);
    }

    @Override
    public void load(Context context) throws NullPointerException {
        innatePointsAllocation = DataPersistence.readMapData(context, fileName);
    }

    @Override
    public void clearFile(Context context) {
        DataPersistence.clearSPData(context, fileName);
    }


}
