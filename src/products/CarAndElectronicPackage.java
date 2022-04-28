package products;

import products.constants.ProductType;

public class CarAndElectronicPackage extends Product {
    private Car car;
    private Electronic electronic;

    public CarAndElectronicPackage(int id, String name, String status, String description, double price, Car car, Electronic electronic) {
        super(id, ProductType.CAR_AND_ELECTRONIC_PACKAGE, name, status, description, price);
        this.car = car;
        this.electronic = electronic;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Electronic getElectronic() {
        return this.electronic;
    }

    public void setElectronic(Electronic electronic) {
        this.electronic = electronic;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\n\t- Car: " + car.toString()
                + "\n\t- Electronic: " + electronic.toString();
    }

    @Override
    public String[] extraStorageFields() {
        return new String[] { car.toStorageString(), electronic.toStorageString() };
    }
}
