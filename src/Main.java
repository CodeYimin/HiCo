
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
import products.Electronic;
import storage.ProductCreator;
import storage.ProductStorage;

public class Main {
    private static final String PRODUCT_STORAGE_FILE_PATH = "../data/products.txt";

    public static void main(String[] args) {
        FileManager productStorageFileManager = new FileManager(new File(PRODUCT_STORAGE_FILE_PATH));
        ProductCreator[] productCreators = {
                new Bodywear.Creator(),
                new Car.Creator(),
                new Electronic.Creator()
        };
        ProductStorage productStorage = new ProductStorage(productStorageFileManager, productCreators);

        Command[] commands = {
                new ExitCommand("exit", "Exit the program."),
                new HelpCommand("help", "Display a list of available commands."),
                new ListCommand("list", "List all products with optional filtering/sorting.", productStorage),
                new RequestCommand(
                        "request",
                        "Add an item to inventory (e.g. when a shipment from the supplier arrives, or when returned).",
                        productStorage,
                        productCreators),
                new AddItemCommand("add", "Add a new product to the store.", productStorage),
                new PutOnHoldCommand("hold", "Put a product on hold (e.g. for online orders).", productStorage),
                new SellCommand("sell", "Mark a product as sold.", productStorage),
                new ReturnCommand("return", "Return a product to the store.", productStorage)
        };
        Scanner keyboard = new Scanner(System.in);
        CommandManager commandManager = new CommandManager(commands, keyboard);

        System.out.println("Welcome to HiCo Inventory Management System! Type \"help\" for a list of commands.");
        commandManager.startListening();
    }
}