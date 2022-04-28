
import java.io.File;
import java.util.Scanner;

import commands.AddItemCommand;
import commands.Command;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.HoldCommand;
import commands.ListCommand;
import commands.RequestCommand;
import commands.ReturnCommand;
import commands.SellCommand;
import core.CommandManager;
import core.FileManager;
import core.ProductStorage;

public class Main {
	private static final String PRODUCT_STORAGE_FILE_PATH = "C:/Users/Yimin/Documents/Programming/Java/Grade 11/Assignments/HiCo/data/test.txt";

	public static void main(String[] args) {
		File productStorageFile = new File(PRODUCT_STORAGE_FILE_PATH);
		FileManager productStorageFileManager = new FileManager(productStorageFile);
		ProductStorage productStorage = new ProductStorage(productStorageFileManager);

		Command[] commands = {
				new ExitCommand("exit", "Exit the program."),
				new HelpCommand("help", "Display a list of available commands."),
				new ListCommand("list", "List all products with optional filtering/sorting.", productStorage),
				new RequestCommand(
						"request",
						"Request a new product to be added to the store",
						productStorage),
				new AddItemCommand("add", "Add an item to inventory (e.g. when a shipment from the supplier arrives, or when returned).",
						productStorage),
				new HoldCommand("hold", "Put a product on hold (e.g. for online orders).", productStorage),
				new SellCommand("sell", "Mark a product as sold.", productStorage),
				new ReturnCommand("return", "Return a product to the store.", productStorage)
		};
		Scanner keyboard = new Scanner(System.in);
		CommandManager commandManager = new CommandManager(commands, keyboard);

		System.out.println("Welcome to HiCo Inventory Management System! Type \"help\" for a list of commands.");
		commandManager.startListening();
	}
}