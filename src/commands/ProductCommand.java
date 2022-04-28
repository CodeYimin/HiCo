package commands;

import core.ProductStorage;

/**
 * The base class for all commands that require
 * access to a product storage to operate on.
 */
public abstract class ProductCommand extends Command {
    private final ProductStorage productStorage;

    public ProductCommand(String name, String description, ProductStorage productStorage) {
        super(name, description);
        this.productStorage = productStorage;
    }

    public ProductStorage getProductStorage() {
        return productStorage;
    }
}
