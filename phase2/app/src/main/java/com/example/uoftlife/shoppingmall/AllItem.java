package com.example.uoftlife.shoppingmall;

public class AllItem {

    private int fullValue;
    private int moneyValue;
    private int intellectValue;
    private int strengthValue;
    private int wakingValue;

    public AllItem(int fullValue, int moneyValue, int intellectValue, int strengthValue, int wakingValue){
        this.fullValue = fullValue;
        this.moneyValue = moneyValue;
        this.intellectValue = intellectValue;
        this.strengthValue = strengthValue;
        this.wakingValue = wakingValue;
    }

    //this is the method used to purchase an item, and the user's status changes accordingly
    public void purchaseItem(Character a){
//        a.fullValue += fullValue;
//        a.moneyValue += moneyValue;
//        a.intellectValue += intellectValue;
//        a.strengthValue += strengthValue;
//        a.wakingValue += wakingValue;
//    }
}

}
