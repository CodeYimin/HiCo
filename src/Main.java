
import java.io.File;
import java.util.Scanner;

import commands.AddItemCommand;
import commands.Command;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.PutOnHoldCommand;
import commands.RequestCommand;
import commands.ReturnCommand;
import commands.SellCommand;
import core.CommandManager;
import core.FileManager;
import products.Bodywear;
import products.Car;
import storage.ProductStorage;
import storage.ProductStorageConverter;

public class Main {
    private static final String PRODUCT_STORAGE_FILE_PATH = "../data/products.txt";

    public static void main(String[] args) throws Exception {
        FileManager productStorageFileManager = new FileManager(new File(PRODUCT_STORAGE_FILE_PATH));
        ProductStorageConverter[] productStorageProcessors = { new Bodywear.StorageProcessor(), new Car.StorageProcessor() };
        ProductStorage productStorage = new ProductStorage(productStorageFileManager, productStorageProcessors);

        Command[] commands = {
                new ExitCommand(),
                new HelpCommand(),
                new ListCommand(),
                new RequestCommand(),
                new AddItemCommand(),
                new PutOnHoldCommand(),
                new SellCommand(),
                new ReturnCommand()
        };
        Scanner keyboard = new Scanner(System.in);
        CommandManager commandManager = new CommandManager(commands, productStorage, keyboard);

        System.out.println("Welcome to HiCo Inventory Management System! Type \"help\" for a list of commands.");
        commandManager.startListening();
    }
}