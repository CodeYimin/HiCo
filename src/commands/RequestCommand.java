package commands;

import java.util.Arrays;
import java.util.Scanner;

import core.CommandManager;
import core.ProductStorage;
import products.Product;
import products.constants.ProductType;
import utils.InputUtils;

public class RequestCommand extends ProductCommand {
    public RequestCommand(String name, String description, ProductStorage productStorage) {
        super(name, description, productStorage);
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        int newId;
        try {
            newId = productStorage.getMaxProductId() + 1;
        } catch (Exception e) {
            System.out.println("Failed to generate a new product id.");
            return;
        }

        // Get the type of product to create
        String[] typeOptions = ProductType.getAllTypes();
        String typePrompt = "Enter a product type " + Arrays.toString(typeOptions) + ": ";
        String typeInput = InputUtils.promptString(keyboard, typePrompt, typeOptions, false);
        String type = ProductType.fromString(typeInput);

        Product newProduct = Product.fromKeyboard(type, keyboard, newId);
        if (newProduct == null) {
            System.out.println("Failed to request new product.");
            return;
        }

        try {
            productStorage.addProduct(newProduct);
            System.out.println("Successfully requested new product with ID " + newProduct.getId());
        } catch (Exception e) {
            System.out.println("Failed to request new product.");
        }
    }
}
