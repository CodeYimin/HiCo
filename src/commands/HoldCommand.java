package commands;

import java.util.Scanner;

import core.CommandManager;
import products.Product;
import products.ProductStatus;
import storage.ProductStorage;
import utils.InputUtils;

public class HoldCommand implements Command {
    private final String name = "hold";
    private final String description = "Marks a product as on-hold and no longer available for purchase.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = commandManager.getProductStorage();

        Product[] availableProducts = productStorage.getProducts(new Product.StatusFilter(ProductStatus.AVAILABLE));
        if (availableProducts.length == 0) {
            System.out.println("No available products to hold.");
            return;
        }

        System.out.println("Available products to hold: ");
        for (Product product : availableProducts) {
            System.out.println(product);
        }

        int id = InputUtils.promptInt(keyboard, "Enter a product ID: ");

        Product product = productStorage.getProduct(id);
        if (product == null) {
            System.out.println("Failed to hold product with ID " + id
                    + ". It probably does not exist.");
            return;
        }

        boolean statusChanged = product.changeProductStatus(ProductStatus.ON_HOLD);
        if (statusChanged) {
            productStorage.syncProduct(product);
            System.out.println("Successfully held product with ID " + id);
        } else {
            System.out.println("Failed to hold product with ID " + id
                    + ". It probably is not available.");
        }
    }
}
