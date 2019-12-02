package com.example.uoftlife.transpage;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;


public class CommonPageActivity extends TransitionPageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateAll();
    }

    private void updateAll() {
        List<String> keys = getIntent().getStringArrayListExtra("keys");
        List<Integer> values = getIntent().getIntegerArrayListExtra("values");
        if (keys != null && values != null && keys.size() == values.size()) {
            for (int i = 0; i < keys.size(); i++) {
                updateValue(keys.get(i), values.get(i));
            }
        }
    }

    @Override
    protected Intent nextActivity() {
        return null;
    }

    @Override
    protected String setTitleText() {
        return getIntent().getStringExtra("title");
    }

    @Override
    protected String setDescriptionText() {
        return getIntent().getStringExtra("description");
    }

    @Override
    protected int setShowLength() {
        return getIntent().getIntExtra("length", 3);
    }

}