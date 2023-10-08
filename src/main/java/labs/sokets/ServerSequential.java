package labs.sokets;

import labs.Car;
import labs.Motorcycle;
import labs.TransportVehicle;
import labs.Viewer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSequential {
    private static Socket clientSocket;
    private static ObjectInputStream stream;
    private static BufferedWriter writer;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(4004)) {
            System.out.println("Сервер запущен!");
            clientSocket = serverSocket.accept();
            stream = new ObjectInputStream(clientSocket.getInputStream());
            Object o = stream.readObject();
            TransportVehicle vehicle = o.getClass().getSimpleName().equals(Car.class.getSimpleName()) ? (Car) o : (Motorcycle) o;
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            writer.write(String.valueOf(Viewer.arithmeticMean(vehicle)));
            System.out.println("Сервер завершил работу!");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            writer.flush();
            stream.close();
            clientSocket.close();
        }
    }
}
