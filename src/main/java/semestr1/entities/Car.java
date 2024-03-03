package semestr1.entities;

import semestr1.Transport;
import semestr1.Visitor;
import semestr1.exceptions.DuplicateModelNameException;
import semestr1.exceptions.ModelPriceOutOfBoundsException;
import semestr1.exceptions.NoSuchModelNameException;
import semestr2.lab3.Command;
import semestr2.lab3.commandImpl.CommandInColumn;
import semestr2.lab3.commandImpl.CommandInLine;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Car implements Transport, Cloneable {
    protected static class Model implements Serializable, Cloneable {
        private static final long serialVersionUID = 4153125143511980746L;
        private String nameOfModel;
        private double price;

        private Model(String nameOfModel, double price) {
            this.nameOfModel = nameOfModel;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Model {" +
                    "nameOfModel = '" + nameOfModel + '\'' +
                    ", price = " + price +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    private class CarIterator implements Iterator<Model> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < getSize();
        }

        @Override
        public Model next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return models[index++];
        }
    }

    public static class Memento implements Serializable {
        private byte[] bytes;
        private void setAuto(Car car) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try (ObjectOutputStream stream = new ObjectOutputStream(outputStream)) {
                stream.writeObject(car);
                bytes = outputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public Car getAuto() throws IOException {
            try (ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
                return (Car) stream.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car clone = (Car) super.clone();
        clone.models = models.clone();
        for (int i = 0; i < getSize(); i++) {
            clone.models[i] = (Model) models[i].clone();
        }
        return clone;
    }

    public void print(OutputStream stream, Command command) {
        command.execute(stream, this);
    }

    public void setPrintCommand(String command) throws IOException {
        if (command.equals("line")) {
            print(Files.newOutputStream(new File("CommandLine.txt").toPath()), new CommandInLine());
        } else if (command.equals("column")) {
            print(Files.newOutputStream(new File("CommandColumn.txt").toPath()), new CommandInColumn());
        }
    }

    public CarIterator iterator() {
        return new CarIterator();
    }

    public Memento createMemento() {
        Memento memento = new Memento();
        memento.setAuto(this);
        return memento;
    }

    public void setMemento(Memento memento) throws IOException {
        Car car = memento.getAuto();
        brand = car.brand;
        models = car.models;
    }

    @Override
    public void accept(Visitor visitor) throws NoSuchModelNameException {
        visitor.visit(this);
    }
}
