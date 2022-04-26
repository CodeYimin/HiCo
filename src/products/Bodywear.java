package products;

import java.util.Scanner;

import storage.ProductStorageProcessor;
import utils.InputUtils;

public class Bodywear extends Product {
    private double weightKg;
    private String size;

    public Bodywear(int id, String status, String name, String description, double price, double weightKg,
            String size) {
        super(id, status, name, description, price);
        this.weightKg = weightKg;
        this.size = size;
    }

    public Bodywear(String name, String status, String description, double price, double weightKg, String size) {
        super(status, name, description, price);
        this.weightKg = weightKg;
        this.size = size;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public String getSize() {
        return size;
    }

    public static Bodywear fromInput(Scanner keyboard) {
        String name = InputUtils.promptString(keyboard, "Enter name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter price: $", 0);
        double weightKg = InputUtils.promptDouble(keyboard, "Enter weight (kg): ", 0);
        String size = InputUtils.promptString(keyboard, "Enter size: ");

        return new Bodywear(name, status, description, price, weightKg, size);
    }

    @Override
    public String toString() {
        return super.toString() + " | Weight: " + weightKg + " kg | Size: " + size;
    }

    public static class StorageProcessor implements ProductStorageProcessor {
        @Override
        public boolean canProcessString(String storageString) {
            return storageString.startsWith(ProductType.BODYWEAR);
        }

        @Override
        public boolean canProcessProduct(Product product) {
            return product instanceof Bodywear;
        }

        @Override
        public String productToString(Product product) {
            Bodywear bodywear = (Bodywear) product;

            return ProductType.BODYWEAR + "," +
                    bodywear.getId() + "," + bodywear.getStatus() + "," + bodywear.getName() + ","
                    + bodywear.getDescription() + ","
                    + bodywear.getPrice() + "," + bodywear.getWeightKg() + "," + bodywear.getSize();
        }

        @Override
        public Product stringToProduct(String storageString) {
            String[] parts = storageString.split(",");

            int id = Integer.parseInt(parts[1]);
            String status = parts[2];
            String name = parts[3];
            String description = parts[4];
            double price = Double.parseDouble(parts[5]);
            double weightKg = Double.parseDouble(parts[6]);
            String size = parts[7];

            return new Bodywear(
                    id, status, name, description, price, weightKg, size);
        }
    }
}
