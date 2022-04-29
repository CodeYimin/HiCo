package commands;

import java.util.Arrays;
import java.util.Scanner;

import commands.helpers.ProductCommandHelper;
import core.CommandManager;
import core.ProductStorage;
import products.Product;
import products.constants.ProductStatus;
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

        // Choose whether to request brand new product or request a copy of an existing
        // product
        String[] modeOptions = { "New", "Copy" };
        String modePrompt = "Would you like to request a brand new product or another copy of an existing product? "
                + Arrays.toString(modeOptions) + ": ";
        String modeInput = InputUtils.promptString(keyboard, modePrompt, modeOptions, false);

        if (modeInput.equalsIgnoreCase("copy")) {
            Product product = ProductCommandHelper.promptProductUsingId(keyboard, productStorage,
                    "Enter the ID of the product to request a copy of: ");
            if (product == null) {
                System.out.println("Product not found.");
                return;
            }

            // Product is only changed in memory, not persisted,
            // so original product is unmodified
            product.setId(generatedId);
            product.setStatus(ProductStatus.REQUESTED);

            // Safe to persist new product now, since id is changed
            try {
                productStorage.addProduct(product);
            } catch (Exception e) {
                System.out.println("Failed to request product.");
                return;
            }
            System.out.println("Requested a copy with ID " + product.getId() + ".");
        } else {
            // Request brand new product

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
            } catch (Exception e) {
                System.out.println("Failed to request new product.");
                return;
            }
            System.out.println("Successfully requested brand new product with ID " + newProduct.getId());
        }
    }
}
