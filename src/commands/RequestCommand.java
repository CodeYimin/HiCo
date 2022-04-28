package commands;

import java.util.Arrays;
import java.util.Scanner;

import core.CommandManager;
import core.ProductStorage;
import products.Product;
import products.constants.ProductType;
import utils.InputUtils;

/**
 * Command to request a new product. The product id is auto generated,
 * the rest of the properties are prompted for, and the product is added to the
 * storage with a default status of REQUESTED.
 */
public class RequestCommand extends ProductCommand {
    public RequestCommand(String name, String description, ProductStorage productStorage) {
        super(name, description, productStorage);
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        // Generate new unique product id
        int generatedId;
        try {
            generatedId = productStorage.getMaxProductId() + 1;
        } catch (Exception e) {
            System.out.println("Failed to generate a new product id.");
            return;
        }

        // Get the type of product to create
        String[] typeOptions = ProductType.getAllTypes();
        String typePrompt = "Enter a product type " + Arrays.toString(typeOptions) + ": ";
        String typeInput = InputUtils.promptString(keyboard, typePrompt, typeOptions, false);
        String type = ProductType.fromString(typeInput);

        // Create the new product in memory
        Product newProduct = Product.fromKeyboard(type, keyboard, generatedId);
        if (newProduct == null) {
            System.out.println("Failed to request new product.");
            return;
        }

        // Save the new product from memory into the storage
        try {
            productStorage.addProduct(newProduct);
            System.out.println("Successfully requested new product with ID " + newProduct.getId());
        } catch (Exception e) {
            System.out.println("Failed to request new product.");
            e.printStackTrace();
        }
    }
}
