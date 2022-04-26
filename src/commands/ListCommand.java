package commands;

import java.util.Arrays;
import java.util.Scanner;

import core.CommandManager;
import products.Product;
import products.ProductFilter;
import products.ProductStatus;
import products.ProductType;
import storage.ProductStorage;
import utils.ArrayUtils;
import utils.InputUtils;

public class ListCommand implements Command {
    private final String name = "list";
    private final String description = "Print a list of all products with optional filters and sorting.";

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
        String useFilters = keyboard.nextLine();

        if (!useFilters.equalsIgnoreCase("y")) {
            System.out.println("Entire inventory: ");
            System.out.println(productStorage);
            return;
        }

        String[] statusOptions = ProductStatus.getAllStatuses();
        String status = InputUtils.promptString(keyboard, "Status filter " + Arrays.toString(statusOptions) + ": ",
                statusOptions, true);

        String[] typeOptions = ProductType.getAllTypes();
        String type = InputUtils.promptString(keyboard, "Type filter " + Arrays.toString(typeOptions) + ": ",
                typeOptions, true);

        String[] sortByOptions = { "id", "name", "price" };
        String sortBy = InputUtils.promptString(keyboard, "Sort by " + Arrays.toString(sortByOptions) + ": ",
                sortByOptions, false);

        ProductFilter statusFilter = null;
        ProductFilter typeFilter = null;

        // STATUS FILTER
        if (status.trim().length() == 0) {
            // Leave filter as null
        } else {
            statusFilter = new Product.StatusFilter(ProductStatus.fromString(status));
        }

        // TYPE FILTER
        if (type.trim().length() == 0) {
            // Leave filter as null
        } else {
            typeFilter = new Product.TypeFilter(ProductType.fromString(type));
        }

        ProductFilter[] filters = {};
        if (statusFilter != null) {
            filters = ArrayUtils.withElementAdded(filters, statusFilter);
        }
        if (typeFilter != null) {
            filters = ArrayUtils.withElementAdded(filters, typeFilter);
        }

        Product[] filteredProducts = productStorage.getProducts(filters);

        // SORT BY
        if (sortBy.equalsIgnoreCase("price")) {
            Arrays.sort(filteredProducts, new Product.PriceComparator());
        } else if (sortBy.equalsIgnoreCase("name")) {
            Arrays.sort(filteredProducts, new Product.NameComparator());
        } else if (sortBy.equalsIgnoreCase("id")) {
            Arrays.sort(filteredProducts, new Product.IdComparator());
        }

        if (filteredProducts.length == 0) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("Filtered products: ");
        for (Product product : filteredProducts) {
            System.out.println("- " + product);
        }
    }
}
