package commands;

import java.util.Arrays;
import java.util.Scanner;

import core.CommandManager;
import products.Bodywear;
import products.Car;
import products.Product;
import products.ProductFilter;
import products.ProductStatus;
import storage.ProductStorage;
import utils.InputUtils;

public class ListCommand implements Command {
    private final String name = "list";
    private final String description = "List all products with filters.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        ProductStorage productStorage = commandManager.getProductStorage();
        Scanner keyboard = commandManager.getKeyboard();

        System.out.print("Would you like to apply filters (y/N): ");
        String useFilters = keyboard.nextLine().toLowerCase();

        if (!useFilters.equals("y")) {
            System.out.println("Entire inventory: ");
            System.out.println(productStorage);
            return;
        }

        String[] statusOptions = { "all", "available", "reserved", "requested" };
        String status = InputUtils.promptString(keyboard, "Status filter (All, available, reserved, requested): ",
                statusOptions);

        String[] typeOptions = { "all", "car", "bodywear" };
        String type = InputUtils.promptString(keyboard, "Type filter (All, car, bodywear): ", typeOptions);

        String[] sortByOptions = { "id", "name", "price" };
        String sortBy = InputUtils.promptString(keyboard, "Sort by (id, name, price): ", sortByOptions);

        ProductFilter statusFilter = null;
        ProductFilter typeFilter = null;

        // STATUS FILTER
        if (status.equals("all")) {
            // Leave filter as null
        } else if (status.equals("available")) {
            statusFilter = new Product.StatusFilter(ProductStatus.AVAILABLE);
        } else if (status.equals("reserved")) {
            statusFilter = new Product.StatusFilter(ProductStatus.RESERVED);
        } else if (status.equals("requested")) {
            statusFilter = new Product.StatusFilter(ProductStatus.RESERVED);
        }

        // TYPE FILTER
        if (type.equals("all")) {
            // Leave filter as null
        } else if (type.equals("car")) {
            typeFilter = new Product.TypeFilter<Car>(Car.class);
        } else if (type.equals("bodywear")) {
            typeFilter = new Product.TypeFilter<Bodywear>(Bodywear.class);
        }

        ProductFilter[] filters = { statusFilter, typeFilter };
        Product[] filteredProducts = productStorage.getProducts(filters);

        // SORT BY
        if (sortBy.equals("price")) {
            Arrays.sort(filteredProducts, new Product.PriceComparator());
        } else if (sortBy.equals("name")) {
            Arrays.sort(filteredProducts, new Product.NameComparator());
        } else if (sortBy.equals("id")) {
            Arrays.sort(filteredProducts, new Product.IdComparator());
        }

        if (filteredProducts.length == 0) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("\nFiltered products: ");
        for (Product product : filteredProducts) {
            System.out.println("- " + product);
        }
    }
}
