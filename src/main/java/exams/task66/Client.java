package exams.task66;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static ObjectOutputStream stream;
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        MyArray array = new MyArray(20);
        try {
            socket = new Socket("localhost", 4004);
            stream = new ObjectOutputStream(socket.getOutputStream());
            stream.writeObject(array);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            socket.close();
            reader.close();
            stream.flush();
        }
    }
}
