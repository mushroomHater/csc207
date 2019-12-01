package com.example.uoftlife.gamemap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.example.uoftlife.data.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class BuildingView extends View {

    public List<Rect> allRectangle = new ArrayList<>();
    public final Paint buildingPaint = new Paint();


    public BuildingView(Context context) {
        super(context);
    }

    public BuildingView(Context context, int gridWidth, int gridHeight) {
        super(context);
        buildingPaint.setColor(0xff88bbaa);

        int drawX = 0;
        int drawY = 0;
        for (int x = 0; x < GameConstants.HORIZONTAL_GRIDS; x++) {
            drawX += gridWidth * GameConstants.ROAD_WIDTH;
            for (int y = 0; y < GameConstants.VERTICAL_GRIDS; y++) {
                drawY += gridHeight * GameConstants.ROAD_WIDTH;
                allRectangle.add(new Rect(drawX, drawY, drawX + gridWidth, drawY + gridHeight));
                drawY += gridHeight;
            }
            drawY = 0;
            drawX += gridWidth;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);
        for (Rect rect : allRectangle) {
            canvas.drawRect(rect, buildingPaint);
        }
    }


}





