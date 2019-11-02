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
    private double totalScore;
    /**
     * The score the user gets at each level.
     */
    private List levelScore;
    /**
     * The current level user is at.
     */
    private int currentLevel;

    /**
     * Construct a new user.
     */
    public User(String username, String password, long userid){
        this.username = username;
        this.password = password;
        this.userid = userid;
        totalScore = 0;
        currentLevel = 0;
        levelScore = new ArrayList<>();
    }

    /**
     * Get the total score of this user.
     */
    public double getTotalScore() {
        return totalScore;
    }

    /**
     * Get the level score of this user.
     */
    public List getLevelScore() {
        return levelScore;
    }

    /**
     * Get the current level of this user.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Get the username of this user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the password of this user.
     */
    String getPassword() {
        return password;
    }

    /**
     * Get the userid of this user.
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

}

