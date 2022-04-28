package products;

import java.util.Scanner;

import products.creators.BodywearCreator;
import products.creators.CarAndElectronicPackageCreator;
import products.creators.CarCreator;
import products.creators.ElectronicCreator;
import products.creators.ProductCreator;
import utils.ArrayUtils;

public abstract class Product {
    // Product creators are used to create products from keyboard input
    // in the static method Product#fromKeyboard
    private static final ProductCreator<?>[] PRODUCT_CREATORS = {
            new BodywearCreator(),
            new CarCreator(),
            new ElectronicCreator(),
            new CarAndElectronicPackageCreator()
    };

    private final int id;
    private final String type;
    private String status;
    private String name;
    private String description;
    private double price;

    public Product(int id, String type, String name, String status, String description, double price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Converts a string array to a single string separated by ","
     * and all original "," from the content in the array are replaced with "\,",
     * so that the commas from the content don't seem like a separator.
     * 
     * @param fields
     * @return
     */
    private static String encodeFields(String[] fields) {
        String[] encodedFields = ArrayUtils.replaceAll(fields, ",", "\\\\,");
        return ArrayUtils.join(encodedFields, ",");
    }

    /**
     * Converts a single string separated by "," to a string array,
     * and all "\," from the strings in the array are replaced with ","
     * 
     * @param data
     * @return
     */
    private static String[] decodeFields(String data) {
        // Only split by commas not preceded by a backslash
        String[] decodedData = data.split("(?<!\\\\),");
        // Replace all "\," with "," after already splitting the original string
        decodedData = ArrayUtils.replaceAll(decodedData, "\\\\,", ",");
        return decodedData;
    }

    /**
     * Extra data child classes can override to store extra data
     */
    public abstract String[] extraStorageFields();

    /**
     * Returns a string representation of the product
     * ready to be stored in a file
     * 
     * @return Storage ready string representation of the product
     */
    public final String toStorageString() {
        // Base fields of the Product class which all child classes have
        String[] storageFields = {
                String.valueOf(getId()),
                getType(),
                getName(),
                getStatus(),
                getDescription(),
                String.valueOf(getPrice())
        };
        // Combine base fields with any extra fields child classes may have
        String[] fullStorageFields = ArrayUtils.concat(storageFields, extraStorageFields());

        // Encode the fields to be stored in a file safely
        return encodeFields(fullStorageFields);
    }

    /**
     * Instantiates a product object from a
     * storage string representation of the product
     * 
     * @param storageString
     * @return
     */
    public static Product fromStorageString(String storageString) {
        String[] storageFields = decodeFields(storageString);
        // First element is id, second is type
        String type = storageFields[1];
        // Find a product creator with the correct product type
        // and instantiate a new product with it
        for (ProductCreator<?> productCreator : PRODUCT_CREATORS) {
            if (productCreator.getType().equals(type)) {
                return productCreator.createFromStorageFields(storageFields);
            }
        }
        return null;
    }

    /**
     * Instantiates a product object from keyboard input
     */
    public static Product fromKeyboard(String type, Scanner keyboard, int newId) {
        for (ProductCreator<?> productCreator : PRODUCT_CREATORS) {
            if (productCreator.getType().equals(type)) {
                return productCreator.createFromKeyboard(keyboard, newId);
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Type: " + type
                + " | Name: " + name
                + " | Status: " + status
                + " | Description: " + description
                + " | Price: $" + price;
    }
}
