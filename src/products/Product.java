package products;

import java.util.Scanner;

import products.creators.BodywearCreator;
import products.creators.CarAndElectronicPackageCreator;
import products.creators.CarCreator;
import products.creators.ElectronicCreator;
import products.creators.ProductCreator;
import utils.ArrayUtils;

public abstract class Product {
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

    private static String encodeFields(String[] fields) {
        String[] encodedFields = ArrayUtils.replaceAll(fields, ",", "\\\\,");
        return ArrayUtils.join(encodedFields, ",");
    }

    private static String[] decodeFields(String data) {
        String[] decodedData = data.split("(?<!\\\\),");
        decodedData = ArrayUtils.replaceAll(decodedData, "\\\\,", ",");
        return decodedData;
    }

    public abstract String[] extraStorageFields();

    public final String toStorageString() {
        String[] storageFields = {
                String.valueOf(getId()),
                getType(),
                getName(),
                getStatus(),
                getDescription(),
                String.valueOf(getPrice())
        };
        String[] fullStorageFields = ArrayUtils.concat(storageFields, extraStorageFields());

        return encodeFields(fullStorageFields);
    }

    public static Product fromStorageString(String storageString) {
        String[] storageFields = decodeFields(storageString);
        String type = storageFields[1];
        for (ProductCreator<?> productCreator : PRODUCT_CREATORS) {
            if (productCreator.getType().equals(type)) {
                return productCreator.createFromStorageFields(storageFields);
            }
        }
        return null;
    }

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
