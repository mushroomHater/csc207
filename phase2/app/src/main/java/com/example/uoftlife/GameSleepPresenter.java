package com.example.uoftlife;

class GameSleepPresenter {
    private GameSleepModel gameSleepModel;


    GameSleepPresenter(GameSleepModel gameSleepModel) {
        this.gameSleepModel = gameSleepModel;

    }


    void initializeDifficulty() {
//        int d = (int)GameConfiguration.getConfig().getDifficulty();
//        if(d==1){
//            timeLeftInMilliseconds = 16000;
//            LevelOneState.setTargetClick(65);
//        }else if(d==2){
//            timeLeftInMilliseconds = 10000;
//            LevelOneState.setTargetClick(60);
//        }else{
//            timeLeftInMilliseconds = 8000;
//            LevelOneState.setTargetClick(65);
//        }
    }

    /**
     * @return the target number of clicks in this game level.
     */
    static int getTargetClick() {
        return GameSleepModel.getTargetClick();
    }

    /**
     * Sets the target number of clicks in this game level.
     */
    static void setTargetClick(int t) {
        GameSleepModel.setTargetClick(t);
    }

    /**
     * @return the number of clicks a user has entered.
     */
    int getClickAmount() {
        return gameSleepModel.getClickAmount();
    }

    /**
     * Adds the number of clicks.
     */
    void addClickAmount() {
        gameSleepModel.addClickAmount();
    }

    int modelScore() {
        return gameSleepModel.getScore();
    }

    void setScore(int score) {
        gameSleepModel.setScore(score);
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
     * Checks if the user passes the game level.
     *
     * @return true if the player passes the game level, otherwise return false.
     */
    private boolean isPassed() {
        return gameSleepModel.getClickAmount() >= GameSleepModel.getTargetClick();
    }


}
