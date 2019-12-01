package com.example.uoftlife.util.calculator;

abstract class GameProcessorStrategyDecorator<O,I> implements GameProcessorStrategy<O,I> {
    private GameProcessorStrategy<O,I> decorated;

    GameProcessorStrategyDecorator(GameProcessorStrategy<O,I> gps) {
        decorated = gps;
    }

    @Override
    public O calculate(I input) {
        if (decorated != null) {
            return decorated.calculate(input);
        } else {
            return null;
        }
    }

}
