package com.example.uoftlife.floating;

import com.example.uoftlife.R;
import com.example.uoftlife.gamacard.GameCardActivity;
import com.example.uoftlife.gamestudy.GameStudyActivity;

public class SchoolEventActivity extends EventSelectActivity {

    @Override
    protected void dynamicCreateView() {
        addButtonWithIntent(R.string.lecture, GameStudyActivity.class);
        addButtonWithIntent(R.string.review, GameCardActivity.class);
    }
}
