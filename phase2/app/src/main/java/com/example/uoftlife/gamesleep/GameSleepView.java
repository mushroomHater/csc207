package com.example.uoftlife.gamesleep;

public interface GameSleepView {
    void setConfigBtn();

    void setInitialLanguage();

    void setInitialCharacter();

    void setAlarmBtn();

    void showAlarmAnimation(float windowWidth, float windowHeight);

    void setTimer();

    void setTimerText(String timerText);

    void startTimer(long timeLeft);

    void makeToast();

    void handleCharacter();

    void showOutcome();


}
