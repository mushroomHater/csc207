package com.example.uoftlife.floating;

import com.example.uoftlife.MainActivity;
import com.example.uoftlife.R;

public class MallEventActivity extends EventSelectActivity {

    @Override
    protected void dynamicCreateView() {
        addButtonWithIntent(R.string.shopping, MainActivity.class);
        addButtonWithIntent(R.string.work, ConfigActivity.class);
    }
}
