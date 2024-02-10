package exams.task68;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4004);
             Socket clientSocket = serverSocket.accept();
             ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
            MyArray array = (MyArray) inputStream.readObject();
            int k = new Scanner(System.in).nextInt();
            int n = new Scanner(System.in).nextInt();
            int val = MyArray.method(array, k, n);
            outputStream.writeObject(val);
        } catch (IOException e) {
            System.out.println("input/output exception!");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        }
    }
}
