package labs2.lab2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxyServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000);
             Socket clientSocket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            String[] message = reader.readLine().split(" ");
            writer.write(String.valueOf(Double.parseDouble(message[0]) * Double.parseDouble(message[1])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
