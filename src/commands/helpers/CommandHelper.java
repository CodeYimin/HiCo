package commands.helpers;

import java.util.Scanner;

import core.ProductStorage;
import products.Product;
import utils.ArrayUtils;
import utils.InputUtils;

public class CommandHelper {
    public static Product promptProductStatusChange(Scanner keyboard, ProductStorage productStorage,
            String[] eligibleFromStatuses, String toStatus, String successMessage, String failMessage) {
        int id = InputUtils.promptInt(keyboard, "Enter a product ID: ");

        Product product;
        try {
            product = productStorage.getProduct(id);
        } catch (Exception e) {
            System.out.println("Failed to get product.");
            return null;
        }
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
        try {
            productStorage.syncProduct(product);
            System.out.println(successMessage);
            return product;
        } catch (Exception e) {
            System.out.println(failMessage);
            return null;
        }
    }
}
