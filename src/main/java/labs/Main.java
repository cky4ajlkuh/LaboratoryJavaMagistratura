package labs;

import labs.first.Car;
import labs.first.Motorcycle;
import labs.first.TransportVehicle;
import labs.first.Viewer;
import labs.first.exceptions.DuplicateModelNameException;
import labs.first.exceptions.NoSuchModelNameException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws NoSuchModelNameException, DuplicateModelNameException, IOException, ClassNotFoundException {
        TransportVehicle vehicle = new Car("BMW", 10);
        printAll(vehicle);
        Path bytes = Paths.get("Byte.bin");
        Path chars = Paths.get("Char.txt");
        Path serialization = Paths.get("Serialization.bin");
        TransportVehicle deserializationVehicle, byteVehicle, charVehicle, systemVehicle;

        // Байтовые потоки
        System.out.println(bytes);
        Viewer.outputVehicle(vehicle, Files.newOutputStream(bytes));
        byteVehicle = Viewer.inputVehicle(Files.newInputStream(bytes));
        printAll(byteVehicle);

        // Символьные потоки
        System.out.println(chars);
        Viewer.writeVehicle(vehicle, new FileWriter(String.valueOf(chars)));
        charVehicle = Viewer.readVehicle(new FileReader(String.valueOf(chars)));
        printAll(charVehicle);

        // System.in и System.out, format: ClassName brand countOfModels <model1> <price1> <model2> <price2>....
        Viewer.writeVehicle(vehicle, new OutputStreamWriter(System.out));
        systemVehicle = Viewer.readVehicle(new InputStreamReader(System.in));
        printAll(systemVehicle);

        //Сериализация
        System.out.println(serialization);
        ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(serialization));
        ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(serialization));
        outputStream.writeObject(vehicle);
        Object o = inputStream.readObject();
        deserializationVehicle = o.getClass().getSimpleName().equals(Car.class.getSimpleName()) ? (Car) o : (Motorcycle) o;
        inputStream.close();
        outputStream.close();
        printAll(deserializationVehicle);
    }

    private static void printAll(TransportVehicle vehicle) {
        System.out.println("__________________________________________");
        System.out.println("Тип: " + vehicle.getClass().getSimpleName());
        System.out.println("Бренд: " + vehicle.getBrand());
        System.out.println("Кол-во моделей: " + vehicle.getSize());
        Viewer.printAllPrices(vehicle);
        Viewer.printAllModels(vehicle);
        System.out.println("__________________________________________");
    }
}
