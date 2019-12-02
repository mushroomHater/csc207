package com.example.uoftlife.gamework;

import java.util.ArrayList;

/**
 * Template class of the mvp structure, used for view.
 */
class CollectCoinTemplate {

    /**
     * The player's life.
     */
    private int life;

    /**
     * The player's score.
     */
    private int score;

    /**
     * The bag location.
     */
    private int bagLoction;

    /**
     * The last drop.
     */
    private long lastDrop;

    /**
     * The x coordinate of border.
     */
    private int borderX = 930;

    /**
     * The y coordinate of border.
     */
    private int borderY = 1700;

    /**
     * The max number of items.
     */
    private int maxNumItem;
    //Since the actual size of screen is actually smaller than that is directly
    // get from WindowManager, so we have to hardcode the size of screen.

    /**
     * The height of bag.
     */
    private int bagHeight;

    /**
     * The CollectCoinPresenter class.
     */
    private CollectCoinPresenter presenter;

    /**
     * The RandomItemGenerator used to generate dropping items.
     */
    private RandomItemGenerator generator;

    /**
     * The ArrayList containing items.
     */
    private ArrayList<DropItems> items;

    /**
     * Instantiates a new Collect coin template.
     */
    CollectCoinTemplate(int bagLoction, int bagHeight,
                               int maxNumItem, CollectCoinPresenter presenter) {
        this.presenter = presenter;
        this.maxNumItem = maxNumItem;
        this.bagLoction = bagLoction;
        this.bagHeight = bagHeight;
        lastDrop = System.currentTimeMillis();
        generator = new RandomItemGenerator(borderX);
        items = new ArrayList<>();
        score = 0;
        life = 3;
    }

    /**
     * Gets items.
     */
    ArrayList<DropItems> getItems() {
        return items;
    }

    /**
     * Refresh the game.
     */
    void refresh(){
        long now = System.currentTimeMillis();
        long pass = now-lastDrop;
        ArrayList<DropItems> newList = new ArrayList<>();
        for(DropItems item: items){
            item.drop((int)pass);
            if(item.getLocationY() < borderY){
                newList.add(item);
            }
        }
        items = newList;
        lastDrop = now;
        ArrayList<DropItems> newItems = generator.generateItems();
        if(items.size() + newItems.size() < maxNumItem){
            items.addAll(newItems);
        }

    }

    /**
     * Set bag location.
     */
    void setBagLocation(int location){
        bagLoction = location;
    }

    /**
     * Increase score.
     */
    void increaseScore(){
        score ++;
    }

    /**
     * Increase health.
     */
    void increaseHealth(){
        life ++;
    }

    /**
     * Decrease health.
     */
    void decreaseHealth(){
        life --;
    }

    /**
     * Check collision to see whether the bag catches a coin, a shit or a health pack .
     */
    void checkCollision(){
        ArrayList<DropItems> newList = new ArrayList<>();
        for(DropItems item: items){
            if(item.getLocationX() > bagLoction-90 && item.getLocationX() < bagLoction + 110){
                if(item.getType().equals("coin")
                        && Math.abs(item.getLocationY() - bagHeight) < 60) {
                    presenter.increaseScore();
                }else if(item.getType().equals("bomb")
                        && Math.abs(item.getLocationY() - bagHeight) < 60){
                    presenter.decreaseHealth();
                }else if(item.getType().equals("health")
                        && Math.abs(item.getLocationY() - bagHeight) < 60 ){
                    presenter.increaseHealth();
                }else{
                    newList.add(item);
                }
            }else{
                newList.add(item);
            }

        }
        items = newList;
    }

    /**
     * Increase drop speed.
     */
     void increaseDropSpeed(){
         generator.setSpeed(2);
    }

    /**
     * Increase generate rate.
     */
     void increaseGenerateRate(){
         generator.setRate(100);
    }
}
