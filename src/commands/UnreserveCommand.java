package commands;

import java.util.Scanner;

import core.CommandManager;
import storage.ProductStorage;
import utils.InputUtils;

public class UnreserveCommand implements Command {
    private final String name = "unreserve";
    private final String description = "Makes a reserved product available again.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = commandManager.getProductStorage();
        int id = InputUtils.promptInt(keyboard, "Enter a product ID: ");

        boolean successfullyUnreserved = productStorage.unreserveProduct(id);
        if (successfullyUnreserved) {
            System.out.println("Successfully unreserved product with ID " + id);
        } else {
            System.out.println("Failed to unreserve product with ID " + id
                    + ". It probably does not exist or is not reserved.");
        }
    }

}
