package com.example.uoftlife;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private long userid;
    private int totalScore;
    private int[] levelScore = new int[3];
    private boolean newGame = true;
    private int highestScore;

    public User(String username, String password, long userid) {
        this.username = username;
        this.password = password;
        this.userid = userid;
        totalScore = 0;
        highestScore = 0;
    }


    int getTotalScore() {
        return totalScore;
    }

    int getHighestScore(){
        return highestScore;
    }
    int getLevelScore(int level) {
        if (level > 0 && level < 4) {
            return levelScore[level - 1];
        } else {
            return 0;
        }
    }

    void setHighestScore() {
        if (totalScore > highestScore) {
            highestScore = totalScore;
        }
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public long getUserid() {
        return userid;
    }

    public void changePassword(String newpassword) {
        this.password = newpassword;
    }

    boolean checkPassword(String password) {
        if (password == null) {
            return false;
        }
        return this.password.equals(password);
    }

    void setNewGame() {
        newGame = true;
    }

    void setLevelScore(int level, int score) {
        if (newGame) {

            levelScore = new int[3];
            newGame = false;
        }
        if (level >= 1 && level <= 3) {
            levelScore[level - 1] = score;
            totalScore = levelScore[0] + levelScore[1] + levelScore[2];
        }
    }

}

