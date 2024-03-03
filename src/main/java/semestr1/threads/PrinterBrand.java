package semestr1.threads;

import semestr1.Transport;

public class PrinterBrand implements Runnable {

    private final Transport vehicle;

    public PrinterBrand(Transport vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        System.out.println("Марка: " + vehicle.getBrand());
    }
}
