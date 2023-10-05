package labs;

import labs.threads.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private final static TransportVehicle vehicle = new Car("BMW", 10);
    private final static TransportSynchronizer synchronizer = new TransportSynchronizer(vehicle);

    private final static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        // 1-oe
        Thread prices = new PrinterPrices(vehicle);
        Thread models = new PrinterModels(vehicle);
        prices.setPriority(Thread.MAX_PRIORITY);
        models.setPriority(Thread.MIN_PRIORITY);
        models.start();
        prices.start();

        // 2-oe

        Thread syncModels = new Thread(new PrinterModelsSync(synchronizer));
        Thread syncPrices = new Thread(new PrinterPricesSync(synchronizer));
        syncModels.start();
        syncPrices.start();

        // 3-ьe
        Thread lockModels = new Thread(new PrinterModelsLock(vehicle, lock));
        Thread lockPrices = new Thread(new PrinterPricesLock(vehicle, lock));
        lockModels.start();
        lockPrices.start();

        // 4-oe
        ExecutorService service = Executors.newFixedThreadPool(2);
        TransportVehicle[] vehicles = new TransportVehicle[]{
                new Car("Kalina", 2),
                new Car("Vesta", 3),
                new Motorcycle("Jupiter", 4),
                new Motorcycle("URAL", 5)};
        for (int i = 0; i < 4; i++) {
            service.submit(new PrinterBrand(vehicles[i]));
        }
        service.shutdown();

        // 5-oe
        ArrayBlockingQueue<TransportVehicle> queue = new ArrayBlockingQueue<>(1);
        String[] files = new String[]{"file0.txt", "file1.txt", "file2.txt", "file3.txt", "file4.txt"};
        for (String file : files) {
            Thread thread = new Thread(new ReaderTransport(file, queue));
            thread.start();
        }
        for (int i = 0; i != 5; i++) {
            System.out.println(queue.take().getBrand());
        }
    }
}
