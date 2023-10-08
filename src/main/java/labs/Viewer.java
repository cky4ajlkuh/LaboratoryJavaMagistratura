package labs;

import labs.exceptions.DuplicateModelNameException;
import labs.exceptions.NoSuchModelNameException;

import java.io.*;

public class Viewer {

    public static double arithmeticMean(TransportVehicle vehicle) {
        double[] prices = vehicle.getPrices();
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    public static void printAllModels(TransportVehicle vehicle) {
        String[] models = vehicle.getNamesOfModels();
        System.out.print("Все модели: ");
        for (String model : models) {
            System.out.print(model + "  ");
        }
        System.out.println();
    }

    public static void printAllPrices(TransportVehicle vehicle) {
        double[] prices = vehicle.getPrices();
        System.out.print("Все цены: ");
        for (double price : prices) {
            System.out.print(price + "  ");
        }
        System.out.println();
    }

    public static void outputVehicle(TransportVehicle vehicle, OutputStream out) throws IOException, NoSuchModelNameException {
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

    public static TransportVehicle inputVehicle(InputStream input) throws IOException, DuplicateModelNameException {
        DataInputStream stream = new DataInputStream(input);
        String type = new String(readBytes(stream, stream.readInt()));
        String brand = new String(readBytes(stream, stream.readInt()));
        TransportVehicle vehicle = (type.equals(Car.class.getSimpleName())) ? new Car(brand, 0) : new Motorcycle(brand, 0);
        int size = stream.readInt();
        for (int i = 0; i < size; i++) {
            vehicle.addNameAndPrice(new String(readBytes(stream, stream.readInt())), stream.readDouble());
        }
        return vehicle;
    }

    public static void writeVehicle(TransportVehicle vehicle, Writer out) {
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

    public static TransportVehicle readVehicle(Reader in) throws IOException, DuplicateModelNameException {
        BufferedReader reader = new BufferedReader(in);
        String type = reader.readLine();
        String brand = reader.readLine();
        TransportVehicle vehicle = (type.equals(Car.class.getSimpleName())) ? new Car(brand, 0) : new Motorcycle(brand, 0);
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
