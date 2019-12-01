package com.example.uoftlife.cardgame;

import android.widget.ImageView;

import java.util.List;

class CardGame {
    private List<Card> cardArray;
    private ImageView[] listOfImageView;
    private int numCardOver;
    private int firstCard, secondCard;
    private int backImage;
    private int score;
    private int numberOfFlipTime;


    CardGame(List<Card> cards, ImageView[] listOfImageView, int backImage) {
        this.cardArray = cards;
        this.listOfImageView = listOfImageView;
        for (int i = 0; i < this.listOfImageView.length; i++) {
            this.listOfImageView[i].setTag(i);
        }
        numCardOver = 0;
        firstCard = 0;
        secondCard = 0;
        this.backImage = backImage;
        score = 0;
        numberOfFlipTime = 0;
    }

    ImageView[] getListOfImageView() {
        return listOfImageView;
    }

    /**
     * This method check whether the two cards matches
     */
    void checkResult() {
        if (!(cardArray.get(firstCard).equals(cardArray.get(secondCard)))) {
            cardArray.get(firstCard).setCovered(true);
            cardArray.get(secondCard).setCovered(true);
            numberOfFlipTime++;

        } else {
            updateScore(numberOfFlipTime);
            numberOfFlipTime = 0;
        }


        for (int i = 0; i < cardArray.size(); i++) {
            if (cardArray.get(i).isCovered()) {
                listOfImageView[i].setImageResource(backImage);
                listOfImageView[i].setEnabled(true);
            }
        }
        numCardOver = 0;
    }

    int getScore() {
        return score;
    }

    int getNumCardOver() {
        return numCardOver;
    }

    /**
     * @param imageView  The imageView that has been clicked
     * @param cardNumber The index of the card that has been click
     *                   This method will flip card that the player has clicked.
     */
    void flipCard(ImageView imageView, int cardNumber) {
        imageView.setImageResource(cardArray.get(cardNumber).getValue());
        cardArray.get(cardNumber).setCovered(false);
        if (numCardOver == 0) {
            firstCard = cardNumber;
            numCardOver++;
            imageView.setEnabled(false);
        } else {
            secondCard = cardNumber;
            numCardOver++;
            for (ImageView image : listOfImageView) {
                image.setEnabled(false);
            }
        }
    }


    private void updateScore(int numberOfFlipTime) {
        score += 10 + Math.floor(30 / numberOfFlipTime);
    }
}