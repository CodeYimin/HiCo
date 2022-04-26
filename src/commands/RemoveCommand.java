package commands;

import java.util.Scanner;

import core.CommandManager;
import products.Product;
import storage.ProductStorage;
import utils.InputUtils;

public class RemoveCommand implements Command {
    private final String name = "remove";
    private final String description = "Removes a product from the store.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = commandManager.getProductStorage();
        int id = InputUtils.promptInt(keyboard, "Enter a product ID: ");

        Product removedProduct = productStorage.removeProduct(productStorage.getProduct(id));
        if (removedProduct != null) {
            System.out.println("Successfully removed product with ID " + id);
        } else {
            System.out.println("Failed to remove product with ID " + id
                    + ". It probably does not exist.");
        }
    }
}
