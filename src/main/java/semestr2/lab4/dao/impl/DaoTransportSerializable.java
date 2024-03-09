package semestr2.lab4.dao.impl;

import semestr1.Transport;
import semestr1.entities.Car;
import semestr2.lab4.dao.DaoTransport;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DaoTransportSerializable implements DaoTransport {

    private final String filename;

    public DaoTransportSerializable(String filename) {
        this.filename = filename;
    }

    @Override
    public Transport[] readTransport() throws Exception {
        writeToFile();
        return readFromFile();
    }

    private void writeToFile() throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            Transport[] output = new Transport[]{new Car("VOLVO", 5), new Car("MAZDA", 2), new Car("AUDI", 3)};
            stream.writeObject(output);
        }
    }

    private Transport[] readFromFile() throws Exception {
        try (ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            return (Transport[]) stream.readObject();
        }
    }
}
