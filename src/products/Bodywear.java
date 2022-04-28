package products;

import products.constants.ProductType;

public class Bodywear extends Product {
    private double weightKg;
    private String size;

    public Bodywear(int id, String name, String status, String description, double price, double weightKg,
            String size) {
        super(id, ProductType.BODYWEAR, name, status, description, price);
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
    public String[] extraStorageFields() {
        return new String[] {
                String.valueOf(getWeightKg()),
                getSize()
        };
    }
}
