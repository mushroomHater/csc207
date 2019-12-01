package com.example.uoftlife.gamework;

 class CollectCoinPresenter {

    private CollectCoinActivity view;
    private CollectCoinTemplate template;

     CollectCoinPresenter(CollectCoinActivity view){
        this.view = view;
        template = new CollectCoinTemplate(view.getBagLocation(),
                view.getBagHeight(), view.getMaxNumItem(), this);
    }

     void updateGraph(){
        template.setBagLocation(view.getBagLocation());
        template.checkCollision();
        template.refresh();
        view.updateGraph(template.getItems());
    }

     void increaseScore(){
        view.increaseScore();
        template.increaseScore();
    }

     void decreaseHealth(){
        view.decreaseHealth();
        template.decreaseHealth();
    }

     void increaseHealth(){
        view.increaseHealth();
        template.increaseHealth();
    }







}
