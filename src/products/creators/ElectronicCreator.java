package products.creators;

import java.util.Scanner;

import products.Electronic;
import products.constants.ProductStatus;
import products.constants.ProductType;
import utils.InputUtils;

public class ElectronicCreator extends ProductCreator<Electronic> {
    public ElectronicCreator() {
        super(ProductType.ELECTRONIC);
    }

    @Override
    public Electronic createFromStorageFields(String[] storageFields) {
        int id = Integer.parseInt(storageFields[0]);
        String name = storageFields[2];
        String status = storageFields[3];
        String description = storageFields[4];
        double price = Double.parseDouble(storageFields[5]);
        double wattage = Double.parseDouble(storageFields[6]);

        return new Electronic(id, name, status, description, price, wattage);
    }

    @Override
    public Electronic createFromKeyboard(Scanner keyboard, int newId) {
        String name = InputUtils.promptString(keyboard, "Enter electronic name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter electronic description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter electronic price: $", 0);
        double wattage = InputUtils.promptDouble(keyboard, "Enter electronic wattage (W): ", 0);

        return new Electronic(newId, name, status, description, price, wattage);
    }
}