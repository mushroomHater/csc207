package com.example.uoftlife.gamestudy;

import com.example.uoftlife.data.DataFacade;

class GameStudy {

    private String word;
    private String alphabet;
    private int lengthOfWord;
    private long time;
    private int score;
    private int char1, char2;
    private int vitalityConsume;

    GameStudy() {
        word = "";
        alphabet = "abcdefghigklmnopqrstuvwxyz";
        char1 = DataFacade.getValue("char1");
        char2 = DataFacade.getValue("char2");
        if (char1 == 6 || char2 == 6){
            lengthOfWord = 30;
            time = 30000;
            vitalityConsume = 10;
        }
        else if (char1 == 7 || char2 == 7){
            lengthOfWord = 20;
            time = 50000;
            vitalityConsume = 5;
        }else {
            lengthOfWord = 25;
            time = 40000;
            vitalityConsume = 7;
        }
        score = 0;
    }

    String randomGenerateWord(){
        StringBuilder sb = new StringBuilder(lengthOfWord);
        for (int i = 0; i < lengthOfWord; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(alphabet.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(alphabet.charAt(index));
        }
        word = sb.toString();
        return word;
    }

    long getTime() {
        return time;
    }

    int getScore() {
        return score;
    }

    void  updateScore(String word){
        if (char2 == 6 || char1 == 6){
            score += Math.floor(20 * correctRate(word));
        }else if (char1 == 7 || char2 == 7){
            score += Math.floor(30 * correctRate(word));
        }else {
            score += Math.floor (25 * correctRate(word));
        }
    }
    private double correctRate(String wordInput){
        int numberLetterCorrect = 0;
        int length = Math.min(lengthOfWord, wordInput.length());
        for (int i = 0; i < length; i++){
            if (wordInput.charAt(i) == word.charAt(i)){
                numberLetterCorrect++;
            }
        }
        return numberLetterCorrect /(double) lengthOfWord;
    }

    int getVitalityConsume() {
        return vitalityConsume;
    }
}
