package products.creators;

import java.util.Scanner;

import products.Product;

/**
 * The base class for all product creators which creates products
 * through user keyboard input or file storage input.
 * 
 * @param <T>
 *            The type of product the creator creates.
 */
public abstract class ProductCreator<T extends Product> {
    private final String type;

    public ProductCreator(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract T createFromKeyboard(Scanner keyboard, int newId);

    public abstract T createFromStorageFields(String[] storageFields);
}
