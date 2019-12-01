package com.example.uoftlife;

import android.widget.TextView;

//todo
public abstract class EventSelectActivity extends FloatingActivity {

    @Override
    protected int setContentLayout() {
        ((TextView) findViewById(R.id.title_message)).setText(String.format("You are now at %s",getIntent().getStringExtra("scene") ));
        return R.layout.activity_event_select;
    }
}
