package com.example.uoftlife.data;

import android.content.Context;

import com.example.uoftlife.util.DataPersistence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// singleton class
// todo save the user name+ image in user.class because they are not integer that need
//  extra method or classes to IO that may cause duplicate codes

// todo ? may need cleaning the existing shared-preference

/**
 * GameProcessorStrategyDecorator ........
 * todo a list of decorator that represents the characteristics
 * develop later after the frame is built
 */

// store a context reference in this class that the context can be reached everywhere outside activity
// always need to initialize this context at start of app

class GameConfiguration implements GameData {
    private static final GameConfiguration configuration = new GameConfiguration();

    private HashMap<String, Integer> innatePointsAllocation;

    private Context appContext;

    private GameConfiguration() {
        initialize();
    }

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

    private boolean isRepeatedCoordinates(int x, int y) {
        for (String key : GameConstants.MAP_BUILDINGS) {
            if (getValue(key + "_x") == x && getValue(key + "_y") == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValue(String key, int value) throws IllegalArgumentException {
        if (key != null && value >= 0) {
            List<String> lst = Arrays.asList(GameConstants.INNATE_ATTRIBUTES);
            if (lst.contains(key)) {
                innatePointsAllocation.put(key, value);
                return;
            }
        }
        throw new IllegalArgumentException();
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
        return DataPersistence.saveMapData(innatePointsAllocation, context, GameConstants.CONFIG_FILE);
    }

    @Override
    public void load(Context context) throws NullPointerException {
        innatePointsAllocation = DataPersistence.readMapData(context, GameConstants.CONFIG_FILE);
    }


}
