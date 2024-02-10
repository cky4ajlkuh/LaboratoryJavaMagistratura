package exams.task66;

import java.io.Serializable;
import java.util.*;

public class MyArray implements Serializable {
    private Integer[] array;

    public MyArray() {

    }

    public MyArray(int size) {
        /*
        this.array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = 1 + new Random().nextInt(10);
        }*/
        this.array = new Integer[]{1, -7, 2, 3, 6, 4, 4, 4, 4, 6, 6, 3, 7, 7, 7, 3, 8, 9, 9, 9, 9, 9};
    }

    public static Integer[] method(MyArray array, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        Collections.addAll(list, array.getArray());
        int flag = 1;
        if (array.getLength() >= k) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).equals(list.get(i + 1))) {
                    flag++;
                }
                if (!list.get(i).equals(list.get(i + 1))) {
                    if (flag >= k) {
                        for (int j = 0; j < flag; j++) {
                            list.remove(i - j);
                        }
                        i = 0;
                    }
                    flag = 1;
                }
                if (flag >= k && i + 2 == list.size()) {
                    for (int j = 0; j < flag; j++) {
                        list.remove(i + 1 - j);
                    }
                }
            }
        }
        return list.toArray(new Integer[0]);
    }

    public int getLength() {
        return array.length;
    }

    public int getValue(int index) {
        return array[index];
    }

    public void setValue(int index, int value) {
        array[index] = value;
    }

    public Integer[] getArray() {
        return array;
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }

}
