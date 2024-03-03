package semestr2.lab3.strategyimpl;

import semestr2.lab3.CountingStrategy;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamStrategy implements CountingStrategy {
    @Override
    public void count(int[] array) {
        System.out.println("Была выбрана стратегия через Stream!");
        IntStream.of(array)
                .boxed()
                .collect(Collectors.groupingBy(s -> s))
                .forEach((k, v) -> System.out.println("Элемент " + k + " встретился " + v.size() + " раз(-а)"));
    }
}
