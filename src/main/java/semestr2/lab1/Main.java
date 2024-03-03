package semestr2.lab1;
import semestr2.lab2.Wrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("out.bin");
        Wrapper adapter = new Wrapper(Files.newOutputStream(path),
                Files.newInputStream(path));
        adapter.writer(new String[]{"Привет", " мир", "!"});
        adapter.reader();
    }
}
