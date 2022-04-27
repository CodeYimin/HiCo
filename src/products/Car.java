package products;

import java.util.Scanner;

import storage.ProductCreator;
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

    @Override
    public String toString() {
        return super.toString() + " | Range: " + rangeKm + "km";
    }

    @Override
    public String[] toStorageData() {
        String[] storageData = {
                ProductType.CAR,
                String.valueOf(getId()),
                getStatus(),
                getName(),
                getDescription(),
                String.valueOf(getPrice()),
                String.valueOf(getRangeKm())
        };

        return storageData;
    }

    public static class Creator implements ProductCreator {
        @Override
        public boolean canCreateFromStorageData(String[] storageData) {
            return storageData[0].equals(ProductType.CAR);
        }

        @Override
        public boolean canCreateFromKeyboard(String createProductType) {
            return createProductType.equals(ProductType.CAR);
        }

        @Override
        public Product createFromStorageData(String[] storageData) {
            int id = Integer.parseInt(storageData[1]);
            String status = storageData[2];
            String name = storageData[3];
            String description = storageData[4];
            double price = Double.parseDouble(storageData[5]);
            double rangeKm = Double.parseDouble(storageData[6]);

            return new Car(id, status, name, description, price, rangeKm);
        }

        @Override
        public Product createFromKeyboard(Scanner keyboard, int newId) {
            String name = InputUtils.promptString(keyboard, "Enter name: ");
            String status = ProductStatus.REQUESTED;
            String description = InputUtils.promptString(keyboard, "Enter description: ");
            double price = InputUtils.promptDouble(keyboard, "Enter price: $", 0);
            double rangeKm = InputUtils.promptDouble(keyboard, "Enter range (km): ", 0);

            return new Car(newId, status, name, description, price, rangeKm);
        }
    }
}
