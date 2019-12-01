package com.example.uoftlife.floating;

import android.content.Intent;
import android.widget.Button;

import androidx.annotation.StringRes;

import com.example.uoftlife.R;

//todo
public abstract class EventSelectActivity extends PauseDisplayActivity {

    @Override
    protected String setTitle() {
        return String.format(getString(R.string.event), "username");
    }

    @Override
    protected abstract void dynamicCreateView();

    protected void addButtonWithIntent(@StringRes int textResId, Class<?> goalActivity) {
        Button button = new Button(this);
        button.setText(getString(textResId));
        getContentBaseLayout().addView(button);
        button.setOnClickListener((view) -> {
            startActivity(new Intent(this, goalActivity));
            finish();
        });
    }
}
