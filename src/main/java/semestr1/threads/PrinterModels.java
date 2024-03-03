package semestr1.threads;

import semestr1.Transport;
import semestr1.entities.Viewer;

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
