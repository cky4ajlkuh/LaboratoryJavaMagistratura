package labs.first.threads;

import labs.first.TransportVehicle;
import labs.first.Viewer;

public class PrinterPrices extends Thread {
    TransportVehicle vehicle;

    public PrinterPrices(TransportVehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        Viewer.printAllPrices(vehicle);
    }
}
