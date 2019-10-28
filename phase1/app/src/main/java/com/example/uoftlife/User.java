package com.example.uoftlife;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private long userid;
    private double totalScore;
    private List levelScore;
    private int currentLevel;

    public User(String username, String password, long userid){
        this.username = username;
        this.password = password;
        this.userid = userid;
        totalScore = 0;
        currentLevel = 0;
        levelScore = new ArrayList<>();
    }


    public double getTotalScore() {
        return totalScore;
    }

    public List getLevelScore() {
        return levelScore;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public long getUserid() {
        return userid;
    }

    public void changePassword(String newpassword){
        this.password = newpassword;
    }

    boolean checkPassword(String password){
        if(password == null){
            return false;
        }
        return this.password.equals(password);
    }

}

