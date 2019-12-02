package com.example.uoftlife.floating;

import com.example.uoftlife.R;
import com.example.uoftlife.gamacard.GameCardActivity;
import com.example.uoftlife.gamesleep.GameSleepActivity;

public class HomeEventActivity extends EventSelectActivity {

    @Override
    protected void dynamicCreateView() {
        addButtonWithIntent(R.string.review, GameCardActivity.class);
        addButtonWithIntent(R.string.sleep, GameSleepActivity.class);
    }
}
