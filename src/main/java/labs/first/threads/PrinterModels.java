package labs.first.threads;

import labs.first.TransportVehicle;
import labs.first.Viewer;

public class PrinterModels extends Thread {
    TransportVehicle vehicle;

    public PrinterModels(TransportVehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        Viewer.printAllModels(vehicle);
    }
}
