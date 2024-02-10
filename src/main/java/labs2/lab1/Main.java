package labs2.lab1;

import labs1.Transport;
import labs1.entities.Car;
import labs1.entities.Motorcycle;
import labs1.entities.Viewer;
import labs1.exceptions.DuplicateModelNameException;
import labs1.exceptions.NoSuchModelNameException;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, DuplicateModelNameException, NoSuchModelNameException {
        // Singleton
        System.out.println("__________________Singleton__________________");
        Config config = Config.getInstance();
        System.out.println(config.getProperty("property1"));
        System.out.println(Config.getInstance());
        System.out.println(Config.getInstance());

        // Factory Method
        System.out.println("__________________Factory Method__________________");
        Transport transport = Viewer.createInstance("BMW", 5);
        System.out.println(transport.getClass().getSimpleName());
        Viewer.setTransportFactory(new MotoFactory());
        transport = Viewer.createInstance("BMW", 5);
        System.out.println(transport.getClass().getSimpleName());

        // Prototype
        System.out.println("__________________Prototype__________________");
        Car car = new Car("Porsche", 10);
        Motorcycle motorcycle = new Motorcycle("Suzuki", 9);
        Car carClone = (Car) car.clone();
        Motorcycle motorcycleClone = (Motorcycle) motorcycle.clone();
        System.out.println("__________________originals__________________");
        Viewer.printAllModels(car);
        Viewer.printAllPrices(motorcycle);
        System.out.println("__________________clones__________________");
        Viewer.printAllModels(carClone);
        Viewer.printAllPrices(motorcycleClone);
        // Для класса Car нельзя метод add, из-за того, что там Arrays.copyOf(), который копирует.
        // Надо использовать только setters
        car.setNameOfModel("1_model", "AAA");
        // можно и add, set и прочие
        motorcycle.setPrice("1Name", 900000);
        System.out.println("__________________originals__________________");
        Viewer.printAllModels(car);
        Viewer.printAllPrices(motorcycle);
        System.out.println("__________________clones__________________");
        Viewer.printAllModels(carClone);
        Viewer.printAllPrices(motorcycleClone);
    }
}
