package products;

public class ProductType {
    public static final String BODYWEAR = "Bodywear";
    public static final String CAR = "Car";

    public static String fromString(String type) {
        for (String t : getAllTypes()) {
            if (t.equalsIgnoreCase(type.trim())) {
                return type;
            }
        }

        return null;
    }

    public static String[] getAllTypes() {
        return new String[] { BODYWEAR, CAR };
    }

}
