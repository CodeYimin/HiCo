package products.creators;

import java.util.Scanner;

import products.Electronic;
import products.Product;
import products.constants.ProductStatus;
import products.constants.ProductType;
import utils.InputUtils;

public class ElectronicCreator implements ProductCreator {
    @Override
    public boolean canCreateFromStorageData(String[] storageData) {
        return storageData[0].equals(ProductType.ELECTRONIC);
    }

    @Override
    public boolean canCreateFromKeyboard(String createProductType) {
        return createProductType.equals(ProductType.ELECTRONIC);
    }

    @Override
    public Product createFromStorageData(String[] storageData) {
        int id = Integer.parseInt(storageData[1]);
        String status = storageData[2];
        String name = storageData[3];
        String description = storageData[4];
        double price = Double.parseDouble(storageData[5]);
        double wattage = Double.parseDouble(storageData[6]);

        return new Electronic(id, status, name, description, price, wattage);
    }

    @Override
    public Product createFromKeyboard(Scanner keyboard, int newId) {
        String name = InputUtils.promptString(keyboard, "Enter name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter price: $", 0);
        double wattage = InputUtils.promptDouble(keyboard, "Enter wattage (W): ", 0);

        return new Electronic(newId, status, name, description, price, wattage);
    }
}