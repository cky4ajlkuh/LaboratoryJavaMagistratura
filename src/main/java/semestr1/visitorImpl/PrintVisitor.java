package semestr1.visitorImpl;

import semestr1.Visitor;
import semestr1.entities.Car;
import semestr1.entities.Motorcycle;
import semestr1.exceptions.NoSuchModelNameException;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) throws NoSuchModelNameException {
        StringBuilder info = new StringBuilder("Type: " + car.getClass().getSimpleName());
        info.append("; Brand: ").append(car.getBrand());
        info.append("; Amount: ").append(car.getSize());
        for (int i = 0; i < car.getSize(); i++) {
            info.append(" model ").append(i).append("; price: ").append(car.getPrice(car.getNamesOfModels()[i])).append("; name: ").append(car.getNamesOfModels()[i]);
        }
        System.out.println(info);
    }

    @Override
    public void visit(Motorcycle motorcycle) throws NoSuchModelNameException {
        System.out.println("Type: " + motorcycle.getClass().getSimpleName());
        System.out.println("Brand: " + motorcycle.getBrand());
        System.out.println("Amount: " + motorcycle.getSize());
        for (int i = 0; i < motorcycle.getSize(); i++) {
            System.out.println("model " + i + "; price: " + motorcycle.getPrice(motorcycle.getNamesOfModels()[i]) + "; name: " +
                    motorcycle.getNamesOfModels()[i]);
        }
    }
}
