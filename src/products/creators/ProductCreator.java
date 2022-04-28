package products.creators;

import java.util.Scanner;

import products.Product;

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
