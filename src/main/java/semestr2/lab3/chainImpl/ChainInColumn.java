package semestr2.lab3.chainImpl;

import semestr1.Transport;
import semestr1.exceptions.NoSuchModelNameException;
import semestr2.lab3.TransportHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ChainInColumn implements TransportHandler {

    private TransportHandler handler;

    @Override
    public void write(Transport transport) {
        if (transport.getSize() <= 3) {
            try (PrintWriter writer = new PrintWriter(Files.newOutputStream(Paths.get("ChainColumn.txt")))) {
                writer.println("Type: " + transport.getClass().getSimpleName());
                writer.println("Brand: " + transport.getBrand());
                writer.println("Amount: " + transport.getSize());
                for (int i = 0; i < transport.getSize(); i++) {
                    writer.println("model " + i + "; price: " + transport.getPrice(transport.getNamesOfModels()[i]) + "; name: " +
                            transport.getNamesOfModels()[i]);
                }
            } catch (IOException | NoSuchModelNameException e) {
                throw new RuntimeException(e);
            }
        } else if (handler != null) {
            handler.write(transport);
        }
    }

    @Override
    public void hasNext(TransportHandler handler) {
        this.handler = handler;
    }
}
