package semestr2.lab3.commandImpl;

import semestr1.entities.Car;
import semestr1.exceptions.NoSuchModelNameException;
import semestr2.lab3.Command;

import java.io.OutputStream;
import java.io.PrintWriter;

public class CommandInColumn implements Command {
    @Override
    public void execute(OutputStream stream, Car car) {
        try (PrintWriter writer = new PrintWriter(stream)) {
            writer.println("Type: " + car.getClass().getSimpleName());
            writer.println("Brand: " + car.getBrand());
            writer.println("Amount: " + car.getSize());
            for (int i = 0; i < car.getSize(); i++) {
                writer.println("model " + i + "; price: " + car.getPrice(car.getNamesOfModels()[i]) + "; name: " +
                        car.getNamesOfModels()[i]);
            }
        } catch (NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
    }
}
