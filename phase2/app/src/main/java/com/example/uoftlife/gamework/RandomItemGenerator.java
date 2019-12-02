package com.example.uoftlife.gamework;

import java.util.ArrayList;
import java.util.Random;

//This is a random generator, we use rand.nextInt to get probability,
//so hard coding cannot be avoid

/**
 * The class represents RandomItemGenerator.
 */
class RandomItemGenerator {

    /**
     * The bound of display.
     */
    private int bound;

    /**
     * The last time.
     */
    private long lastTime;

    /**
     * The speed of dropping items.
     */
    private double speed;

    /**
     * The rate of dropping coins.
     */
    private int rate;

    /**
     * Sets rate.
     */
     void setRate(int rate) {
         this.rate = rate;
     }

    /**
     * Instantiates a new Randomitemgenerator.
     */
    RandomItemGenerator(int bound) {
        this.bound = bound;
        lastTime = System.currentTimeMillis();
        speed = 0.7;
        rate = 300;
    }

    /**
     * Generate items ArrayList.
     */
    ArrayList<DropItems> generateItems(){
        ArrayList<DropItems> result = new ArrayList<>();
        long now = System.currentTimeMillis();
        long pass = now - lastTime;
        if(pass < rate){
            return result;
        }
        Random rand = new Random();
        int choice = rand.nextInt(100);
        if(choice > 70){
            result.add(generateSingleItem());
        }
        if(choice > 92){
            result.add(generateSingleItem());
        }
        if(choice > 97){
            result.add(generateSingleItem());
        }
        lastTime = now;
        return result;
    }

    /**
     * Sets speed.
     */
     void setSpeed(double speed) {
         this.speed = speed;
     }

    /**
     * Generate a single item.
     */
     private DropItems generateSingleItem(){
        Random rand = new Random();
        int choice = rand.nextInt(50);
        if(choice < 35){
            return new DropItems("coin", rand.nextInt(bound), speed);
        }else if(choice < 49){
            return new DropItems("bomb", rand.nextInt(bound), speed);
        }else{
            return new DropItems("health", rand.nextInt(bound), speed);
        }
    }
}
