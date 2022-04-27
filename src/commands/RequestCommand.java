package commands;

import java.util.Arrays;
import java.util.Scanner;

import core.CommandManager;
import products.Product;
import products.ProductType;
import storage.ProductCreator;
import storage.ProductStorage;
import utils.InputUtils;

public class RequestCommand extends ProductStorageCommand {
    private final ProductCreator[] productCreators;

    public RequestCommand(String name, String description, ProductStorage productStorage, ProductCreator[] productCreators) {
        super(name, description, productStorage);
        this.productCreators = productCreators;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        Product newProduct = null;
        int newId = productStorage.getMaxProductId() + 1;

        String[] typeOptions = ProductType.getAllTypes();
        String typePrompt = "Enter a product type " + Arrays.toString(typeOptions) + ": ";
        String typeInput = InputUtils.promptString(keyboard, typePrompt, typeOptions, false);
        String type = ProductType.fromString(typeInput);

        for (ProductCreator productCreator : productCreators) {
            if (productCreator.canCreateFromKeyboard(type)) {
                newProduct = productCreator.createFromKeyboard(keyboard, newId);
            }
        }

        if (newProduct == null) {
            System.out.println("Failed to create a new product.");
            return;
        } else {
            productStorage.addProduct(newProduct);
            System.out.println("Successfully requested new product with ID " + newProduct.getId());
        }
    }
}
