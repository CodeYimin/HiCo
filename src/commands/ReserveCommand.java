package commands;

import java.util.Scanner;

import core.CommandManager;
import products.Product;
import products.ProductStatus;
import storage.ProductStorage;
import utils.InputUtils;

public class ReserveCommand implements Command {
    private final String name = "reserve";
    private final String description = "Reserves an existing product from the store.";

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

        Product product = productStorage.getProduct(id);
        boolean statusChanged = product.changeProductStatus(ProductStatus.RESERVED);

        if (statusChanged) {
            productStorage.updateProduct(product);
            System.out.println("Successfully reserved product with ID " + id);
        } else {
            System.out.println("Failed to reserve product with ID " + id
                    + ". It probably does not exist or is not available for reservation.");
        }
    }
}
