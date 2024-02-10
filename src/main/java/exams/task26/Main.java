package exams.task26;

import java.io.*;
public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
                int size = Integer.parseInt(bufferedReader.readLine());
                Helper helper = new Helper(size);
                Reader reader = new Reader(helper);
                Writer writer = new Writer(helper);
                Thread tReader = new Thread(reader);
                Thread tWriter = new Thread(writer);
                tWriter.start();
                tReader.start();
            } catch (FileNotFoundException e) {
                System.out.println("file not found!");
            } catch (IOException e) {
                System.out.println("file is empty!");
            }
        } else {
            System.out.println("Set file name, pls!");
        }
    }
}
