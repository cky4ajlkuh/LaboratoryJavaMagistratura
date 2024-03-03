package semestr1.threads;

import semestr1.Transport;
import semestr1.entities.Viewer;

import java.util.concurrent.locks.Lock;

public class PrinterPricesLock implements Runnable {
    private final Transport vehicle;
    private final Lock lock;

    public PrinterPricesLock(Transport vehicle, Lock lock) {
        this.vehicle = vehicle;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            Viewer.printAllPrices(vehicle);
        } finally {
            lock.unlock();
        }
    }

}
