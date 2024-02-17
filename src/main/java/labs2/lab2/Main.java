package labs2.lab2;

import java.io.*;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) throws IOException {
        Wrapper wrapper = new Wrapper(Files.newOutputStream(new File("out.bin").toPath()),
                Files.newInputStream(new File("out.bin").toPath()));
        wrapper.writer(new String[]{"Привет", " как", " дела", " ?"});
        wrapper.reader();
    }
}