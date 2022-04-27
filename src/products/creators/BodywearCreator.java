package products.creators;

import java.util.Scanner;

import products.Bodywear;
import products.Product;
import products.constants.ProductStatus;
import products.constants.ProductType;
import utils.InputUtils;

public class BodywearCreator implements ProductCreator {
    @Override
    public boolean canCreateFromStorageData(String[] storageData) {
        return storageData[0].equals(ProductType.BODYWEAR);
    }

    @Override
    public boolean canCreateFromKeyboard(String createProductType) {
        return createProductType.equals(ProductType.BODYWEAR);
    }

    @Override
    public Product createFromStorageData(String[] storageData) {
        int id = Integer.parseInt(storageData[1]);
        String status = storageData[2];
        String name = storageData[3];
        String description = storageData[4];
        double price = Double.parseDouble(storageData[5]);
        double weightKg = Double.parseDouble(storageData[6]);
        String size = storageData[7];

        return new Bodywear(id, status, name, description, price, weightKg, size);
    }

    @Override
    public Product createFromKeyboard(Scanner keyboard, int newId) {
        String name = InputUtils.promptString(keyboard, "Enter name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter price: $", 0);
        double weightKg = InputUtils.promptDouble(keyboard, "Enter weight (kg): ", 0);
        String size = InputUtils.promptString(keyboard, "Enter size: ");

        return new Bodywear(newId, status, name, description, price, weightKg, size);
    }
}