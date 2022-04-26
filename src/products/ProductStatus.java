package products;

public class ProductStatus {
    public static final String REQUESTED = "requested";
    public static final String AVAILABLE = "available";
    public static final String ON_HOLD = "on_hold";
    public static final String SOLD = "sold";

    public static boolean isValidStatusChange(String fromStatus, String toStatus) {
        String[][] validStatusChanges = {
                { REQUESTED, AVAILABLE },
                { AVAILABLE, ON_HOLD },
                { AVAILABLE, SOLD },
                { ON_HOLD, SOLD },
                { ON_HOLD, AVAILABLE },
                { SOLD, AVAILABLE }
        };

        for (String[] validStatusChange : validStatusChanges) {
            String validFromStatus = validStatusChange[0];
            String validToStatus = validStatusChange[1];
            if (validFromStatus.equals(fromStatus) && validToStatus.equals(toStatus)) {
                return true;
            }
        }

        return false;
    }
}
