package commands;

import java.util.Arrays;
import java.util.Scanner;

import core.CommandManager;
import core.ProductStorage;
import products.Product;
import products.comparators.ProductIdComparator;
import products.comparators.ProductNameComparator;
import products.comparators.ProductPriceComparator;
import products.constants.ProductStatus;
import products.constants.ProductType;
import products.filters.ProductFilter;
import products.filters.ProductStatusFilter;
import products.filters.ProductTypeFilter;
import utils.ArrayUtils;
import utils.InputUtils;

/**
 * Command to list all available products.
 * With optional filters and sorting options.
 */
public class ListCommand extends ProductCommand {
    public ListCommand(String name, String description, ProductStorage productStorage) {
        super(name, description, productStorage);
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        // Enable filters?
        String[] useFilterOptions = { "y", "n" };
        String useFilter = InputUtils.promptString(keyboard, "Use filters? (y/N): ", useFilterOptions, true);

        // Print everything if no filters are used
        if (!useFilter.equalsIgnoreCase("y")) {
            Product[] products = null;

            try {
                products = productStorage.getProducts();
            } catch (Exception e) {
                System.out.println("Failed to retrieve products.");
            }

            if (products == null || products.length == 0) {
                System.out.println("No products.");
            } else {
                System.out.println("All products:");
                System.out.println(ArrayUtils.join(products, "\n\n"));
            }
            return;
        }

        // Ask user for filters
        final boolean ALLOW_BLANK_STATUS = true;
        final boolean ALLOW_BLANK_TYPE = true;
        final boolean ALLOW_BLANK_SORT_BY = true;

        String[] statusOptions = ProductStatus.getAllStatuses();
        String statusPrompt = "(Enter to skip) Status filter " + Arrays.toString(statusOptions) + ": ";
        String statusInput = InputUtils.promptString(keyboard, statusPrompt, statusOptions, ALLOW_BLANK_STATUS);
        String status = ProductStatus.fromString(statusInput);

        String[] typeOptions = ProductType.getAllTypes();
        String typePrompt = "(Enter to skip) Type filter " + Arrays.toString(typeOptions) + ": ";
        String typeInput = InputUtils.promptString(keyboard, typePrompt, typeOptions, ALLOW_BLANK_TYPE);
        String type = ProductType.fromString(typeInput);

        String[] sortOptions = { "id", "name", "price" };
        String sortPrompt = "(Enter to skip) Sort by " + Arrays.toString(sortOptions) + ": ";
        String sort = InputUtils.promptString(keyboard, sortPrompt, sortOptions, ALLOW_BLANK_SORT_BY);

        // Save filters
        ProductFilter[] filters = {};
        if (status != null) {
            filters = ArrayUtils.concat(filters, new ProductStatusFilter(status));
        }
        if (type != null) {
            filters = ArrayUtils.concat(filters, new ProductTypeFilter(type));
        }

        // Get filtered products
        Product[] filteredProducts;
        try {
            filteredProducts = productStorage.getProducts(filters);
        } catch (Exception error) {
            System.out.println("Failed to get products.");
            return;
        }

        // Apply sort to filtered products
        if (sort.equalsIgnoreCase("price")) {
            Arrays.sort(filteredProducts, new ProductPriceComparator());
        } else if (sort.equalsIgnoreCase("name")) {
            Arrays.sort(filteredProducts, new ProductNameComparator());
        } else if (sort.equalsIgnoreCase("id")) {
            Arrays.sort(filteredProducts, new ProductIdComparator());
        } else {
            // Do nothing (don't sort)
        }

        // No filtered products found
        if (filteredProducts.length == 0) {
            System.out.println("No products found.");
            return;
        }

        // Print filtered products
        System.out.println("Filtered products: ");
        System.out.println(ArrayUtils.join(filteredProducts, "\n\n"));
    }
}
