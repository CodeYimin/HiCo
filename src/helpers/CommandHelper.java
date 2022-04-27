package helpers;

import java.util.Scanner;

import products.Product;
import storage.ProductStorage;
import utils.ArrayUtils;
import utils.InputUtils;

public class CommandHelper {
    public static Product promptProductStatusChange(Scanner keyboard, ProductStorage productStorage,
            String[] eligibleFromStatuses, String toStatus, String successMessage, String failMessage) {
        int id = InputUtils.promptInt(keyboard, "Enter a product ID: ");

        Product product = productStorage.getProduct(id);
        if (product == null) {
            System.out.println("Product ID " + id + " does not exist.");
            return null;
        }

        boolean fromStatusIsEligible = ArrayUtils.includes(eligibleFromStatuses, product.getStatus());
        if (!fromStatusIsEligible) {
            System.out.println(failMessage);
            return null;
        }

        product.setStatus(toStatus);
        boolean successfullySyncedToStorage = productStorage.syncProduct(product);
        if (!successfullySyncedToStorage) {
            System.out.println(failMessage);
            return null;
        }

        System.out.println(successMessage);
        return product;
    }
}
