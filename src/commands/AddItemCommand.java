package commands;

import java.util.Scanner;

import commands.helpers.ProductCommandHelper;
import core.CommandManager;
import core.ProductStorage;
import products.constants.ProductStatus;

/**
 * Command to mark a product as available. The target product must be in a
 * requested or returned state.
 */
public class AddItemCommand extends ProductCommand {
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
        ProductCommandHelper.promptProductStatusChange(keyboard, productStorage, eligibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
