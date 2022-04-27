package commands;

import java.util.Scanner;

import core.CommandManager;
import helpers.CommandHelper;
import products.ProductStatus;
import storage.ProductStorage;

public class PutOnHoldCommand implements Command {
    private final String name = "putOnHold";
    private final String description = "Place an item on hold for online orders.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = commandManager.getProductStorage();

        String[] eligibleFromStatuses = { ProductStatus.AVAILABLE };
        String toStatus = ProductStatus.ON_HOLD;
        String successMessage = "Successfully placed the item on hold for the online order.";
        String failMessage = "Failed to placed the item on hold.";
        CommandHelper.promptProductStatusChange(keyboard, productStorage, eligibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
