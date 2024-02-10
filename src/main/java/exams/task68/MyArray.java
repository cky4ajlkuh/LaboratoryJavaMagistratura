package exams.task68;

import java.io.Serializable;
import java.util.Random;

public class MyArray implements Serializable {
    private int[] array;

    public MyArray(int size) {
        this.array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = 1 + new Random().nextInt(10);
        }
    }

    public MyArray() {

    }

    public int size() {
        return array.length;
    }

    public int get(int index) {
        return index < size() ? array[index] : -1;
    }

    public void set(int index, int value) {
        if (get(index) != -1) array[index] = value;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public static int method(MyArray array, int k, int n) {
        int count = 0, check = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == k) {
                count++;
            } else {
                if (count >= n) {
                    check++;
                }
                count = 0;
            }
            if (count >= n && array.size() - 1 == i) {
                check++;
            }
        }
        return check;
    }
}
