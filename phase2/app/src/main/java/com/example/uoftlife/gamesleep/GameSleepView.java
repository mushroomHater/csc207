package com.example.uoftlife.gamesleep;

public interface GameSleepView {
    void setConfigBtn();

    void setInitialLanguage();

    void setInitialCharacter();

    void setAlarmBtn();

    void showAlarmAnimation(float dx, float dy);

    void setTimer();

    void setTimerText(String timerText);

    void startTimer();

    void makeToast();

    void handleCharacter();

    void showOutcome();


}
