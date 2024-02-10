package labs2.lab1;

import labs1.Transport;
import labs1.entities.Motorcycle;

public class MotoFactory implements TransportFactory {
    @Override
    public Transport createInstance(String brand, int size) {
        return new Motorcycle(brand, size);
    }
}
