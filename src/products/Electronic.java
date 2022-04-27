package products;

import java.util.Scanner;

import storage.ProductCreator;
import utils.InputUtils;

public class Electronic extends Product {
    private double wattage;

    public Electronic(int id, String name, String status, String description, double price, double wattage) {
        super(id, name, status, description, price);
        this.wattage = wattage;
    }

    public double getWattage() {
        return wattage;
    }

    @Override
    public String toString() {
        return super.toString() + " | Wattage: " + wattage + "W";
    }

    @Override
    public String[] toStorageData() {
        String[] storageData = {
                ProductType.ELECTRONIC,
                String.valueOf(getId()),
                getName(),
                getStatus(),
                getDescription(),
                String.valueOf(getPrice()),
                String.valueOf(getWattage())
        };

        return storageData;
    }

    public static class Creator implements ProductCreator {
        @Override
        public boolean canCreateFromStorageData(String[] storageData) {
            return storageData[0].equals(ProductType.ELECTRONIC);
        }

        @Override
        public boolean canCreateFromKeyboard(String createProductType) {
            return createProductType.equals(ProductType.ELECTRONIC);
        }

        @Override
        public Product createFromStorageData(String[] storageData) {
            int id = Integer.parseInt(storageData[1]);
            String status = storageData[2];
            String name = storageData[3];
            String description = storageData[4];
            double price = Double.parseDouble(storageData[5]);
            double wattage = Double.parseDouble(storageData[6]);

            return new Electronic(id, status, name, description, price, wattage);
        }

        @Override
        public Product createFromKeyboard(Scanner keyboard, int newId) {
            String name = InputUtils.promptString(keyboard, "Enter name: ");
            String status = ProductStatus.REQUESTED;
            String description = InputUtils.promptString(keyboard, "Enter description: ");
            double price = InputUtils.promptDouble(keyboard, "Enter price: $", 0);
            double wattage = InputUtils.promptDouble(keyboard, "Enter wattage (W): ", 0);

            return new Electronic(newId, status, name, description, price, wattage);
        }
    }
}
