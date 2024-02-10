package exams.task77;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             DataOutputStream stream = new DataOutputStream(new FileOutputStream(args[1]))) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[][] matrix = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
            stream.writeInt(matrix.length);
            stream.writeInt(matrix[0].length);
            for (int i = 0; i < matrix.length; i++) {
                String line = "";
                for (int j = 0; j < matrix[0].length; j++) {
                    line = line + matrix[i][j] + " ";
                }
                stream.writeUTF(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
