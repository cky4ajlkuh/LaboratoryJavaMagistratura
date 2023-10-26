package labs.threads;

import labs.TransportVehicle;
import labs.entities.Viewer;

import java.util.concurrent.locks.Lock;

public class PrinterPricesLock implements Runnable {
    private final TransportVehicle vehicle;
    private final Lock lock;

    public PrinterPricesLock(TransportVehicle vehicle, Lock lock) {
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
