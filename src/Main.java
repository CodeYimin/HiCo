
import java.io.File;
import java.util.Scanner;

import commands.AddCommand;
import commands.Command;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.RemoveCommand;
import commands.ReserveCommand;
import commands.UnreserveCommand;
import core.CommandManager;
import products.Bodywear;
import products.Car;
import storage.ProductStorage;
import storage.ProductStorageProcessor;

public class Main {
    private static final String PRODUCT_STORAGE_FILE_PATH = "../inventory/productStorage.txt";
    private static final File PRODUCT_STORAGE_FILE = new File(PRODUCT_STORAGE_FILE_PATH);

    public static void main(String[] args) throws Exception {
        ProductStorageProcessor[] productStorageProcessors = { new Bodywear.StorageProcessor(),
                new Car.StorageProcessor() };
        ProductStorage productStorage = new ProductStorage(PRODUCT_STORAGE_FILE, productStorageProcessors);

        System.out.println("Welcome to HiCo Inventory Management System! Type \"help\" for a list of commands.");
        Command[] commands = { new ListCommand(), new AddCommand(), new RemoveCommand(),
                new ReserveCommand(), new UnreserveCommand(), new ExitCommand(), new HelpCommand() };
        CommandManager commandManager = new CommandManager(commands, productStorage, new Scanner(System.in));
        commandManager.startListening();
    }
}