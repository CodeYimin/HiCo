package products;

import java.util.Scanner;

import storage.ProductStorageConverter;
import utils.InputUtils;

public class Car extends Product {
    private double rangeKm;

    public Car(int id, String name, String status, String description, double price, double rangeKm) {
        super(id, name, status, description, price);
        this.rangeKm = rangeKm;
    }

    public double getRangeKm() {
        return rangeKm;
    }

    public static Car fromInput(Scanner keyboard, int id) {
        String name = InputUtils.promptString(keyboard, "Enter name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter price: $", 0);
        double rangeKm = InputUtils.promptDouble(keyboard, "Enter range (km): ", 0);

        return new Car(id, name, status, description, price, rangeKm);
    }

    @Override
    public String toString() {
        return super.toString() + " | Range: " + rangeKm + " km";
    }

    public static class StorageProcessor implements ProductStorageConverter {
        @Override
        public boolean canConvertString(String storageString) {
            return storageString.startsWith(ProductType.CAR);
        }

        @Override
        public boolean canConvertProduct(Product product) {
            return product instanceof Car;
        }

        @Override
        public String convertProduct(Product product) {
            Car car = (Car) product;

            return ProductType.CAR + ","
                    + car.getId() + ","
                    + car.getStatus() + ","
                    + car.getName() + ","
                    + car.getDescription() + ","
                    + car.getPrice() + ","
                    + car.getRangeKm();
        }

        @Override
        public Product convertString(String storageString) {
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
