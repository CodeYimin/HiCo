package products;

import java.util.Scanner;

import storage.ProductCreator;
import utils.InputUtils;

public class Bodywear extends Product {
    private double weightKg;
    private String size;

    public Bodywear(int id, String name, String status, String description, double price, double weightKg,
            String size) {
        super(id, name, status, description, price);
        this.weightKg = weightKg;
        this.size = size;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Weight: " + weightKg + "kg"
                + " | Size: " + size;
    }

    @Override
    public String[] toStorageData() {
        String[] storageData = {
                ProductType.BODYWEAR,
                String.valueOf(getId()),
                getStatus(),
                getName(),
                getDescription(),
                String.valueOf(getPrice()),
                String.valueOf(getWeightKg()),
                getSize()
        };

        return storageData;
    }

    public static class Creator implements ProductCreator {
        @Override
        public boolean canCreateFromStorageData(String[] storageData) {
            return storageData[0].equals(ProductType.BODYWEAR);
        }

        @Override
        public boolean canCreateFromKeyboard(String createProductType) {
            return createProductType.equals(ProductType.BODYWEAR);
        }

        @Override
        public Product createFromStorageData(String[] storageData) {
            int id = Integer.parseInt(storageData[1]);
            String status = storageData[2];
            String name = storageData[3];
            String description = storageData[4];
            double price = Double.parseDouble(storageData[5]);
            double weightKg = Double.parseDouble(storageData[6]);
            String size = storageData[7];

            return new Bodywear(id, status, name, description, price, weightKg, size);
        }

        @Override
        public Product createFromKeyboard(Scanner keyboard, int newId) {
            String name = InputUtils.promptString(keyboard, "Enter name: ");
            String status = ProductStatus.REQUESTED;
            String description = InputUtils.promptString(keyboard, "Enter description: ");
            double price = InputUtils.promptDouble(keyboard, "Enter price: $", 0);
            double weightKg = InputUtils.promptDouble(keyboard, "Enter weight (kg): ", 0);
            String size = InputUtils.promptString(keyboard, "Enter size: ");

            return new Bodywear(newId, status, name, description, price, weightKg, size);
        }
    }
}
