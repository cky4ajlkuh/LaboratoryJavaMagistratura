package semestr2.lab1;

import semestr1.entities.Car;

public class AutoFactory implements TransportFactory {
    @Override
    public Car createInstance(String brand, int size) {
        return new Car(brand, size);
    }
}
