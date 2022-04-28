package products.creators;

import java.util.Scanner;

import products.Car;
import products.constants.ProductStatus;
import products.constants.ProductType;
import utils.InputUtils;

public class CarCreator extends ProductCreator<Car> {
    public CarCreator() {
        super(ProductType.CAR);
    }

    @Override
    public Car createFromStorageFields(String[] storageFields) {
        int id = Integer.parseInt(storageFields[0]);
        String name = storageFields[2];
        String status = storageFields[3];
        String description = storageFields[4];
        double price = Double.parseDouble(storageFields[5]);
        double rangeKm = Double.parseDouble(storageFields[6]);

        return new Car(id, name, status, description, price, rangeKm);
    }

    @Override
    public Car createFromKeyboard(Scanner keyboard, int newId) {
        String name = InputUtils.promptString(keyboard, "Enter car name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter car description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter car price: $", 0);
        double rangeKm = InputUtils.promptDouble(keyboard, "Enter car range (km): ", 0);

        return new Car(newId, name, status, description, price, rangeKm);
    }
}