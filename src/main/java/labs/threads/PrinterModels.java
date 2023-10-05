package labs.threads;

import labs.TransportVehicle;
import labs.Viewer;

public class PrinterModels extends Thread {
    private final TransportVehicle vehicle;

    public PrinterModels(TransportVehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        Viewer.printAllModels(vehicle);
    }
}
