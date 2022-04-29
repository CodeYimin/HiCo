package commands;

import java.util.Scanner;

import commands.helpers.ProductCommandHelper;
import core.CommandManager;
import core.ProductStorage;
import products.Product;
import utils.InputUtils;

public class DeleteCommand extends ProductCommand {
    public DeleteCommand(String name, String description, ProductStorage productStorage) {
        super(name, description, productStorage);
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        boolean verify = InputUtils.promptBoolean(keyboard,
                "WARNING: You are about to permanently delete a product from the system. Continue? [y/N] ",
                "y", "N", false);
        if (!verify) {
            System.out.println("Delete cancelled.");
            return;
        }

        Product product = ProductCommandHelper.promptProductUsingId(keyboard, productStorage, "Enter the product ID to delete: ");
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        Product removedProduct;
        try {
            removedProduct = productStorage.removeProduct(product);
        } catch (Exception error) {
            System.out.println("Failed to delete product.");
            return;
        }

        if (removedProduct == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Successfully deleted product.");
    }
}
