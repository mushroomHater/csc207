package com.example.uoftlife.gamesleep;

import android.os.CountDownTimer;

class GameSleepPresenter {
    private GameSleepModel gameSleepModel;
    private GameSleepView gameSleepView;


    GameSleepPresenter(GameSleepModel gameSleepModel, GameSleepView gameSleepView) {
        this.gameSleepModel = gameSleepModel;
        this.gameSleepView = gameSleepView;

    }


    void initializeDifficulty() {
        //todo set difficulty

        //       int d = (int)GameConfiguration.getConfig().getDifficulty();
        int d = 1;
        if (d == 1) {
            setTimeLeftInMilliseconds(16000);
            setTargetClick(45);
            setAlarmChangePositionInterval(5000);
        } else if (d == 2) {
            setTimeLeftInMilliseconds(15000);
            setTargetClick(50);
            setAlarmChangePositionInterval(4000);
        } else {
            setTimeLeftInMilliseconds(14000);
            setTargetClick(60);
            setAlarmChangePositionInterval(3000);
        }
    }

    /**
     * @return the target number of clicks in this game level.
     */
    private int getTargetClick() {
        return gameSleepModel.getTargetClick();
    }

    /**
     * Sets the target number of clicks in this game level.
     */
    private void setTargetClick(int targetClick) {
        gameSleepModel.setTargetClick(targetClick);
    }

    /**
     * @return the number of clicks a user has entered.
     */
    private int getClickAmount() {
        return gameSleepModel.getClickAmount();
    }

    /**
     * Adds the number of clicks.
     */
    void addClickAmount() {
        gameSleepModel.addClickAmount();
    }

    /**
     * @return the score of the user within the range of 0 - 100.
     * If the user passes the game level, return a score within the range 1 - 100,
     * depending on the performance/ click amount of the user.
     * If the user fails the game level, return 0.
     */
    int getScore() {
        if (isPassed()) {
            if (getClickAmount() >= getTargetClick() &&
                    getClickAmount() < getTargetClick() * 1.2) {
                gameSleepModel.setScore(60);
            } else if (getClickAmount() >= getTargetClick() * 1.2 &&
                    getClickAmount() < getTargetClick() * 1.3) {
                gameSleepModel.setScore(70);
            } else if (getClickAmount() >= getTargetClick() * 1.3 &&
                    getClickAmount() < getTargetClick() * 1.4) {
                gameSleepModel.setScore(80);
            } else if (getClickAmount() >= getTargetClick() * 1.4 &&
                    getClickAmount() < getTargetClick() * 1.5) {
                gameSleepModel.setScore(90);
            } else if (getClickAmount() >= getTargetClick() * 1.5) {
                gameSleepModel.setScore(100);
            }
        } else {
            gameSleepModel.setScore(0);
        }
        return gameSleepModel.getScore();
    }


    /**
     * @return the if the timer is timing.
     */
    private boolean isTiming() {
        return gameSleepModel.isTiming();
    }

    /**
     * Sets if the timer should be timing.
     */
    void setTiming(boolean timing) {
        gameSleepModel.setTiming(timing);
    }

    /**
     * @return the time left for the game level in milliseconds.
     */
    long getTimeLeftInMilliseconds() {
        return gameSleepModel.getTimeLeftInMilliseconds();
    }

    /**
     * Sets the time left for the game level in milliseconds.
     */
    void setTimeLeftInMilliseconds(long timeLeftInMilliseconds) {
        gameSleepModel.setTimeLeftInMilliseconds(timeLeftInMilliseconds);
    }

    /**
     * Sets the timer.
     */
    void setTimer(CountDownTimer timer) {
        gameSleepModel.setTimer(timer);

    }

    /**
     * Updates the countdown timer.
     */
    void updateTimer() {
        if (isTiming()) {
            int seconds = (int) getTimeLeftInMilliseconds() / 1000;

            String timeLeftText;
            timeLeftText = "00:";
            if (seconds < 10) {
                timeLeftText += "0";
            }
            timeLeftText += seconds;
            gameSleepView.setTimerText(timeLeftText);

        }

    }

    void onDestroy() {
        gameSleepView.showOutcome();
        setTiming(false);
        cancelTimer();
    }

    /**
     * Stops the countdown timer.
     */
    void pauseTimer() {
        setTiming(false);
    }

    /**
     * Cancels the countdown timer.
     */
    void cancelTimer() {
        gameSleepModel.cancelTimer();
    }

    /**
     * Handles the alarm button with changing location.
     */
    void handleAlarmAnimation() {
        float[] coordinate = gameSleepModel.generateRandomLocation();
        gameSleepView.showAlarmAnimation(coordinate[0], coordinate[1]);
    }


    int getAlarmChangePositionInterval() {
        return gameSleepModel.getAlarmChangePositionInterval();
    }

    private void setAlarmChangePositionInterval(int alarmChangePositionInterval) {
        gameSleepModel.setAlarmChangePositionInterval(alarmChangePositionInterval);
    }

    /**
     * Checks if the user passes the game level.
     *
     * @return true if the player passes the game level, otherwise return false.
     */
    private boolean isPassed() {
        return getClickAmount() >= getTargetClick();
    }

    /**
     * Makes a toast if click amount reaches 4.
     */
    void makeToast() {
        if (this.getClickAmount() == 4) {
            gameSleepView.makeToast();
        }
    }

    /**
     * Specifies character's appearance according to the number of clicks entered.
     */
    int handleCharacter() {
        int d = 0;

        if (getClickAmount() == getTargetClick() / 4) {
            d = 1;
        }
        if (getClickAmount() == getTargetClick() / 2) {
            d = 2;
        }
        if (getClickAmount() == getTargetClick() / 4 * 3) {
            d = 3;
        }

        if (getClickAmount() == getTargetClick()) {
            d = 4;
        }
        return d;
    }


}
