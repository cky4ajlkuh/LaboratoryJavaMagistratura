package labs1.entities;

import labs1.Transport;
import labs1.exceptions.DuplicateModelNameException;
import labs1.exceptions.NoSuchModelNameException;
import labs2.lab1.AutoFactory;
import labs2.lab1.TransportFactory;
import labs2.lab2.TransportWrapper;

import java.io.*;

public class Viewer {

    private static TransportFactory factory = new AutoFactory();

    public static void setTransportFactory(TransportFactory transportFactory) {
        factory = transportFactory;
    }

    public static synchronized TransportWrapper synchronizedTransport(Transport t) {
        return new TransportWrapper(t);
    }

    public static Transport createInstance(String name, int size) {
        return factory.createInstance(name, size);
    }

    public static double arithmeticMean(Transport vehicle) {
        double[] prices = vehicle.getPrices();
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    public static void printAllModels(Transport vehicle) {
        String[] models = vehicle.getNamesOfModels();
        System.out.print("Все модели: ");
        for (String model : models) {
            System.out.print(model + "  ");
        }
        System.out.println();
    }

    public static void printAllPrices(Transport vehicle) {
        double[] prices = vehicle.getPrices();
        System.out.print("Все цены: ");
        for (double price : prices) {
            System.out.print(price + "  ");
        }
        System.out.println();
    }

    public static void outputVehicle(Transport vehicle, OutputStream out) throws IOException, NoSuchModelNameException {
        DataOutputStream stream = new DataOutputStream(out);
        stream.writeInt(vehicle.getClass().getName().getBytes().length);
        writeBytes(stream, vehicle.getClass().getSimpleName().getBytes());
        stream.writeInt(vehicle.getBrand().getBytes().length);
        writeBytes(stream, vehicle.getBrand().getBytes());
        stream.writeInt(vehicle.getSize());
        for (int i = 0; i < vehicle.getSize(); i++) {
            stream.writeInt(vehicle.getNamesOfModels()[i].getBytes().length);
            writeBytes(stream, vehicle.getNamesOfModels()[i].getBytes());
            stream.writeDouble(vehicle.getPrice(vehicle.getNamesOfModels()[i]));
        }
        stream.flush();
    }

    public static Transport inputVehicle(InputStream input) throws IOException, DuplicateModelNameException {
        DataInputStream stream = new DataInputStream(input);
        String type = new String(readBytes(stream, stream.readInt()));
        String brand = new String(readBytes(stream, stream.readInt()));
        Transport vehicle = (type.equals(Car.class.getSimpleName())) ? new Car(brand, 0) : new Motorcycle(brand, 0);
        int size = stream.readInt();
        for (int i = 0; i < size; i++) {
            vehicle.addNameAndPrice(new String(readBytes(stream, stream.readInt())), stream.readDouble());
        }
        return vehicle;
    }

    public static void writeVehicle(Transport vehicle, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.println(vehicle.getClass().getSimpleName());
        writer.println(vehicle.getBrand());
        writer.println(vehicle.getSize());
        String[] names = vehicle.getNamesOfModels();
        double[] prices = vehicle.getPrices();
        for (int i = 0; i < vehicle.getSize(); i++) {
            writer.println(names[i]);
            writer.println(prices[i]);
        }
        writer.flush();
    }

    public static Transport readVehicle(Reader in) throws IOException, DuplicateModelNameException {
        BufferedReader reader = new BufferedReader(in);
        String type = reader.readLine();
        String brand = reader.readLine();
        Transport vehicle = (type.equals(Car.class.getSimpleName())) ? new Car(brand, 0) : new Motorcycle(brand, 0);
        int size = Integer.parseInt(reader.readLine());
        for (int i = 0; i < size; i++) {
            vehicle.addNameAndPrice(reader.readLine(), Double.parseDouble(reader.readLine()));
        }
        return vehicle;
    }

    public static void writeBytes(DataOutputStream stream, byte[] array) throws IOException {
        for (byte b : array) {
            stream.writeByte(b);
        }
    }

    public static byte[] readBytes(DataInputStream stream, int length) throws IOException {
        byte[] array = new byte[length];
        for (int i = 0; i < length; i++) {
            array[i] = stream.readByte();
        }
        return array;
    }
}
