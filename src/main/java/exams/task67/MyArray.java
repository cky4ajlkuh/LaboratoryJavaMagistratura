package exams.task67;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class MyArray implements Serializable {
    private int[] array;

    public MyArray() {

    }

    public MyArray(int size) {
        this.array = new int[size];
        /*for (int i = 0; i < size; i++) {
            array[i] = 1 + new Random().nextInt(10);
        }*/
    }

    public static int[] sort(MyArray array) {
        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(i) == array.get(j)) {
                    System.arraycopy(array.toArray(), i + 1, array.toArray(), i, array.size() - i - 1);
                    array.setArray(Arrays.copyOf(array.toArray(), array.size() - 1));
                    i = 0;
                }
            }
        }
        return array.toArray();
    }

    public int size() {
        return array.length;
    }

    public void set(int index, int value) {
        array[index] = value;
    }

    public int get(int index) {
        return array[index];
    }

    public int[] toArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

}
