package commands;

import java.util.Scanner;

import core.CommandManager;
import helpers.CommandHelper;
import products.ProductStatus;
import storage.ProductStorage;

public class ReturnCommand implements Command {
    private final String name = "return";
    private final String description = "Return a product to the store.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = commandManager.getProductStorage();

        String[] eligibleFromStatuses = { ProductStatus.SOLD };
        String toStatus = ProductStatus.RETURNED;
        String successMessage = "Successfully returned the item to the store.";
        String failMessage = "Failed to return the item to the store.";
        CommandHelper.promptProductStatusChange(keyboard, productStorage, eligibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
