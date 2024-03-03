package semestr1.threads;

import semestr1.entities.Car;
import semestr1.Transport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class ReaderTransport implements Runnable {
    private final String filename;
    private final ArrayBlockingQueue<Transport> queue;

    public ReaderTransport(String filename, ArrayBlockingQueue<Transport> queue) {
        this.filename = filename;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String brand = reader.readLine();
            Transport vehicle = new Car(brand, 0);
            //queue.add(vehicle);
            queue.put(vehicle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
