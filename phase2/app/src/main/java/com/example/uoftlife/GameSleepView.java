package com.example.uoftlife;

public interface GameSleepView {
    void showAlarmAnimation(float dx, float dy);

    void makeToast();

    void handleCharacter();

    void showOutcome();

    void setTimerText(String timerText);
}
