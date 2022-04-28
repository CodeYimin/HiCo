package products.creators;

import java.util.Scanner;

import products.Bodywear;
import products.constants.ProductStatus;
import products.constants.ProductType;
import utils.InputUtils;

public class BodywearCreator extends ProductCreator<Bodywear> {
    public BodywearCreator() {
        super(ProductType.BODYWEAR);
    }

    @Override
    public Bodywear createFromStorageFields(String[] storageFields) {
        int id = Integer.parseInt(storageFields[0]);
        String name = storageFields[2];
        String status = storageFields[3];
        String description = storageFields[4];
        double price = Double.parseDouble(storageFields[5]);
        double weightKg = Double.parseDouble(storageFields[6]);
        String size = storageFields[7];

        return new Bodywear(id, name, status, description, price, weightKg, size);
    }

    @Override
    public Bodywear createFromKeyboard(Scanner keyboard, int newId) {
        String name = InputUtils.promptString(keyboard, "Enter bodywear name: ");
        String status = ProductStatus.REQUESTED;
        String description = InputUtils.promptString(keyboard, "Enter bodywear description: ");
        double price = InputUtils.promptDouble(keyboard, "Enter bodywear price: $", 0);
        double weightKg = InputUtils.promptDouble(keyboard, "Enter bodywear weight (kg): ", 0);
        String size = InputUtils.promptString(keyboard, "Enter bodywear size: ");

        return new Bodywear(newId, name, status, description, price, weightKg, size);
    }
}