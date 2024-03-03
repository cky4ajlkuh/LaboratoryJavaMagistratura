package semestr1;

import semestr1.exceptions.DuplicateModelNameException;
import semestr1.exceptions.NoSuchModelNameException;

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

    void accept(Visitor visitor) throws NoSuchModelNameException;
}
