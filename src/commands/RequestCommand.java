package commands;

import java.util.Arrays;
import java.util.Scanner;

import core.CommandManager;
import products.Bodywear;
import products.Car;
import products.Product;
import storage.ProductStorage;
import utils.InputUtils;

public class RequestCommand implements Command {
    private final String name = "request";
    private final String description = "Request a new item from the supplier.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        ProductStorage productStorage = commandManager.getProductStorage();
        Scanner keyboard = commandManager.getKeyboard();

        Product newProduct = null;

        String[] typeOptions = { "Car", "Bodywear" };
        String type = InputUtils.promptString(keyboard, "Enter a product type " + Arrays.toString(typeOptions) + ": ", typeOptions, false);

        int newId = productStorage.getMaxProductId() + 1;

        if (type.equalsIgnoreCase("car")) {
            newProduct = Car.fromInput(keyboard, newId);
        } else if (type.equalsIgnoreCase("bodywear")) {
            newProduct = Bodywear.fromInput(keyboard, newId);
        }

        productStorage.addProduct(newProduct);

        System.out.println("Successfully requested new product with ID " + newProduct.getId());
    }
}
