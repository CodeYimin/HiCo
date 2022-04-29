
import java.io.File;
import java.util.Scanner;

import commands.AddItemCommand;
import commands.Command;
import commands.DeleteCommand;
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
	private static final String PRODUCT_STORAGE_FILE_PATH = "../data/test.txt";
	private static final String WELCOME_MESSAGE = "Welcome to HiCo Inventory Management System! Type \"help\" for a list of commands.";

	public static void main(String[] args) {
		// Initialize product storage
		File productStorageFile = new File(PRODUCT_STORAGE_FILE_PATH);
		FileManager productStorageFileManager = new FileManager(productStorageFile);
		ProductStorage productStorage = new ProductStorage(productStorageFileManager);

		// Initialize commands and command manager
		Command[] commands = {
				new ExitCommand("exit", "Exit the program."),
				new HelpCommand("help", "Display a list of available commands."),
				new ListCommand("list", "List all products with optional filtering/sorting.", productStorage),
				new RequestCommand(
						"request",
						"Request from the supplier a new product to be added to the store",
						productStorage),
				new AddItemCommand(
						"add",
						"Add an item to inventory (e.g. when a request from the supplier arrives, or when returned).",
						productStorage),
				new HoldCommand("hold", "Put a product on hold (e.g. for online orders).", productStorage),
				new SellCommand("sell", "Mark a product as sold.", productStorage),
				new ReturnCommand("return", "Return a product to the store.", productStorage),
				new DeleteCommand("delete", "Permanently deletes a product record from this system.", productStorage)
		};
		Scanner keyboard = new Scanner(System.in);
		CommandManager commandManager = new CommandManager(commands, keyboard);

		// Start the program
		System.out.println(WELCOME_MESSAGE);
		commandManager.startListening();
	}
}