package com.example.uoftlife.gamework;

import java.util.ArrayList;

public class CollectCoinTemplate {

    int life;
    int score;
    int bagLoction;
    long lastDrop;
    int borderX = 930;
    int borderY = 1700;
    int maxNumItem;
    //Since the actual size of screen is actually smaller than that is directly
    // get from WindowManager, so we have to hardcode the size of screen.
    int bagHeight;
    CollectCoinPresenter presenter;
    RandomItemGenerator generator;
    ArrayList<DropItems> items;

    public CollectCoinTemplate(int bagLoction, int bagHeight,
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

    public ArrayList<DropItems> getItems() {
        return items;
    }

    public void refresh(){
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

    public void setBagLocation(int location){
        bagLoction = location;
    }

    public void increaseScore(){
        score ++;
    }

    public void increaseHealth(){
        life ++;
    }

    public void decreaseHealth(){
        life --;
    }

    public void checkCollision(){
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
}
