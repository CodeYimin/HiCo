package products.creators;

import java.util.Scanner;

import products.Car;
import products.Product;
import products.constants.ProductStatus;
import products.constants.ProductType;
import utils.InputUtils;

public class CarCreator implements ProductCreator {
    @Override
    public boolean canCreateFromStorageData(String[] storageData) {
        return storageData[1].equals(ProductType.CAR);
    }

    @Override
    public boolean canCreateFromKeyboard(String createProductType) {
        return createProductType.equals(ProductType.CAR);
    }

    @Override
    public Product createFromStorageData(String[] storageData) {
        int id = Integer.parseInt(storageData[0]);
        String name = storageData[2];
        String status = storageData[3];
        String description = storageData[4];
        double price = Double.parseDouble(storageData[5]);
        double rangeKm = Double.parseDouble(storageData[6]);

        return new Car(id, name, status, description, price, rangeKm);
    }

    @Override
    public Product createFromKeyboard(Scanner keyboard, int newId) {
        String name = InputUtils.promptString(keyboard, "Enter car name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter car description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter car price: $", 0);
        double rangeKm = InputUtils.promptDouble(keyboard, "Enter car range (km): ", 0);

        return new Car(newId, name, status, description, price, rangeKm);
    }
}