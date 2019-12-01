package com.example.uoftlife.cardgame;

class Card {
    private boolean covered;
    private int value;

    Card(int value) {
        this.value = value;
        covered = true;
    }

    boolean isMatched(Card card) {
        return this.value == card.value;
    }

    void setCovered(boolean covered) {
        this.covered = covered;
    }

    int getValue() {
        return value;
    }

    boolean isCovered() {
        return covered;
    }

    boolean equals(Card card) {
        return this.value == card.value;
    }
}