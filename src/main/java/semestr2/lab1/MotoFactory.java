package semestr2.lab1;

import semestr1.Transport;
import semestr1.entities.Motorcycle;

public class MotoFactory implements TransportFactory {
    @Override
    public Transport createInstance(String brand, int size) {
        return new Motorcycle(brand, size);
    }
}
