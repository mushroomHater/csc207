package com.example.uoftlife.gamework;

import java.util.ArrayList;

 class CollectCoinTemplate {

    private int life;
    private int score;
    private int bagLoction;
    private long lastDrop;
    private int borderX = 930;
    private int borderY = 1700;
    private int maxNumItem;
    //Since the actual size of screen is actually smaller than that is directly
    // get from WindowManager, so we have to hardcode the size of screen.
    private int bagHeight;
    private CollectCoinPresenter presenter;
    private RandomItemGenerator generator;
    private ArrayList<DropItems> items;

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

     ArrayList<DropItems> getItems() {
        return items;
    }

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

     void setBagLocation(int location){
        bagLoction = location;
    }

     void increaseScore(){
        score ++;
    }

     void increaseHealth(){
        life ++;
    }

     void decreaseHealth(){
        life --;
    }

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

    public void increaseDropSpeed(){
         generator.setSpeed(2);
    }

    public void increaseGenerateRate(){
         generator.setRate(100);
    }
}
