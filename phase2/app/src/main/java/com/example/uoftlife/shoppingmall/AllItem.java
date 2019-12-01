package com.example.uoftlife.shoppingmall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AllItem implements Iterable{

    private ArrayList<String> items = new ArrayList(){{
        for (int i = 0; i < 100; i++){
            double r = Math.random();
            if (r < 0.2){
                items.add("bread");
            }
            //todo
        }
    }};
    //the order of this list is the value of money, understanding, practice, vitality, repletion, mood respectively
    static int[] bread = {-200, 0,0,0,10,5};
    static int[] coffee = {-200, 0,0,10,0,5};
    static int[] bubbleTea = {-300, 0,0,15, 0, 10};
    static int[] book = {-2000, 10,10, -10, 0, -10};
    static int[] lipstick = {-500, 0,0,15, 0, 20};
    public static HashMap item = new HashMap(){{
        put("bread", bread);
        put("coffee", coffee);
        put("bubbleTea", bubbleTea);
        put("book", book);
        put("lipstick", lipstick);
    }
    };
//
    public void purchaseItem(HashMap a, int[] c){
        a.replace("money", ((int) (a.get("money")) + c[0]));
        a.replace("understanding", ((int) (a.get("understanding")) + c[1]));
        a.replace("practice", ((int) (a.get("practice")) + c[2]));
        a.replace("vitality", ((int) (a.get("vitality")) + c[3]));
        a.replace("repletion", ((int) (a.get("repletion")) + c[4]));
        a.replace("mood", ((int) (a.get("mood")) + c[5]));
    }


    public Iterator iterator() {
        return new itemIterator();
    }

    private class itemIterator implements Iterator<String> {

        private int next;


        @Override
        public boolean hasNext() {
            return next < items.size();
        }


        @Override
        public String next() {
            if (hasNext()) {
                return items.get(next++);
            }
            throw new NoSuchElementException();
        }

    }

}
