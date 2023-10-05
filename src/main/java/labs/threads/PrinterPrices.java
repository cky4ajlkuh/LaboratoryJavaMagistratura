package labs.threads;

import labs.TransportVehicle;
import labs.Viewer;

public class PrinterPrices extends Thread {
    private final TransportVehicle vehicle;

    public PrinterPrices(TransportVehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        Viewer.printAllPrices(vehicle);
    }
}
