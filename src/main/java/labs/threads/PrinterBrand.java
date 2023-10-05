package labs.threads;

import labs.TransportVehicle;

public class PrinterBrand implements Runnable {

    private final TransportVehicle vehicle;

    public PrinterBrand(TransportVehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        System.out.println("Марка: " + vehicle.getBrand());
    }
}
