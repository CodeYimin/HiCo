package products.creators;

import java.util.Scanner;

import products.Car;
import products.CarAndElectronicPackage;
import products.Electronic;
import products.Product;
import products.constants.ProductStatus;
import products.constants.ProductType;
import utils.InputUtils;

public class CarAndElectronicPackageCreator implements ProductCreator {
    private CarCreator carCreator;
    private ElectronicCreator electronicCreator;

    public CarAndElectronicPackageCreator(CarCreator carCreator, ElectronicCreator electronicCreator) {
        this.carCreator = carCreator;
        this.electronicCreator = electronicCreator;
    }

    @Override
    public boolean canCreateFromStorageData(String[] storageData) {
        return storageData[1].equals(ProductType.CAR_AND_ELECTRONIC_PACKAGE);
    }

    @Override
    public boolean canCreateFromKeyboard(String createProductType) {
        return createProductType.equals(ProductType.CAR_AND_ELECTRONIC_PACKAGE);
    }

    @Override
    public Product createFromStorageData(String[] storageData) {
        int id = Integer.parseInt(storageData[0]);
        String name = storageData[2];
        String status = storageData[3];
        String description = storageData[4];
        double price = Double.parseDouble(storageData[5]);

        Car car = (Car) Product.fromStorageString(storageData[6]);
        Electronic electronic = (Electronic) Product.fromStorageString(storageData[7]);

        return new CarAndElectronicPackage(id, name, status, description, price, car, electronic);
    }

    @Override
    public Product createFromKeyboard(Scanner keyboard, int newId) {
        String name = InputUtils.promptString(keyboard, "Enter package name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter package description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter package price: $", 0);

        System.out.println("---Please enter car information now---");
        Car car = (Car) carCreator.createFromKeyboard(keyboard, newId);
        car.setStatus("N/A");

        System.out.println("---Please enter electronic information now---");
        Electronic electronic = (Electronic) electronicCreator.createFromKeyboard(keyboard, newId);
        electronic.setStatus("N/A");

        return new CarAndElectronicPackage(newId, name, status, description, price, car, electronic);
    }
}
