package labs1;

import labs1.exceptions.DuplicateModelNameException;
import labs1.exceptions.NoSuchModelNameException;

import java.io.Serializable;

public interface Transport extends Serializable {

    String[] getNamesOfModels();

    void setNameOfModel(String oldNameOfModel, String newNameOfModel) throws NoSuchModelNameException, DuplicateModelNameException;

    double getPrice(String nameOfModel) throws NoSuchModelNameException;

    void setPrice(String nameOfModel, double price) throws NoSuchModelNameException;

    double[] getPrices();

    void addNameAndPrice(String nameOfModel, double price) throws DuplicateModelNameException;

    void removeModel(String nameOfModel, double price) throws NoSuchModelNameException;

    int getSize();

    String getBrand();

    void setBrand(String brand);
}
