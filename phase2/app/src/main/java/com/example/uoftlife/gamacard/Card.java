package com.example.uoftlife.gamacard;

class Card {
    private boolean covered;
    private int value;

    Card(int value) {
        this.value = value;
        covered = true;
    }

    /**
     *
     * @param covered true or false
     * set covered to false if card is fliped over and true otherwise
     */
    void setCovered(boolean covered) {
        this.covered = covered;
    }

    /**
     *
     * @return the value of this card
     */
    int getValue() {
        return value;
    }

    /**
     *
     * @return true if the card is covered, false otherwise
     */
    boolean isCovered() {
        return covered;
    }

    /**
     *
     * @param card the other card
     * @return true if the values of two cards' are equal.
     */
    boolean equals(Card card) {
        return this.value == card.value;
    }
}