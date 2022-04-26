package commands;

import java.util.Scanner;

import core.CommandManager;
import products.ProductStatus;
import storage.ProductStorage;
import utils.CommandUtils;

public class SellCommand implements Command {
    private final String name = "sell";
    private final String description = "Remove an item from the inventory. (Mark as sold)";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = commandManager.getProductStorage();

        String[] possibleFromStatuses = { ProductStatus.AVAILABLE, ProductStatus.ON_HOLD };
        String toStatus = ProductStatus.SOLD;
        String successMessage = "Successfully removed the item from the inventory. (Marked as sold)";
        String failMessage = "Failed to remove the item from the inventory.";
        CommandUtils.promptProductStatusChange(keyboard, productStorage, possibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
