package exams.exam;

public class Matrix {

    private int[][] m;

    private int size() {
        return m.length;
    }

    public static int[][] method(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            if (m[i][i] != 0) {
                for (int j = 0; j < m.length; j++)
                    if (m[i][j] == 0) {
                        int[] str = m[j];
                        m[j] = m[i];
                        m[i] = str;
                        //i = 0;
                    }
            }
        }
        return m;
    }
}