package labs2.lab1;

import labs1.Transport;

public interface TransportFactory {
    Transport createInstance(String brand, int size);
}
