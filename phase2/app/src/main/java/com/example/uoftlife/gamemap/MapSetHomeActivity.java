package com.example.uoftlife.gamemap;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.uoftlife.R;

public class MapSetHomeActivity extends MapActivity {

    // will be stored in user data after connecting the interfaces
    // take these fields as placeholder
    private int homeX = 300;
    private int homeY = 300;

    public MapSetHomeActivity(Activity activity) {
        super(activity);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_map);
        changeHomeButtonLocation();
    }


    //change home button to the target position
    public void changeHomeButtonLocation() {
        MapSelectHome msh = new MapSelectHome(this);
        if (msh.checkAvailableHome(homeX, homeY)) {
            Button homeButton = findViewById(R.id.home);
            homeButton.setX(homeX);
            homeButton.setY(homeY);
        }
    }
}
