package labs1.threads;

public class PrinterModelsSync implements Runnable {
    private final TransportSynchronizer synchronizer;

    public PrinterModelsSync(TransportSynchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        try {
            while (synchronizer.canPrintModel()) {
                synchronizer.printModel();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
