package com.example.uoftlife;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    @Override
    /**
     * Compares its two arguments for order.
     *
     * Returns a negative integer, zero, or a positive integer
     * as s1 is less than, equal to, or greater than s2 in terms
     * of score.
     *
     * @param s1 the first score to compare
     * @param s2 the second score to compare
     * @return a negative integer, zero, or a positive integer
     *      as s1 is less than, equal to, or greater than s2
     */
    public int compare(Score s1, Score s2) {
        return s1.getScore() - s2.getScore();
    }
}
