package semestr2.lab3.strategyimpl;

import semestr2.lab3.CountingStrategy;

import java.util.HashMap;
import java.util.Map;

public class HasMapStrategy implements CountingStrategy {
    @Override
    public void count(int[] array) {
        System.out.println("Была выбрана стратегия через HasMap!");
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int element : array) {
            countMap.put(element, countMap.getOrDefault(element, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            System.out.println("Элемент " + entry.getKey() + " встретился " + entry.getValue() + " раз(-а)");
        }
    }
}
