package com.example.uoftlife.floating;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.uoftlife.R;
import com.example.uoftlife.data.DataFacade;
import com.example.uoftlife.data.GameConstants;
import com.example.uoftlife.gamemap.MapActivity;
import com.example.uoftlife.util.GameMessenger;

public class ConfigActivity extends FloatingActivity {

    private LayoutInflater inflater;
    private int point;
    private TextView pointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataFacade.initialize();
        inflater = LayoutInflater.from(this);
        point = getIntent().getIntExtra("points", GameConstants.EASY_POINTS);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_config;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.build_character);
    }

    @Override
    protected void initializeView() {
        ((Button) findViewById(R.id.saveButton)).setText(R.string.randomize);
        ((Button) findViewById(R.id.exitButton)).setText(R.string.ready);
        pointView.setText(String.valueOf(point));
        findViewById(R.id.exitButton).setOnClickListener((view) -> {
            if(point>0){
                GameMessenger.getMessenger().toastMessage(getString(R.string.ch_alert));
            }else{
                DataFacade.saveConfig();
                DataFacade.saveProgress();
                finish();
                startActivity(new Intent(this, MapActivity.class));
            }
        });
        findViewById(R.id.saveButton).setOnClickListener((view) -> {
            if(point>0){
                GameMessenger.getMessenger().toastMessage(getString(R.string.ch_alert));
            }else{
                updateCharacteristics();
            }
        });
    }

    @Override
    protected void dynamicCreateView() {
        setSubtitle(getString(R.string.fated_resources));

        View pointLine = inflater.inflate(R.layout.attribute_line, getContentBaseLayout(), false);
        ((TextView) pointLine.findViewById(R.id.attribute_name)).setText(R.string.free_points);
        pointView = pointLine.findViewById(R.id.attribute_value);
        for (String s : GameConstants.INNATE_ATTRIBUTES) {
            addConfigLine(s);
        }
        getContentBaseLayout().addView(pointLine);
        getContentBaseLayout().addView(inflater.inflate(R.layout.attribute_line, getContentBaseLayout(), false));
        setSubtitle(getString(R.string.characteristic));
    }

    private void setSubtitle(String text) {
        View subTitle = inflater.inflate(R.layout.padding_text, getContentBaseLayout(), false);
        ((TextView) subTitle.findViewById(R.id.text_display)).setText(text);
        getContentBaseLayout().addView(subTitle);
    }

    private void addConfigLine(String key) {
        View line = inflater.inflate(R.layout.config_line, getContentBaseLayout(), false);
        int value = DataFacade.getValue(key);
        if (value != -1) {
            ((TextView) line.findViewById(R.id.item_name)).setText(key);
            setLineListener(line, key);
        }
        getContentBaseLayout().addView(line);
    }

    private void setLineListener(View line, String key) {
        line.findViewById(R.id.minus).setOnClickListener((view) -> {
            if (DataFacade.getValue(key) > 0) {
                DataFacade.addToValue(key, -1);
                point++;
                updateDisplayValue(line, key);
            }
        });
        line.findViewById(R.id.plus).setOnClickListener((view) -> {
            if (point > 0) {
                DataFacade.addToValue(key, 1);
                point--;
                updateDisplayValue(line, key);
                if(point==0){
                    updateCharacteristics();
                }
            }
        });
    }

    private void updateCharacteristics(){
        //todo
        System.out.println("todo");
    }

    private void updateDisplayValue(View line, String key) {
        ((TextView) line.findViewById(R.id.item_value)).setText(String.valueOf(DataFacade.getValue(key)));
        pointView.setText(String.valueOf(point));
    }

}
