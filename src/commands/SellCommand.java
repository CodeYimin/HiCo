package commands;

import java.util.Scanner;

import core.CommandManager;
import helpers.CommandHelper;
import products.ProductStatus;
import storage.ProductStorage;

public class SellCommand extends ProductStorageCommand {
    public SellCommand(String name, String description, ProductStorage productStorage) {
        super(name, description, productStorage);
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        String[] eligibleFromStatuses = { ProductStatus.AVAILABLE, ProductStatus.ON_HOLD };
        String toStatus = ProductStatus.SOLD;
        String successMessage = "Successfully removed the item from the inventory. (Marked as sold)";
        String failMessage = "Failed to remove the item from the inventory.";
        CommandHelper.promptProductStatusChange(keyboard, productStorage, eligibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
