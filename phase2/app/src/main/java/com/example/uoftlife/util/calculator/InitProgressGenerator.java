package com.example.uoftlife.util.calculator;

import com.example.uoftlife.data.DataFacade;

public class InitProgressGenerator {
    private final int WEALTH_RATE = 500;

    public void generate() {
        DataFacade.addToValue("money", WEALTH_RATE * DataFacade.getValue("wealth"));
        DataFacade.setValue("started", 1);
    }
}
