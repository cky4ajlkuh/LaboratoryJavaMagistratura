package exams.task67;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MyArrayTest {

    @Test
    public void test() {
        MyArray array = new MyArray(20);
        System.out.println(Arrays.toString(array.toArray()));
        System.out.println(Arrays.toString(MyArray.sort(array)));
    }

}