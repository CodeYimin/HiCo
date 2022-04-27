package commands;

import storage.ProductStorage;

public abstract class ProductStorageCommand extends Command {
    private final ProductStorage productStorage;

    public ProductStorageCommand(String name, String description, ProductStorage productStorage) {
        super(name, description);
        this.productStorage = productStorage;
    }

    public ProductStorage getProductStorage() {
        return productStorage;
    }
}
