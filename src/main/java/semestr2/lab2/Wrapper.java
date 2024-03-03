package semestr2.lab2;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Wrapper {

    private final BufferedInputStream inputStream;
    private final BufferedOutputStream outputStream;

    public Wrapper(OutputStream outputStream, InputStream inputStream) {
        this.inputStream = new BufferedInputStream(inputStream);
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    public void writer(String[] array) throws IOException {
        outputStream.write(String.join("", array).getBytes(), 0, String.join("", array).getBytes().length);
        outputStream.flush();
        outputStream.close();
    }

    public void reader() throws IOException {
        byte[] array = new byte[inputStream.available()];
        for (int i = 0; i < array.length; i++) {
            array[i] = (byte) inputStream.read();
        }
        System.out.println(new String(array, StandardCharsets.UTF_8));
        inputStream.close();
    }

}
