package commands;

import java.util.Scanner;

import commands.helpers.CommandHelper;
import core.CommandManager;
import core.ProductStorage;
import products.constants.ProductStatus;

public class HoldCommand extends ProductCommand {
    public HoldCommand(String name, String description, ProductStorage productStorage) {
        super(name, description, productStorage);
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        String[] eligibleFromStatuses = { ProductStatus.AVAILABLE };
        String toStatus = ProductStatus.ON_HOLD;
        String successMessage = "Successfully placed the item on hold for the online order.";
        String failMessage = "Failed to placed the item on hold.";
        CommandHelper.promptProductStatusChange(keyboard, productStorage, eligibleFromStatuses, toStatus, successMessage, failMessage);
    }
}
