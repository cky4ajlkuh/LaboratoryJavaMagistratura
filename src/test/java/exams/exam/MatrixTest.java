package exams.exam;

import exams.task66.MyArray;
import org.junit.jupiter.api.Test;

class MatrixTest {


    @Test
    public void test() {
        Matrix.method(new int[][]{{1, 0, 1, 1},
                                  {1, 1, 0, 1},
                                  {0, 1, 1, 1},
                                  {1, 1, 1, 0}});

    }

}