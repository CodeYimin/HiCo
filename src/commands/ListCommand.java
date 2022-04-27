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

public class ListCommand extends ProductStorageCommand {
    public ListCommand(String name, String description, ProductStorage productStorage) {
        super(name, description, productStorage);
    }

    public void execute(CommandManager commandManager) {
        Scanner keyboard = commandManager.getKeyboard();
        ProductStorage productStorage = getProductStorage();

        // Enable filters?
        String[] useFilterOptions = { "y", "n" };
        String useFilter = InputUtils.promptString(keyboard, "Use filters? (y/N): ", useFilterOptions, true);

        if (!useFilter.equalsIgnoreCase("y")) {
            System.out.println("All products: ");
            System.out.println(productStorage);
            return;
        }

        // Input filters
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

        // Apply filters
        ProductFilter[] filters = {};
        if (status != null) {
            ProductFilter statusFilter = new Product.StatusFilter(status);
            filters = ArrayUtils.withElement(filters, statusFilter);
        }
        if (type != null) {
            ProductFilter typeFilter = new Product.TypeFilter(type);
            filters = ArrayUtils.withElement(filters, typeFilter);
        }
        Product[] filteredProducts = productStorage.getProducts(filters);

        // Apply sort
        if (sort.equalsIgnoreCase("price")) {
            Arrays.sort(filteredProducts, new Product.PriceComparator());
        } else if (sort.equalsIgnoreCase("name")) {
            Arrays.sort(filteredProducts, new Product.NameComparator());
        } else if (sort.equalsIgnoreCase("id")) {
            Arrays.sort(filteredProducts, new Product.IdComparator());
        } else {
            // Do nothing (don't sort)
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
