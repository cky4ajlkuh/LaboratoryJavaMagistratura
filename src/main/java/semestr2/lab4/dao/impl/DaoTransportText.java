package semestr2.lab4.dao.impl;

import semestr1.Transport;
import semestr1.entities.Car;
import semestr1.entities.Motorcycle;
import semestr2.lab4.dao.DaoTransport;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DaoTransportText implements DaoTransport {

    private final String filename;

    public DaoTransportText(String filename) {
        this.filename = filename;
    }

    @Override
    public Transport[] readTransport() throws Exception {
        return readFromFile();
    }

    private Transport[] readFromFile() throws Exception {
        List<Transport> list = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String type = buffer.readLine();
            while (type != null) {
                String brand = buffer.readLine();
                Transport vehicle = (type.equals(Car.class.getSimpleName())) ? new Car(brand, 0) : new Motorcycle(brand, 0);
                int size = Integer.parseInt(buffer.readLine());
                for (int i = 0; i < size; i++) {
                    String name = buffer.readLine();
                    double price = Double.parseDouble(buffer.readLine());
                    vehicle.addNameAndPrice(name, price);
                }
                list.add(vehicle);
                type = buffer.readLine();
            }
        }
        return list.toArray(new Transport[0]);
    }

}
