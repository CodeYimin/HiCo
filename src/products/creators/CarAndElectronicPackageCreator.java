package products.creators;

import java.util.Scanner;

import products.Car;
import products.CarAndElectronicPackage;
import products.Electronic;
import products.Product;
import products.constants.ProductStatus;
import products.constants.ProductType;
import utils.InputUtils;

public class CarAndElectronicPackageCreator extends ProductCreator<CarAndElectronicPackage> {
    private final CarCreator carCreator = new CarCreator();
    private final ElectronicCreator electronicCreator = new ElectronicCreator();

    public CarAndElectronicPackageCreator() {
        super(ProductType.CAR_AND_ELECTRONIC_PACKAGE);
    }

    @Override
    public CarAndElectronicPackage createFromStorageFields(String[] storageFields) {
        int id = Integer.parseInt(storageFields[0]);
        String name = storageFields[2];
        String status = storageFields[3];
        String description = storageFields[4];
        double price = Double.parseDouble(storageFields[5]);

        Car car = (Car) Product.fromStorageString(storageFields[6]);
        Electronic electronic = (Electronic) Product.fromStorageString(storageFields[7]);

        return new CarAndElectronicPackage(id, name, status, description, price, car, electronic);
    }

    @Override
    public CarAndElectronicPackage createFromKeyboard(Scanner keyboard, int newId) {
        String name = InputUtils.promptString(keyboard, "Enter package name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter package description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter package price: $", 0);

        System.out.println("---Please enter car information now---");
        Car car = carCreator.createFromKeyboard(keyboard, newId);
        car.setStatus("N/A");

        System.out.println("---Please enter electronic information now---");
        Electronic electronic = electronicCreator.createFromKeyboard(keyboard, newId);
        electronic.setStatus("N/A");

        return new CarAndElectronicPackage(newId, name, status, description, price, car, electronic);
    }
}
