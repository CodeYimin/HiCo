package storage;

import java.util.Scanner;

import products.Product;

public interface ProductCreator {
    public boolean canCreateFromKeyboard(String createProductType);

    public boolean canCreateFromStorageData(String[] storageData);

    public Product createFromKeyboard(Scanner keyboard, int newId);

    public Product createFromStorageData(String[] storageData);
}
