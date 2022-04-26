package commands;

import java.util.Scanner;

import core.CommandManager;
import products.Product;
import products.ProductStatus;
import storage.ProductStorage;
import utils.InputUtils;

public class SoldCommand implements Command {
    private final String name = "sold";
    private final String description = "Marks a product as sold and no longer available for purchase.";

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
        if (product == null) {
            System.out.println("Failed to get product with ID " + id);
        }

        boolean statusChanged = product.changeStatus(ProductStatus.SOLD);
        if (statusChanged) {
            System.out.println("Successfully sold product with ID " + id);
        } else {
            System.out.println("Failed to sell product with ID " + id);
        }
    }
}
