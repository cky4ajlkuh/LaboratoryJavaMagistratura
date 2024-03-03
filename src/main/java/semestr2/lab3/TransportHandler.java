package semestr2.lab3;

import semestr1.Transport;

public interface TransportHandler {

    void write(Transport transport);

    void hasNext(TransportHandler chain);
}
