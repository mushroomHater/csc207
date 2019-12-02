package com.example.uoftlife.floating;

import com.example.uoftlife.R;
import com.example.uoftlife.gamework.CollectCoinActivity;
import com.example.uoftlife.shoppingmall.ShoppingMallActivity;

public class MallEventActivity extends EventSelectActivity {

    @Override
    protected void dynamicCreateView() {
        addButtonWithIntent(R.string.shopping, ShoppingMallActivity.class);
        addButtonWithIntent(R.string.work, CollectCoinActivity.class);
    }
}
