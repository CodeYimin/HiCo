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

        String[] statusOptions = { "all", "requested", "available", "on_hold", "sold" };
        String status = InputUtils.promptString(keyboard, "Status filter (All, requested, available, on_hold, sold): ",
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
        } else if (status.equals("requested")) {
            statusFilter = new Product.StatusFilter(ProductStatus.REQUESTED);
        } else if (status.equals("available")) {
            statusFilter = new Product.StatusFilter(ProductStatus.AVAILABLE);
        } else if (status.equals("on_hold")) {
            statusFilter = new Product.StatusFilter(ProductStatus.ON_HOLD);
        } else if (status.equals("sold")) {
            statusFilter = new Product.StatusFilter(ProductStatus.SOLD);
        }

        // TYPE FILTER
        if (type.equals("all")) {
            // Leave filter as null
        } else if (type.equals("car")) {
            typeFilter = new Product.TypeFilter(ProductType.CAR);
        } else if (type.equals("bodywear")) {
            typeFilter = new Product.TypeFilter(ProductType.BODYWEAR);
        }

        ProductFilter[] filters = {};
        if (statusFilter != null) {
            filters = ArrayUtils.withNewElement(filters, statusFilter);
        }
        if (typeFilter != null) {
            filters = ArrayUtils.withNewElement(filters, typeFilter);
        }

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

        System.out.println("Filtered products: ");
        for (Product product : filteredProducts) {
            System.out.println("- " + product);
        }
    }
}
