package exams.task26;

import java.util.Random;

public class Helper {
    private final int[] array;

    public Helper(int size) {
        this.array = new int[size];
    }

    public synchronized void read() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public synchronized void write() {
        for (int i = 0; i < array.length; i++)
            array[i] = 1 + new Random().nextInt(10);
    }
}
