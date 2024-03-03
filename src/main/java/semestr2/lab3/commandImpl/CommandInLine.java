package semestr2.lab3.commandImpl;

import semestr1.entities.Car;
import semestr1.exceptions.NoSuchModelNameException;
import semestr2.lab3.Command;

import java.io.OutputStream;
import java.io.PrintWriter;

public class CommandInLine implements Command {
    @Override
    public void execute(OutputStream stream, Car car) {
        try (PrintWriter writer = new PrintWriter(stream)) {
            StringBuilder info = new StringBuilder("Type: " + car.getClass().getSimpleName());
            info.append("; Brand: ").append(car.getBrand());
            info.append("; Amount: ").append(car.getSize());
            for (int i = 0; i < car.getSize(); i++) {
                info.append(" model ").append(i).append("; price: ").append(car.getPrice(car.getNamesOfModels()[i])).append("; name: ").append(car.getNamesOfModels()[i]);
            }
            writer.println(info);
        } catch (NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
    }
}
