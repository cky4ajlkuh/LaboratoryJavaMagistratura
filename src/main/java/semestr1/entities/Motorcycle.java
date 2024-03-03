package semestr1.entities;

import semestr1.Transport;
import semestr1.Visitor;
import semestr1.exceptions.DuplicateModelNameException;
import semestr1.exceptions.ModelPriceOutOfBoundsException;
import semestr1.exceptions.NoSuchModelNameException;

import java.io.Serializable;

public class Motorcycle implements Transport, Cloneable {
    private class Model implements Serializable, Cloneable {
        private static final long serialVersionUID = 5165605677394760811L;
        private String nameOfModel;
        private double price;
        Model prev = null;
        Model next = null;

        public Model(String nameOfModel, double price) {
            this.nameOfModel = nameOfModel;
            this.price = price;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    private String brand;
    private Model head;
    private int size;

    {
        long lastModified = System.currentTimeMillis();
        System.out.println("Last modified: " + lastModified);
    }

    public Motorcycle(String brand, int size) {
        head = new Model("0", 0);
        this.brand = brand;
        this.size = size;
        head.prev = head;
        head.next = head;
        for (int i = 0; i < size; i++) {
            Model node = new Model(i + "Name", i + 10);
            node.prev = head.prev;
            node.next = head;
            head.prev.next = node;
            head.prev = node;
        }
    }

    public void setNameOfModel(String oldNameOfModel, String newNameOfModel) throws NoSuchModelNameException, DuplicateModelNameException {
        Model node = head;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (node.nameOfModel.equals(newNameOfModel)) {
                throw new DuplicateModelNameException("Модель " + newNameOfModel + " уже существует!");
            }
            node = node.next;
        }
        node = head;
        for (int i = 0; i < size; i++) {
            if (node.nameOfModel.equals(oldNameOfModel)) {
                node.nameOfModel = newNameOfModel;
            } else {
                count++;
            }
            node = node.next;
        }
        if (count == size) {
            throw new NoSuchModelNameException("Модель " + oldNameOfModel + " отсутствует!");
        }
    }


    public String[] getNamesOfModels() {
        String[] namesOfModels = new String[size];
        Model node = head.next;
        for (int i = 0; i < size; i++) {
            namesOfModels[i] = node.nameOfModel;
            node = node.next;
        }
        return namesOfModels;
    }

    public double getPrice(String nameOfModel) throws NoSuchModelNameException {
        Model node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.nameOfModel.equals(nameOfModel)) {
                return node.price;
            }
            node = node.next;
        }
        throw new NoSuchModelNameException("Модель " + nameOfModel + " отсутствует!");
    }

    public void setPrice(String nameOfModel, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Нельзя устанавливать отрицательную цену!");
        }
        Model node = head.next;
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (node.nameOfModel.equals(nameOfModel)) {
                node.price = price;
                break;
            } else {
                counter++;
            }
            node = node.next;
        }
        if (counter == size) {
            throw new NoSuchModelNameException("Модель " + nameOfModel + " отсутствует!");
        }
    }

    public double[] getPrices() {
        Model node = head.next;
        double[] prices = new double[size];
        for (int i = 0; i < size; i++) {
            prices[i] = node.price;
            node = node.next;
        }
        return prices;
    }

    public void addNameAndPrice(String nameOfModel, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Нельзя устанавливать отрицательную цену!");
        }
        Model next = head.next;
        for (int i = 0; i < size; i++) {
            if (next.nameOfModel.equals(nameOfModel)) {
                throw new DuplicateModelNameException("Модель " + nameOfModel + " уже существует!");
            }
            next = next.next;
        }
        if (isEmpty()) {
            head = new Model(nameOfModel, price);
            head.next = head;
            head.prev = head;
        } else {
            Model x = new Model(nameOfModel, price);
            x.prev = head.prev;
            x.next = head;
            head.prev.next = x;
            head.prev = x;
        }
        size++;
    }

    public void removeModel(String nameOfModel, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Нельзя устанавливать отрицательную цену!");
        }
        Model node = head.next;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (node.nameOfModel.equals(nameOfModel)) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else {
                count++;
            }
            node = node.next;
        }
        if (count == size) {
            throw new NoSuchModelNameException("Модель " + nameOfModel + " отсутствует!");
        }
        size--;
    }

    public int getSize() {
        return size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Motorcycle clone = (Motorcycle) super.clone();
        clone.head = (Model) head.clone();
        clone.head.prev = clone.head;
        clone.head.next = clone.head;
        for (int i = 0; i < size; i++) {
            Model node = new Model(i + "Name", i + 10);
            node.prev = clone.head.prev;
            node.next = clone.head;
            clone.head.prev.next = node;
            clone.head.prev = node;
        }
        return clone;
    }

    @Override
    public void accept(Visitor visitor) throws NoSuchModelNameException {
        visitor.visit(this);
    }
}
