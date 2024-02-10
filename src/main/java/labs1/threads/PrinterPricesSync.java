package labs1.threads;

public class PrinterPricesSync implements Runnable {

    private final TransportSynchronizer synchronizer;

    public PrinterPricesSync(TransportSynchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        try {
            while (synchronizer.canPrintPrice()) {
                synchronizer.printPrice();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
