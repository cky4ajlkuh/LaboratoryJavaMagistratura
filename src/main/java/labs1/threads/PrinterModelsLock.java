package labs1.threads;

import labs1.Transport;
import labs1.entities.Viewer;

import java.util.concurrent.locks.Lock;

public class PrinterModelsLock implements Runnable {
    private final Transport vehicle;
    private final Lock lock;

    public PrinterModelsLock(Transport vehicle, Lock lock) {
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
