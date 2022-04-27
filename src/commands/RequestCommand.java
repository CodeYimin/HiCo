package commands;

import java.util.Arrays;
import java.util.Scanner;

import core.CommandManager;
import core.ProductStorage;
import products.Product;
import products.constants.ProductType;
import products.creators.ProductCreator;
import utils.InputUtils;

public class RequestCommand extends ProductCommand {
    private final ProductCreator[] productCreators;

    public RequestCommand(String name, String description, ProductStorage productStorage, ProductCreator[] productCreators) {
        super(name, description, productStorage);
        this.productCreators = productCreators;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        Product newProduct = null;
        int newId;
        try {
            newId = productStorage.getMaxProductId() + 1;
        } catch (Exception e) {
            System.out.println("Failed to request a new product id.");
            return;
        }

        // Get the type of product to create
        String[] typeOptions = ProductType.getAllTypes();
        String typePrompt = "Enter a product type " + Arrays.toString(typeOptions) + ": ";
        String typeInput = InputUtils.promptString(keyboard, typePrompt, typeOptions, false);
        String type = ProductType.fromString(typeInput);

        // Try to create a product with the given type using the product creators
        for (ProductCreator productCreator : productCreators) {
            if (productCreator.canCreateFromKeyboard(type)) {
                newProduct = productCreator.createFromKeyboard(keyboard, newId);
            }
        }

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
