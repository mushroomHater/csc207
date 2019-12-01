package com.example.uoftlife.floating;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.R;

public abstract class FloatingActivity extends AppCompatActivity {
    LinearLayout contentBaseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);
        contentBaseLayout = findViewById(R.id.contentLayout);
        ((TextView) findViewById(R.id.title_message)).setText(setTitle());
        setReturnButton();
        dynamicCreateView();
        inflateContentLayout();
        initializeView();
    }

    protected abstract @LayoutRes
    int setContentLayout();

    protected abstract String setTitle();

    protected abstract void initializeView();

    protected abstract void dynamicCreateView();

    protected LinearLayout getContentBaseLayout() {
        return contentBaseLayout;
    }

    private void inflateContentLayout() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        layoutInflater.inflate(setContentLayout(), getContentBaseLayout(), true);
    }

    private void setReturnButton() {
        findViewById(R.id.returnButton).setOnClickListener((view) -> finish());
    }
}
