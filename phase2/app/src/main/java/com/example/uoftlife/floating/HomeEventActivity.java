package com.example.uoftlife.floating;

import com.example.uoftlife.MainActivity;
import com.example.uoftlife.R;
import com.example.uoftlife.gamesleep.GameSleepActivity;

public class HomeEventActivity extends EventSelectActivity {

    @Override
    protected void dynamicCreateView() {
        addButtonWithIntent(R.string.review, MainActivity.class);
        addButtonWithIntent(R.string.sleep, GameSleepActivity.class);
    }
}
