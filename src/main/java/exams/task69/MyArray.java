package exams.task69;

import java.io.Serializable;
import java.util.Random;

public class MyArray implements Serializable {
    private int[] array;

    public MyArray(int size) {
        this.array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(200) - 100;
        }
    }

    public MyArray() {

    }

    public int size() {
        return array.length;
    }

    public int get(int index) {
        return index < array.length ? array[index] : -1;
    }

    public void set(int index, int value) {
        if (get(index) != -1) {
            array[index] = value;
        }
    }

    public int[] toArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public static int returnIndex(MyArray array) {
        int index = 0, counter = 0, max = 0;
        MyArray signs = new MyArray(array.size() - 1);
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) * array.get(i + 1) < 0) {
                signs.set(i, -1);
            } else {
                signs.set(i, 1);
            }
        }
        for (int i = 0; i < signs.size() - 1; i++) {
            if (signs.get(i) == signs.get(i + 1)) {
                counter++;
            } else {
                if (max < counter) {
                    max = counter;
                    index = i - max;
                } else {
                    counter = 0;
                }
            }
        }
        return index;
    }
}
