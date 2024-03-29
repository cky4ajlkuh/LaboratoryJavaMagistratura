package semestr1.sokets;

import semestr1.entities.Car;
import semestr1.entities.Motorcycle;
import semestr1.Transport;
import semestr1.entities.Viewer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerParallel implements Runnable {
    private final Socket clientSocket;
    private static ObjectInputStream stream;
    private static BufferedWriter writer;

    public ServerParallel(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            stream = new ObjectInputStream(clientSocket.getInputStream());
            Object o = stream.readObject();
            Transport vehicle = o.getClass().getSimpleName().equals(Car.class.getSimpleName()) ? (Car) o : (Motorcycle) o;
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            writer.write(String.valueOf(Viewer.arithmeticMean(vehicle)));
            writer.flush();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(4004)) {
            System.out.println("Сервер запущен!");
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(new ServerParallel(clientSocket));
            thread.start();
            System.out.println("Сервер завершил работу!");
        } finally {
            stream.close();
        }
    }
}
