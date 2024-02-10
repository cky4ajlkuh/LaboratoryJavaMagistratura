package labs1.threads;

import labs1.Transport;
import labs1.entities.Viewer;

public class PrinterPrices extends Thread {
    private final Transport vehicle;

    public PrinterPrices(Transport vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        Viewer.printAllPrices(vehicle);
    }
}
