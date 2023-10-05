package labs.threads;

import labs.TransportVehicle;
import labs.Viewer;

import java.util.concurrent.locks.Lock;

public class PrinterModelsLock implements Runnable {
    private final TransportVehicle vehicle;
    private final Lock lock;

    public PrinterModelsLock(TransportVehicle vehicle, Lock lock) {
        this.vehicle = vehicle;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            Viewer.printAllModels(vehicle);
        } finally {
            lock.unlock();
        }
    }
}
