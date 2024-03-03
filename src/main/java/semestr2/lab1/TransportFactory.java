package semestr2.lab1;

import semestr1.Transport;

public interface TransportFactory {
    Transport createInstance(String brand, int size);
}
