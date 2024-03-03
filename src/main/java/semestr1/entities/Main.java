package semestr1.entities;

import semestr1.exceptions.DuplicateModelNameException;
import semestr1.exceptions.NoSuchModelNameException;

import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, IOException, NoSuchModelNameException {
        // Iterator
        Car car = new Car("Cherry", 4);
        Iterator<Car.Model> modelIterator = car.iterator();
        while (modelIterator.hasNext()) {
            System.out.println(modelIterator.next());
        }
        // Memento
        Car JAC = new Car("JAC", 5);
        System.out.println("________before________");
        System.out.println(JAC.getBrand());
        Viewer.printAllModels(JAC);
        Viewer.printAllPrices(JAC);
        // мементо возвращаем
        Car.Memento status = JAC.createMemento();
        JAC.setBrand("GAC");
        JAC.addNameAndPrice("AAA", 900000);
        JAC.setNameOfModel("1_model", "BBB");
        System.out.println("________change________");
        System.out.println(JAC.getBrand());
        Viewer.printAllModels(JAC);
        Viewer.printAllPrices(JAC);
        JAC.setMemento(status);
        System.out.println("________restore________");
        System.out.println(JAC.getBrand());
        Viewer.printAllModels(JAC);
        Viewer.printAllPrices(JAC);
    }
}
