package com.example.uoftlife;

import java.util.Comparator;

class UserScoreComparator implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        return u1.getHighestScore() - u2.getHighestScore();
    }
}
