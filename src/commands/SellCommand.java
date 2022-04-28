package commands;

import java.util.Scanner;

import commands.helpers.ProductCommandHelper;
import core.CommandManager;
import core.ProductStorage;
import products.constants.ProductStatus;

/**
 * Command to mark a product from an available or on hold state to sold state.
 */
public class SellCommand extends ProductCommand {
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
        ProductCommandHelper.promptProductStatusChange(keyboard, productStorage, eligibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
