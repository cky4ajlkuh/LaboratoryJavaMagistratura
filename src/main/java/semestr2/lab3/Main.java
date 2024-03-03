package semestr2.lab3;

import semestr1.entities.Car;
import semestr1.entities.Motorcycle;
import semestr1.exceptions.NoSuchModelNameException;
import semestr1.visitorImpl.PrintVisitor;
import semestr2.lab3.chainImpl.ChainInColumn;
import semestr2.lab3.chainImpl.ChainInLine;
import semestr2.lab3.strategyimpl.HasMapStrategy;
import semestr2.lab3.strategyimpl.SelectorStrategy;

import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchModelNameException {
        // Chain of Responsibility
        TransportHandler line = new ChainInLine();
        TransportHandler column = new ChainInColumn();
        line.hasNext(column);
        line.write(new Motorcycle("BMW", 4));
        line.write(new Car("Audi", 2));

        // Command
        Car car = new Car("Volvo", 10);
        car.setPrintCommand("line");
        car.setPrintCommand("column");

        //Strategy
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(new File(args[0]).toPath()));
             ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(new File(args[0]).toPath()))) {
            outputStream.writeObject(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 3, 4, 4, 8});
            SelectorStrategy strategy = new SelectorStrategy(new HasMapStrategy());
            strategy.count((int[]) inputStream.readObject());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Visitor
        Car auto = new Car("auto", 4);
        Motorcycle moto = new Motorcycle("moto", 5);
        PrintVisitor visitor = new PrintVisitor();
        auto.accept(visitor);
        moto.accept(visitor);
    }
}
