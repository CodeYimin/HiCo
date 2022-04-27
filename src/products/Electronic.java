package products;

import products.constants.ProductType;

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
                getStatus(),
                getName(),
                getDescription(),
                String.valueOf(getPrice()),
                String.valueOf(getWattage())
        };

        return storageData;
    }
}
