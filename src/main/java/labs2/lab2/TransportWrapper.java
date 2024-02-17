package labs2.lab2;

import labs1.Transport;
import labs1.exceptions.DuplicateModelNameException;
import labs1.exceptions.NoSuchModelNameException;

public class TransportWrapper implements Transport {

    private final Transport transport;

    public TransportWrapper(Transport transport) {
        this.transport = transport;
    }

    @Override
    public synchronized String[] getNamesOfModels() {
        return transport.getNamesOfModels();
    }

    @Override
    public synchronized void setNameOfModel(String oldNameOfModel, String newNameOfModel) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setNameOfModel(oldNameOfModel, newNameOfModel);
    }

    @Override
    public synchronized double getPrice(String nameOfModel) throws NoSuchModelNameException {
        return transport.getPrice(nameOfModel);
    }

    @Override
    public synchronized void setPrice(String nameOfModel, double price) throws NoSuchModelNameException {
        transport.setPrice(nameOfModel, price);
    }

    @Override
    public synchronized double[] getPrices() {
        return transport.getPrices();
    }

    @Override
    public synchronized void addNameAndPrice(String nameOfModel, double price) throws DuplicateModelNameException {
        transport.addNameAndPrice(nameOfModel, price);
    }

    @Override
    public synchronized void removeModel(String nameOfModel, double price) throws NoSuchModelNameException {
        transport.removeModel(nameOfModel, price);
    }

    @Override
    public synchronized int getSize() {
        return transport.getSize();
    }

    @Override
    public synchronized String getBrand() {
        return transport.getBrand();
    }

    @Override
    public synchronized void setBrand(String brand) {
        transport.setBrand(brand);
    }
}
