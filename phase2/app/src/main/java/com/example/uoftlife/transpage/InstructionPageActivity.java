package com.example.uoftlife.transpage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uoftlife.R;

@SuppressLint("Registered")
public class InstructionPageActivity extends TransitionPageActivity {

    @Override
    protected Intent nextActivity() {
        return null;
    }

    @Override
    protected String setTitleText() {
        return getString(R.string.help);
    }


    private void setButtons() {
        findViewById(R.id.pause).setVisibility(View.GONE);
        Button backButton = new Button(this);
        ((ViewGroup) findViewById(R.id.page_base)).addView(backButton);
        backButton.setText(R.string.back);
        backButton.setOnClickListener((view) -> finish());
    }

    @Override
    protected String setDescriptionText() {
        setButtons();
        return getString(R.string.whole_instruction);
    }

    @Override
    protected int setShowLength() {
        return 2000;
    }

}
