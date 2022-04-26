
import java.io.File;
import java.util.Scanner;

import commands.Command;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.HoldCommand;
import commands.ListCommand;
import commands.MakeAvailableCommand;
import commands.RequestCommand;
import commands.SoldCommand;
import core.CommandManager;
import products.Bodywear;
import products.Car;
import storage.ProductStorage;
import storage.ProductStorageProcessor;

public class Main {
    private static final String PRODUCT_STORAGE_FILE_PATH = "../data/products.txt";
    private static final File PRODUCT_STORAGE_FILE = new File(PRODUCT_STORAGE_FILE_PATH);

    public static void main(String[] args) throws Exception {
        ProductStorageProcessor[] productStorageProcessors = { new Bodywear.StorageProcessor(),
                new Car.StorageProcessor() };
        ProductStorage productStorage = new ProductStorage(PRODUCT_STORAGE_FILE, productStorageProcessors);

        System.out.println("Welcome to HiCo Inventory Management System! Type \"help\" for a list of commands.");

        Command[] commands = { new ExitCommand(), new HelpCommand(), new ListCommand(), new RequestCommand(),
                new SoldCommand(),
                new HoldCommand(), new MakeAvailableCommand() };
        Scanner keyboard = new Scanner(System.in);

        CommandManager commandManager = new CommandManager(commands, productStorage, keyboard);
        commandManager.startListening();
    }
}