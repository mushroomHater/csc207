package com.example.uoftlife.gamework;

import java.util.ArrayList;
import java.util.Random;

//This is a random generator, we use rand.nextInt to get probability,
//so hard coding cannot be avoid

public class RandomItemGenerator {

    private int bound;
    long lastTime;

    public RandomItemGenerator(int bound) {
        this.bound = bound;
        lastTime = System.currentTimeMillis();
    }

    public ArrayList<DropItems> generateItems(){
        ArrayList<DropItems> result = new ArrayList<>();
        long now = System.currentTimeMillis();
        long pass = now - lastTime;
        if(pass < 300){
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

    private DropItems generateSingleItem(){
        Random rand = new Random();
        int choice = rand.nextInt(50);
        if(choice < 35){
            return new DropItems("coin", rand.nextInt(bound));
        }else if(choice < 49){
            return new DropItems("bomb", rand.nextInt(bound));
        }else{
            return new DropItems("health", rand.nextInt(bound));
        }
    }
}
