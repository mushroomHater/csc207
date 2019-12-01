package com.example.uoftlife.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class DataPersistence {
    private DataPersistence() {
    }

    public static void clearSPData(Context context, String fileName) throws NullPointerException {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();
        } else {
            throw new NullPointerException();
        }
    }

    public static HashMap<String, Integer> readMapData(Context context, String fileName) throws NullPointerException {
        if (context != null) {
            HashMap<String, Integer> map = new HashMap<>();
            SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            Map<String, ?> allContents = sharedPreferences.getAll();
            for (Map.Entry<String, ?> kvEntry : allContents.entrySet()) {
                if (kvEntry.getValue() instanceof Integer) {
                    map.put(kvEntry.getKey(), (Integer) kvEntry.getValue());
                }
            }
            return map;
        } else {
            throw new NullPointerException();
        }
    }

    public static boolean saveMapData(Map<String, Integer> map, Context context, String fileName) throws NullPointerException {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for (Map.Entry<String, Integer> kvEntry : map.entrySet()) {
                editor.putInt(kvEntry.getKey(), kvEntry.getValue());
            }
            return editor.commit();
        } else {
            throw new NullPointerException();
        }
    }
}
