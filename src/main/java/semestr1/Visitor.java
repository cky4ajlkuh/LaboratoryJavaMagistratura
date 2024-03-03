package semestr1;

import semestr1.entities.Car;
import semestr1.entities.Motorcycle;
import semestr1.exceptions.NoSuchModelNameException;

public interface Visitor {
    void visit(Car car) throws NoSuchModelNameException;
    void visit(Motorcycle motorcycle) throws NoSuchModelNameException;
}
