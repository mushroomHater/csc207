package com.example.uoftlife.util.calculator;

public interface GameProcessorStrategy<O,I> {
    O calculate(I input);
}
