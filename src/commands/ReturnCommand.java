package commands;

import java.util.Scanner;

import core.CommandManager;
import helpers.CommandHelper;
import products.ProductStatus;
import storage.ProductStorage;

public class ReturnCommand extends ProductStorageCommand {
    public ReturnCommand(String name, String description, ProductStorage productStorage) {
        super(name, description, productStorage);
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        String[] eligibleFromStatuses = { ProductStatus.SOLD };
        String toStatus = ProductStatus.RETURNED;
        String successMessage = "Successfully returned the item to the store.";
        String failMessage = "Failed to return the item to the store.";
        CommandHelper.promptProductStatusChange(keyboard, productStorage, eligibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
