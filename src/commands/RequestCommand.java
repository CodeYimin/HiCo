package commands;

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

        String[] typeOptions = { "car", "bodywear" };
        String type = InputUtils.promptString(keyboard, "Enter a product type (Car, bodywear): ",
                typeOptions, false);

        Product newProduct = null;

        if (type.equalsIgnoreCase("car")) {
            newProduct = Car.fromInput(keyboard);
        } else if (type.equalsIgnoreCase("bodywear")) {
            newProduct = Bodywear.fromInput(keyboard);
        }

        productStorage.addProduct(newProduct);

        System.out.println("Successfully requested new product with ID " + newProduct.getId());
    }
}
