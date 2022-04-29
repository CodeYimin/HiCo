package products.constants;

public class ProductType {
    public static final String BODYWEAR = "Bodywear";
    public static final String CAR = "Car";
    public static final String ELECTRONIC = "Electronic";
    public static final String CAR_AND_ELECTRONIC_PACKAGE = "CarAndElectronicPackage";

    public static String[] getAllTypes() {
        return new String[] { BODYWEAR, CAR, ELECTRONIC, CAR_AND_ELECTRONIC_PACKAGE };
    }

    /**
     * Get a type constant given a string. Used for
     * user input scenarios where the casing might not match the constant exactly.
     * The method also removes any leading or trailing whitespace.
     * 
     * @param type
     *            The type string to get the constant for.
     * @return The corrected type constant or null if the type is not found.
     */
    public static String fromString(String type) {
        for (String t : getAllTypes()) {
            if (t.equalsIgnoreCase(type.trim())) {
                return t;
            }
        }

        return null;
    }
}
