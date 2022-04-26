package commands;

import java.util.Scanner;

import core.CommandManager;
import products.ProductStatus;
import storage.ProductStorage;
import utils.CommandUtils;

public class AddItemCommand implements Command {
    private final String name = "add";
    private final String description = "Add an item to inventory (e.g. when a shipment from the supplier arrives, or when returned).";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = commandManager.getProductStorage();

        String[] possibleFromStatuses = { ProductStatus.REQUESTED, ProductStatus.RETURNED };
        String toStatus = ProductStatus.AVAILABLE;
        String successMessage = "Successfully made product available.";
        String failMessage = "Failed to make product available.";
        CommandUtils.promptProductStatusChange(keyboard, productStorage, possibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
