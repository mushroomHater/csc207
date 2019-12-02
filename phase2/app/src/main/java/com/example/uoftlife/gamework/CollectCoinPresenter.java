package com.example.uoftlife.gamework;

/**
 * Presenter class of the mvp structure, used for view class and model class to communicate.
 */
 class CollectCoinPresenter {

    /**
     * A CollectCoinActivity class(the view part of mvp).
     */
    private CollectCoinActivity view;

    /**
     * The model of the mvp.
     */
    private CollectCoinTemplate template;

    /**
     * The constructor of the class CollectCoinPresenter.
     */
     CollectCoinPresenter(CollectCoinActivity view){
        this.view = view;
        template = new CollectCoinTemplate(view.getBagLocation(),
                view.getBagHeight(), view.getMaxNumItem(), this);
    }

    /**
     * Update the positions of items in display.
     */
     void updateGraph(){
        template.setBagLocation(view.getBagLocation());
        template.checkCollision();
        template.refresh();
        view.updateGraph(template.getItems());
    }

    /**
     * Increase the score and update in view and template.
     */
     void increaseScore(){
        view.increaseScore();
        template.increaseScore();
    }

    /**
     * Decrease the score and update in view and template.
     */
     void decreaseHealth(){
        view.decreaseHealth();
        template.decreaseHealth();
    }

    /**
     * Increase the health and update in view and template.
     */
    void increaseHealth(){
        view.increaseHealth();
        template.increaseHealth();
    }

    void setDifficulty(){
      template.increaseDropSpeed();
      template.increaseGenerateRate();

    }







}
