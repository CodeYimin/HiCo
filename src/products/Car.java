package products;

import java.util.Scanner;

import storage.ProductStorageProcessor;
import utils.InputUtils;

public class Car extends Product {
    private double rangeKm;

    public Car(String name, String status, String description, double price, double rangeKm) {
        super(status, name, description, price);
        this.rangeKm = rangeKm;
    }

    public Car(int id, String status, String name, String description, double price, double rangeKm) {
        super(id, status, name, description, price);
        this.rangeKm = rangeKm;
    }

    public double getRangeKm() {
        return rangeKm;
    }

    public static Car fromInput(Scanner keyboard) {
        String name = InputUtils.promptString(keyboard, "Enter name: ");
        String[] statusOptions = { ProductStatus.AVAILABLE, ProductStatus.RESERVED, ProductStatus.REQUESTED };
        String status = InputUtils.promptString(keyboard, "Enter status: ", statusOptions);
        String description = InputUtils.promptString(keyboard, "Enter description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter price: ", 0);
        double rangeKm = InputUtils.promptDouble(keyboard, "Enter range: ", 0);

        return new Car(name, status, description, price, rangeKm);
    }

    @Override
    public String toString() {
        return super.toString() + " | Range: " + rangeKm + " km";
    }

    public static class StorageProcessor implements ProductStorageProcessor {
        @Override
        public boolean canProcessString(String storageString) {
            return storageString.startsWith("Car");
        }

        @Override
        public boolean canProcessProduct(Product product) {
            return product instanceof Car;
        }

        @Override
        public String productToString(Product product) {
            Car car = (Car) product;

            return "Car" + "," +
                    car.getId() + "," + car.getStatus() + "," + car.getName() + "," + car.getDescription() + ","
                    + car.getPrice() + "," + car.getRangeKm();
        }

        @Override
        public Product stringToProduct(String storageString) {
            String[] parts = storageString.split(",");

            int id = Integer.parseInt(parts[1]);
            String status = parts[2];
            String name = parts[3];
            String description = parts[4];
            double price = Double.parseDouble(parts[5]);
            double rangeKm = Double.parseDouble(parts[6]);

            return new Car(id, status, name, description, price, rangeKm);
        }
    }
}
