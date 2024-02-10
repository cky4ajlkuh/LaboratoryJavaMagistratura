package exams.task67;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length == 2) {
            try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(args[1]));
                 Scanner scanner = new Scanner(new FileReader(args[0]))) {
                MyArray array = new MyArray(scanner.nextInt());
                for (int i = 0; i < array.size(); i++) {
                    if (scanner.hasNext()) {
                        array.set(i, scanner.nextInt());
                    }
                }
                stream.writeObject(MyArray.sort(array));
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            } catch (IOException e) {
                System.out.println("File is empty!");
            }
        } else {
            System.out.println("not correct input parameters!");
        }
    }
}
