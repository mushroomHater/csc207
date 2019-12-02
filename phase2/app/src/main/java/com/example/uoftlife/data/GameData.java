package com.example.uoftlife.data;

import android.content.Context;


// because of the facade the implementation concrete classes of this interface can
// be package-private and not exposed to external
interface GameData {

    void initialize();

    boolean setValue(String key, int value) throws IllegalArgumentException;

    // return -1 if not valid;
    // otherwise the value need to be positive integer in this game
    int getValue(String key);

    boolean save(Context context);

    void load(Context context);

    void clearFile(Context context);

}
