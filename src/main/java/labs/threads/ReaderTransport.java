package labs.threads;

import labs.Car;
import labs.TransportVehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class ReaderTransport implements Runnable {
    private final String filename;
    private final ArrayBlockingQueue<TransportVehicle> queue;

    public ReaderTransport(String filename, ArrayBlockingQueue<TransportVehicle> queue) {
        this.filename = filename;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String brand = reader.readLine();
            TransportVehicle vehicle = new Car(brand, 0);
            //queue.add(vehicle);
            queue.put(vehicle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
