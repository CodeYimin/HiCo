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

    public static boolean isValidStatusChange(String fromStatus, String toStatus) {
        String[][] validStatusChanges = {
                { REQUESTED, AVAILABLE },
                { AVAILABLE, ON_HOLD },
                { AVAILABLE, SOLD },
                { ON_HOLD, SOLD },
                {},
                { SOLD, AVAILABLE }
        };

        for (String[] validStatusChange : validStatusChanges) {
            String validFromStatus = validStatusChange[0];
            String validToStatus = validStatusChange[1];
            if (validFromStatus.equalsIgnoreCase(fromStatus) && validToStatus.equalsIgnoreCase(toStatus)) {
                return true;
            }
        }

        return false;
    }
}
