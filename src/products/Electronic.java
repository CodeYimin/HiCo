package products;

import products.constants.ProductType;

public class Electronic extends Product {
    private double wattage;

    public Electronic(int id, String name, String status, String description, double price, double wattage) {
        super(id, ProductType.ELECTRONIC, name, status, description, price);
        this.wattage = wattage;
    }

    public double getWattage() {
        return wattage;
    }

    @Override
    public String extraToStringBody() {
        return "Wattage: " + wattage + "W";
    }

    @Override
    public String[] extraStorageFields() {
        return new String[] { String.valueOf(getWattage()) };
    }
}
