package semestr1.threads;

import semestr1.Transport;
import semestr1.entities.Viewer;

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
