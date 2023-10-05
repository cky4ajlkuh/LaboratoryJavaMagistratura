package labs;

import labs.exceptions.DuplicateModelNameException;
import labs.exceptions.ModelPriceOutOfBoundsException;
import labs.exceptions.NoSuchModelNameException;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements TransportVehicle {
    private class Model implements Serializable {
        private static final long serialVersionUID = 4153125143511980746L;
        private String nameOfModel;
        private double price;

        private Model(String nameOfModel, double price) {
            this.nameOfModel = nameOfModel;
            this.price = price;
        }
    }

    private String brand;

    private Model[] models;

    public Car(String brand, int size) {
        this.brand = brand;
        models = new Model[size];
        for (int i = 0; i < size; i++) {
            models[i] = new Model(i + "_model", i);
        }
    }


    public void setNameOfModel(String oldNameOfModel, String newNameOfModel) throws NoSuchModelNameException, DuplicateModelNameException {
        int counter = 0;
        for (Model model : models) {
            if (model.nameOfModel.equals(newNameOfModel)) {
                throw new DuplicateModelNameException("Модель " + newNameOfModel + " уже существует!");
            }
        }
        for (Model model : models) {
            if (model.nameOfModel.equals(oldNameOfModel)) {
                model.nameOfModel = newNameOfModel;
            } else {
                counter++;
            }
        }
        if (counter == models.length) {
            throw new NoSuchModelNameException("Модель " + oldNameOfModel + " отсутствует!");
        }
    }

    public String[] getNamesOfModels() {
        String[] namesOfModels = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            namesOfModels[i] = models[i].nameOfModel;
        }
        return namesOfModels;
    }

    public double getPrice(String nameOfModel) throws NoSuchModelNameException {
        for (Model model : models) {
            if (nameOfModel.equals(model.nameOfModel)) {
                return model.price;
            }
        }
        throw new NoSuchModelNameException("Модель " + nameOfModel + " отсутствует!");
    }

    public void setPrice(String nameOfModel, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Нельзя устанавливать отрицательную цену!");
        }
        int counter = 0;
        for (Model model : models) {
            if (nameOfModel.equals(model.nameOfModel)) {
                model.price = price;
                break;
            } else {
                counter++;
            }
        }
        if (counter == models.length) {
            throw new NoSuchModelNameException("Модель " + nameOfModel + " отсутствует!");
        }
    }

    public double[] getPrices() {
        double[] prices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            prices[i] = models[i].price;
        }
        return prices;
    }

    public void addNameAndPrice(String nameOfModel, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Нельзя устанавливать отрицательную цену!");
        }
        for (Model model : models) {
            if (model.nameOfModel.equals(nameOfModel)) {
                throw new DuplicateModelNameException("Модель " + nameOfModel + " уже существует!");
            }
        }
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(nameOfModel, price);
    }

    public void removeModel(String nameOfModel, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Нельзя устанавливать отрицательную цену!");
        }
        int count = 0;
        int index = 0;
        for (int i = 0; i < models.length; i++) {
            if (!models[i].nameOfModel.equals(nameOfModel)) {
                count++;
            } else {
                index = i;
            }
            if (count == models.length) {
                throw new NoSuchModelNameException("Модель " + nameOfModel + " отсутствует!");
            }
        }
        System.arraycopy(models, index + 1, models, index, models.length - index - 1);
        models = Arrays.copyOf(models, models.length - 1);
    }

    public int getSize() {
        return models.length;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
