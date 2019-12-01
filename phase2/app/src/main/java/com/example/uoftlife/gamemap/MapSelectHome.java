package com.example.uoftlife.gamemap;

import android.app.Activity;
import android.graphics.Rect;

public class MapSelectHome extends BuildingView {
    public MapSelectHome(Activity a) {
        super(a);
    }

    //check if the clicked location is an available building
    protected boolean checkAvailableHome(float x, float y) {
        for (Rect r : super.allRectangle) {
            if (r.contains((int) x, (int) y) && checkIfSchool(x, y) && checkIfShoppingMall(x, y)) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkIfSchool(float x, float y) {
        return x != super.schoolLocationX && y != super.schoolLocationY;
    }

    protected boolean checkIfShoppingMall(float x, float y) {
        return x != super.shoppingMallLocationX && y != super.shoppingMallLocationY;
    }
}