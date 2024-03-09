package semestr2.lab4;

import semestr1.Transport;
import semestr1.entities.Motorcycle;
import semestr1.entities.Viewer;
import semestr2.lab4.dao.DaoTransport;
import semestr2.lab4.dao.impl.DaoTransportSerializable;
import semestr2.lab4.dao.impl.DaoTransportText;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        FileWriter writer = new FileWriter("TransportText.txt");
        Viewer.writeVehicle(new Motorcycle("Suzuki", 2), writer);
        Viewer.writeVehicle(new Motorcycle("BMW", 3), writer);
        DaoTransport transportText = new DaoTransportText("TransportText.txt");
        DaoTransport transportSerial = new DaoTransportSerializable("TransportSerial.bin");
        List<Transport> list = new ArrayList<>(Arrays.asList(transportSerial.readTransport()));
        list.addAll(Arrays.asList(transportText.readTransport()));
        for (Transport transport : list) {
            System.out.println(transport.getBrand());
        }
    }
}
