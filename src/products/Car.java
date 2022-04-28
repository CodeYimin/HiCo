package products;

import products.constants.ProductType;

public class Car extends Product {
    private double rangeKm;

    public Car(int id, String name, String status, String description, double price, double rangeKm) {
        super(id, ProductType.CAR, name, status, description, price);
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
    public String[] extraStorageData() {
        return new String[] { String.valueOf(getRangeKm()) };
    }
}
