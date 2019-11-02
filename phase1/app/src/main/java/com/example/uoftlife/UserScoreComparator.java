package com.example.uoftlife;

import java.util.Comparator;

public class UserScoreComparator implements Comparator<User> {
    /**
     * Compares its two arguments for order.
     *
     * Returns a negative integer, zero, or a positive integer
     * as s1 is less than, equal to, or greater than s2 in terms
     * of score.
     *
     * @param u1 the first score to compare
     * @param u2 the second score to compare
     * @return a negative integer, zero, or a positive integer
     *      as s1 is less than, equal to, or greater than s2
     */
    @Override
    public int compare(User u1, User u2) {
        return u1.getTotalScore() - u2.getTotalScore();
    }
}
