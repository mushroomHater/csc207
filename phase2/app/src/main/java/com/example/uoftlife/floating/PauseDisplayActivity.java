package com.example.uoftlife.floating;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.uoftlife.MainActivity;
import com.example.uoftlife.R;
import com.example.uoftlife.data.DataFacade;
import com.example.uoftlife.data.GameConstants;
import com.example.uoftlife.util.GameMessenger;

public class PauseDisplayActivity extends FloatingActivity {

    private LayoutInflater inflater;
    private int saveClick = 0;
    private int exitClick = 0;
    private final String TIME_KEY = "time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inflater = LayoutInflater.from(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_pause_display;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.pause);
    }

    @Override
    protected void initializeView() {
        setSaveButton();
        setExitButton();
        ((TextView) findViewById(R.id.status)).setText(getRandomStatusDisplay());
        setTimeBar();
    }

    private void setTimeBar() {
        addAttributeLine(TIME_KEY);
        ProgressBar timeBar = findViewById(R.id.time_bar);
        timeBar.setIndeterminate(false);
        Integer max = GameConstants.GAME_STATUS_INIT.get(TIME_KEY);
        if (max != null) {
            timeBar.setMax(max);
        } else {
            throw new NullPointerException();
        }
        timeBar.setMin(0);
        timeBar.setProgress(DataFacade.getValue(TIME_KEY), true);
    }

    private String getRandomStatusDisplay() {
        /*Object statusList = DataFacade.getTempData("status");
        if (statusList instanceof List) {
            int index = (int) (((List) statusList).size() * Math.random());
            Object statusString = ((List) statusList).get(index);
            if (statusString instanceof String) {
                return (String) statusString;
            }
        }
        return String.format(getString(R.string.status_default), DataFacade.getTempData("name"));*/
        Object status = DataFacade.getTempData("status");
        if (status instanceof String) {
            return String.format((String) status, DataFacade.getTempData("name"));
        } else {
            return String.format(getString(R.string.status_default), DataFacade.getTempData("name"));
        }
    }

    @Override
    protected void dynamicCreateView() {
        for (String s : GameConstants.GAME_STATUS) {
            addAttributeLine(s);
        }
    }

    private void addAttributeLine(String key) {
        View line = inflater.inflate(R.layout.attribute_line, getContentBaseLayout(), false);
        int value = DataFacade.getValue(key);
        if (value != -1) {
            ((TextView) line.findViewById(R.id.attribute_name)).setText(key);
            ((TextView) line.findViewById(R.id.attribute_value)).setText(String.valueOf(DataFacade.getValue(key)));
        }
        getContentBaseLayout().addView(line);
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


}
