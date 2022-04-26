package utils;

import java.util.Scanner;

import products.Product;
import storage.ProductStorage;

public class CommandUtils {
    public static Product promptProductStatusChange(Scanner keyboard, ProductStorage productStorage,
            String[] possibleFromStatuses, String toStatus, String successMessage, String failMessage) {
        int id = InputUtils.promptInt(keyboard, "Enter a product ID: ");

        Product product = productStorage.getProduct(id);
        if (product == null) {
            System.out.println("Product ID " + id + " does not exist.");
            return null;
        }

        if (ArrayUtils.includes(possibleFromStatuses, product.getStatus())) {
            product.setStatus(toStatus);
            productStorage.syncProduct(product);
            System.out.println(successMessage);
            return product;
        } else {
            System.out.println(failMessage);
            return null;
        }
    }
}
