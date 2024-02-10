package exams.task66;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(4004);
             Socket clientSocket = serverSocket.accept();
             ObjectInputStream stream = new ObjectInputStream(clientSocket.getInputStream());
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            writer.write(Arrays.toString(MyArray.method((MyArray) stream.readObject(), new Scanner(System.in).nextInt())));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
