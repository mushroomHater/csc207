package com.example.uoftlife;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.example.uoftlife.data.DataFacade;
import com.example.uoftlife.util.GameMessenger;

public class PauseDisplayActivity extends FloatingActivity {

    private int saveClick = 0;
    private int exitClick = 0;


    @Override
    protected int setContentLayout() {
        return R.layout.activity_pause_display;
    }

    @Override
    protected void initializeView() {
        ((TextView)findViewById(R.id.testbutton)).setText("test");
    }


    private void setSaveButton() {
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener((view) -> {
            String text;
            if (getIntent().getBooleanExtra("savable", false)) {
                if (saveClick == 0) {
                    text = getString(R.string.save_alert);
                    saveClick++;
                } else {
                    // todo may add some progress animation during IO
                    if (DataFacade.saveProgress()) {
                        text = getString(R.string.save_success);
                    } else {
                        text = getString(R.string.save_error);
                    }
                }
            } else {
                text = getString(R.string.save_disable);
            }
            GameMessenger.getMessenger().toastMessage(text);
        });
    }

    private void setExitButton() {
        findViewById(R.id.exitButton).setOnClickListener((view) -> {
            if (exitClick == 0) {
                GameMessenger.getMessenger().toastMessage(getString(R.string.quit_alert));
                exitClick++;
            } else {
                startActivity(new Intent(this, MainActivity.class));
                // because launch mode of main activity is single task,
                // so there is no need to finish this activity manually
            }
        });
    }

    @Override
    protected void setListeners() {
        setSaveButton();
        setExitButton();
    }

}
