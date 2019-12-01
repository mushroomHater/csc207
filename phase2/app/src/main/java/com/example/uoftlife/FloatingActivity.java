package com.example.uoftlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

public abstract class FloatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);
        setReturnButton();
        inflateContentLayout();
        setListeners();
        initializeView();
    }

    protected abstract @LayoutRes
    int setContentLayout();

    protected abstract void initializeView();

    protected ViewGroup getContentBaseLayout() {
        return findViewById(R.id.contentLayout);
    }

    private void inflateContentLayout() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        layoutInflater.inflate(setContentLayout(), getContentBaseLayout(), true);
    }

    private void setReturnButton() {
        findViewById(R.id.returnButton).setOnClickListener((view) -> finish());
    }

    protected abstract void setListeners();
}
