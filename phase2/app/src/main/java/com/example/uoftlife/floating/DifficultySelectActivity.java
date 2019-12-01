package com.example.uoftlife.floating;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.uoftlife.R;
import com.example.uoftlife.data.GameConstants;

public class DifficultySelectActivity extends FloatingActivity {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_difficulty_select;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.difficulty);
    }

    @Override
    protected void initializeView() {

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(getContentBaseLayout().getLayoutParams());
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.topToBottom = R.id.title_message;
        getContentBaseLayout().setLayoutParams(params);
        ((TextView) (findViewById(R.id.instruction)))
                .setText(String.format(getString(R.string.difficulty_instruction),
                        getString(R.string.diff_easy), GameConstants.EASY_POINTS,
                        getString(R.string.diff_medium), GameConstants.MEDIUM_POINTS,
                        getString(R.string.diff_hard), GameConstants.HARD_POINTS));
        setListener();
    }

    private void setListener() {
        findViewById(R.id.easy).setOnClickListener((v) -> {
            Intent nextActivity = new Intent(this, ConfigActivity.class);
            nextActivity.putExtra("points", GameConstants.EASY_POINTS);
            finish();
            startActivity(nextActivity);
        });
        findViewById(R.id.medium).setOnClickListener((v) -> {
            Intent nextActivity = new Intent(this, ConfigActivity.class);
            nextActivity.putExtra("points", GameConstants.MEDIUM_POINTS);
            finish();
            startActivity(nextActivity);
        });
        findViewById(R.id.hard).setOnClickListener((v) -> {
            Intent nextActivity = new Intent(this, ConfigActivity.class);
            nextActivity.putExtra("points", GameConstants.HARD_POINTS);
            finish();
            startActivity(nextActivity);
        });
    }

    @Override
    protected void dynamicCreateView() {
        findViewById(R.id.saveButton).setVisibility(View.GONE);
        findViewById(R.id.exitButton).setVisibility(View.GONE);
        findViewById(R.id.returnButton).setVisibility(View.GONE);
        findViewById(R.id.time_bar).setVisibility(View.GONE);
    }
}
