package semestr2.lab3;

import semestr1.entities.Car;

import java.io.OutputStream;

public interface Command {
    void execute(OutputStream stream, Car car);
}
