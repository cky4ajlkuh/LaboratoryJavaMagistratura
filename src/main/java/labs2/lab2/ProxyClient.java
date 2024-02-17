package labs2.lab2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ProxyClient {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            System.out.print("Введите первое число:");
            double num1 = new Scanner(System.in).nextDouble();
            System.out.print("Введите второе число:");
            double num2 = new Scanner(System.in).nextDouble();
            System.out.println("Результат: " + new Proxy().multiply(num1, num2, reader, writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Proxy {
        public double multiply(double num1, double num2, BufferedReader reader, BufferedWriter writer) throws IOException {
            writer.write(num1 + " " + num2 + "\n");
            System.out.println("Proxy отправил сообщение!");
            writer.flush();
            return Double.parseDouble(reader.readLine());
        }
    }
}
