package com.example.uoftlife.collectcoin;

import java.util.ArrayList;

public class CollectCoinPresenter {

    CollectCoinActivity view;
    CollectCoinTemplate template;

    public CollectCoinPresenter(CollectCoinActivity view){
        this.view = view;
        template = new CollectCoinTemplate(view.getBagLocation(),
                view.getBagHeight(), view.getMaxNumItem(), this);
    }

    public void updateGraph(){
        template.setBagLocation(view.getBagLocation());
        template.checkCollision();
        template.refresh();
        view.updateGraph(template.getItems());
    }

    public void increaseScore(){
        view.increaseScore();
        template.increaseScore();
    }

    public void decreaseHealth(){
        view.decreaseHealth();
        template.decreaseHealth();
    }

    public void increaseHealth(){
        view.increaseHealth();
        template.increaseHealth();
    }







}
