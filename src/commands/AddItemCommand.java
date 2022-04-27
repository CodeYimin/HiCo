package commands;

import java.util.Scanner;

import core.CommandManager;
import helpers.CommandHelper;
import products.ProductStatus;
import storage.ProductStorage;

public class AddItemCommand extends ProductStorageCommand {
    public AddItemCommand(String name, String description, ProductStorage productStorage) {
        super(name, description, productStorage);
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        String[] eligibleFromStatuses = { ProductStatus.REQUESTED, ProductStatus.RETURNED };
        String toStatus = ProductStatus.AVAILABLE;
        String successMessage = "Successfully added item to inventory.";
        String failMessage = "Failed to add item to inventory.";
        CommandHelper.promptProductStatusChange(keyboard, productStorage, eligibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
