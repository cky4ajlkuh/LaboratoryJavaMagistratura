package labs1.threads;

import labs1.Transport;
import labs1.entities.Viewer;

public class PrinterModels extends Thread {
    private final Transport vehicle;

    public PrinterModels(Transport vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        Viewer.printAllModels(vehicle);
    }
}
