package com.example.uoftlife.floating;

import com.example.uoftlife.GameBaseActivity;
import com.example.uoftlife.MainActivity;
import com.example.uoftlife.R;

public class SchoolEventActivity extends EventSelectActivity {

    @Override
    protected void dynamicCreateView() {
        addButtonWithIntent(R.string.lecture, MainActivity.class);
        addButtonWithIntent(R.string.review, GameBaseActivity.class);
    }
}
