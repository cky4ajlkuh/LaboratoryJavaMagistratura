package exams.task68;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4004);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
            outputStream.writeObject(new MyArray(40));
            System.out.println(inputStream.readObject());
        } catch (IOException e) {
            System.out.println("input/output exception!");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        }
    }
}
