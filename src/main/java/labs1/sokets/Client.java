package labs1.sokets;

import labs1.entities.Car;
import labs1.Transport;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static ObjectOutputStream stream;
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        Transport vehicle = new Car("BMW", 11);
        try {
            socket = new Socket("localhost", 4004);
            stream = new ObjectOutputStream(socket.getOutputStream());
            stream.writeObject(vehicle);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Среднее арифметическое цен = " + reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            socket.close();
            reader.close();
            stream.flush();
        }
    }
}
