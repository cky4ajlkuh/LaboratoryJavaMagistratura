package exams.task68;

import org.junit.jupiter.api.Test;

class MyArrayTest {

    @Test
    public void test() {
        MyArray array = new MyArray();
        array.setArray(new int[]{1, 2, 3, 4, 1, 1, 3, 1, 1, 1, 3, 4, 5, 1, 1, 1, 1});
        MyArray.method(array, 1, 3);
    }
}