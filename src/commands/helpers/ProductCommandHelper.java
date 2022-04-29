package commands.helpers;

import java.util.Scanner;

import commands.ProductCommand;
import core.ProductStorage;
import products.Product;
import utils.ArrayUtils;
import utils.InputUtils;

/**
 * Helper class for {@link ProductCommand}s.
 */
public class ProductCommandHelper {
    /**
     * Prompts the user to select a product from the product storage by id.
     *
     * @param keyboard
     *            the keyboard to read from
     * @param productStorage
     *            the product storage to search
     * @param prompt
     *            The message displayed when asking user for ID
     * @return the selected product
     */
    public static Product promptProductUsingId(Scanner keyboard, ProductStorage productStorage, String prompt) {
        // Get product ID from user
        int productId = InputUtils.promptInt(keyboard, prompt);

        // Get product from storage
        Product product;
        try {
            product = productStorage.getProduct(productId);
        } catch (Exception e) {
            System.out.println("Failed to get product.");
            return null;
        }

        return product;
    }

    /**
     * Prompts the user for a product to change the status of.
     * The method then attempts to change the status of the product. If the product
     * is found, and its current status is part of eligibleFromStatuses, its status
     * is changed to toStatus.
     * Otherwise, nothing is changed and the failMessage is printed.
     * 
     * @param keyboard
     *            The Scanner object to read input from.
     * @param productStorage
     *            The product storage to save the changes to.
     * @param eligibleFromStatuses
     *            The statuses that the product can be changed from.
     * @param toStatus
     *            The status to change the product to.
     * @param successMessage
     *            The message to print if the product is successfully changed.
     * @param failMessage
     *            The message to print if the product's current status is not
     *            eligible for change.
     * @return The product that was changed, or null if the product was not found or
     *         wasn't changed.
     */
    public static Product promptProductStatusChange(Scanner keyboard, ProductStorage productStorage,
            String[] eligibleFromStatuses, String toStatus, String successMessage, String failMessage) {
        Product product = promptProductUsingId(keyboard, productStorage, "Enter a product ID: ");

        if (product == null) {
            System.out.println("Product ID does not exist.");
            return null;
        }

        boolean fromStatusIsEligible = ArrayUtils.includes(eligibleFromStatuses, product.getStatus());
        if (!fromStatusIsEligible) {
            System.out.println(failMessage);
            return null;
        }

        // Change product status
        product.setStatus(toStatus);

        // Persist the change
        try {
            productStorage.syncProduct(product);
        } catch (Exception e) {
            System.out.println("Failed to update product.");
            return null;
        }

        System.out.println(successMessage);
        return product;
    }
}
