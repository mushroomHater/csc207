package com.example.uoftlife;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A user class.
 */
public class User implements Serializable {
    /**
     * The name of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The id of the user.
     */
    private long userid;

    /**
     * The total score of the user.
     */
    private int totalScore;

    /**
     * The score the user gets at each level.
     */
    private int[] levelScore = new int[3];

    /**
     * Indicates if this is a new game.
     */
    private boolean newGame = true;

    /**
     * Construct a new user.
     */
    public User(String username, String password, long userid){
        this.username = username;
        this.password = password;
        this.userid = userid;
        totalScore = 0;
    }

    /**
     * Return the total score of this user.
     */
    int getTotalScore() {
        return totalScore;
    }

    /**
     * Return the level scores of this user.
     */
    int getLevelScore(int level) {
        return levelScore[level-1];
    }

    /**
     * Return the username of this user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return the password of this user.
     */
    String getPassword() {
        return password;
    }

    /**
     * Return the userid of this user.
     */
    public long getUserid() {
        return userid;
    }

    /**
     * Change the password of this user.
     */
    public void changePassword(String newpassword){
        this.password = newpassword;
    }

    /**
     * Check if the input password is correct.
     */
    boolean checkPassword(String password){
        if(password == null){
            return false;
        }
        return this.password.equals(password);
    }

    /**
     * Set this game of this user as a new game.
     */
    void setNewGame(){
        newGame = true;
    }

    /**
     * Set the level scores of this user.
     */
    void setLevelScore(int level, int score){
        if(newGame){
            levelScore = new int[3];
            newGame = false;
        }
        if(level>=1&&level<=3){
            levelScore[level - 1] = score;
            totalScore = levelScore[0] + levelScore[1] + levelScore[2];
        }
    }

}

