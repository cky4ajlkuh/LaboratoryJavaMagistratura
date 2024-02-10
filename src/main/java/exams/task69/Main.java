package exams.task69;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 2) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(args[1]));
                 ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream((args[0])))) {
                /*MyArray myArray = new MyArray(30);
                outputStream.writeObject(myArray);*/
                MyArray array = (MyArray) inputStream.readObject();
                outputStream.writeObject(MyArray.returnIndex(array));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("input params not correct!");
        }
    }
}
