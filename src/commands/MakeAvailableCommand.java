package commands;

import java.util.Scanner;

import core.CommandManager;
import products.Product;
import products.ProductStatus;
import storage.ProductStorage;
import utils.ArrayUtils;
import utils.InputUtils;

public class MakeAvailableCommand implements Command {
    private final String name = "makeAvailable";
    private final String description = "Makes a requested, held, or returned product available to be purchased.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = commandManager.getProductStorage();

        Product[] requestedProducts = productStorage.getProducts(new Product.StatusFilter(ProductStatus.REQUESTED));
        Product[] soldProducts = productStorage.getProducts(new Product.StatusFilter(ProductStatus.SOLD));
        Product[] onHoldProducts = productStorage.getProducts(new Product.StatusFilter(ProductStatus.ON_HOLD));

        Product[] eligibleProducts = ArrayUtils.concat(ArrayUtils.concat(requestedProducts, soldProducts),
                onHoldProducts);

        if (eligibleProducts.length == 0) {
            System.out.println("No products to be made available.");
            return;
        }

        System.out.println("Products to be made available: ");
        for (Product product : eligibleProducts) {
            System.out.println(product);
        }

        int id = InputUtils.promptInt(keyboard, "Enter a product ID: ");

        Product product = productStorage.getProduct(id);
        if (product == null) {
            System.out.println("Failed to get product with ID " + id);
            return;
        }

        boolean statusChanged = product.changeProductStatus(ProductStatus.AVAILABLE);
        if (statusChanged) {
            productStorage.syncProduct(product);
            System.out.println("Successfully added product with ID " + id);
        } else {
            System.out.println("Failed to make product available with ID " + id);
        }
    }
}
