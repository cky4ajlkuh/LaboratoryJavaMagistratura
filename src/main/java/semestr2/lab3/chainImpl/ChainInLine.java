package semestr2.lab3.chainImpl;

import semestr1.Transport;
import semestr1.exceptions.NoSuchModelNameException;
import semestr2.lab3.TransportHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ChainInLine implements TransportHandler {

    private TransportHandler handler;

    @Override
    public void write(Transport transport) {
        if (transport.getSize() > 3) {
            try (PrintWriter writer = new PrintWriter(Files.newOutputStream(Paths.get("ChainLine.txt")))) {
                StringBuilder info = new StringBuilder("Type: " + transport.getClass().getSimpleName());
                info.append("; Brand: ").append(transport.getBrand());
                info.append("; Amount: ").append(transport.getSize());
                for (int i = 0; i < transport.getSize(); i++) {
                    info.append(" model ").append(i).append("; price: ").append(transport.getPrice(transport.getNamesOfModels()[i])).append("; name: ").append(transport.getNamesOfModels()[i]);
                }
                writer.println(info);
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
