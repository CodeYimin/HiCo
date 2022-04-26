package commands;

import java.util.Scanner;

import core.CommandManager;
import products.Bodywear;
import products.Car;
import products.Product;
import storage.ProductStorage;
import utils.InputUtils;

public class AddCommand implements Command {
    private final String name = "add";
    private final String description = "Adds a new product to the store.";

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
                typeOptions);

        Product newProduct = null;

        if (type.equals("car")) {
            Car car = Car.fromInput(keyboard);
            productStorage.addProduct(car);
            newProduct = car;
        } else if (type.equals("bodywear")) {
            Bodywear bodywear = Bodywear.fromInput(keyboard);
            productStorage.addProduct(bodywear);
            newProduct = bodywear;
        }

        System.out.println("Successfully added new product with ID " + newProduct.getId());
    }
}
