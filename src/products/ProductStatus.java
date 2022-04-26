package products;

public class ProductStatus {
    public static final String REQUESTED = "requested";
    public static final String AVAILABLE = "available";
    public static final String ON_HOLD = "on_hold";
    public static final String SOLD = "sold";
    public static final String RETURNED = "returned";

    public static String getStatus(String status) {
        for (String s : getAllStatuses()) {
            if (s.equalsIgnoreCase(status.trim())) {
                return s;
            }
        }
        return null;
    }

    public static String[] getAllStatuses() {
        return new String[] { REQUESTED, AVAILABLE, ON_HOLD, SOLD, RETURNED
        };
    }
}
