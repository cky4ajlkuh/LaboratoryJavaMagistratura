package semestr2.lab3.strategyimpl;

import semestr2.lab3.CountingStrategy;

public class SelectorStrategy {
    private CountingStrategy strategy;

    public SelectorStrategy(CountingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(CountingStrategy strategy) {
        this.strategy = strategy;
    }

    public void count(int[] array) {
        strategy.count(array);
    }
}
