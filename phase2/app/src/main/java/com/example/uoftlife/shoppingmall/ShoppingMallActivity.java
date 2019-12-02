package com.example.uoftlife.shoppingmall;

import com.example.uoftlife.GameBaseActivity;
import com.example.uoftlife.R;

public class ShoppingMallActivity extends GameBaseActivity {

    AllItem item;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_purchase_item;
    }

    @Override
    protected boolean setSavable() {
        return false;
    }
/*

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_item);
        setItem1Button();
        setItem2Button();
        setItem3Button();
        setNextPageButton();

    }


    private void setNextPageButton() {
        findViewById(R.id.nextPage).setOnClickListener((view) -> {
        });
    }

    private void setItem1Button() {
        findViewById(R.id.button1).setOnClickListener((view) -> {
        });
    }

    private void setItem2Button() {
        findViewById(R.id.button2).setOnClickListener((view) -> {
        });
    }

    private void setItem3Button() {
        findViewById(R.id.button3).setOnClickListener((view) -> {
        });
    }
*/


    // if click on the item button, the user purchases it immediately
    // and its status changes accordingly.

}

