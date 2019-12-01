package com.example.uoftlife.gamacard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uoftlife.R;

import java.util.ArrayList;
import java.util.Collections;

public class GameCardActivity extends AppCompatActivity {

    private GameCard cardGame;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game_acticity);
        int backImage = R.drawable.question;
        Card card1 = new Card(R.drawable.cow1);
        Card card2 = new Card(R.drawable.cow2);
        Card card3 = new Card(R.drawable.cow3);
        Card card4 = new Card(R.drawable.cow4);
        Card card5 = new Card(R.drawable.cow5);
        Card card6 = new Card(R.drawable.cow6);
        score = findViewById(R.id.score);

        Card[] cards = {card1, card2, card3, card4, card5, card6};
        ArrayList<Card> cardArray = new ArrayList<>();
        for (Card card: cards){
            cardArray.add(card);
            cardArray.add(card);
        }
        Collections.shuffle(cardArray);
        ImageView[] listOfImageView = new ImageView[12];
        listOfImageView[0] = findViewById(R.id.pic1);
        listOfImageView[1] = findViewById(R.id.pic2);
        listOfImageView[2] = findViewById(R.id.pic3);
        listOfImageView[3] = findViewById(R.id.pic4);
        listOfImageView[4] = findViewById(R.id.pic5);
        listOfImageView[5] = findViewById(R.id.pic6);
        listOfImageView[6] = findViewById(R.id.pic7);
        listOfImageView[7] = findViewById(R.id.pic8);
        listOfImageView[8] = findViewById(R.id.pic9);
        listOfImageView[9] = findViewById(R.id.pic10);
        listOfImageView[10] = findViewById(R.id.pic11);
        listOfImageView[11] = findViewById(R.id.pic12);

        cardGame = new GameCard(cardArray, listOfImageView,backImage);
    }

    @Override
    protected void onResume(){
        super.onResume();
        setViews();
        score.setText("Score: 0");
    }

    private void setViews(){
        for (ImageView imageView: cardGame.getListOfImageView()){
            imageView.setOnClickListener((view)->{
                int cardNumber = (int) imageView.getTag();
                cardGame.flipCard(imageView, cardNumber);
                if (cardGame.getNumCardOver() == 2){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cardGame.checkResult();
                            score.setText("Score: " + cardGame.getScore());
                        }
                    }, 1000);
                }
            });
        }
    }

}
