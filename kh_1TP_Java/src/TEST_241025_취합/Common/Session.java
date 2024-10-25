package TEST_241025_취합.Common;

public class Session {
    public static String loggedInUserId = null;
    public static String storeId = null;
    public static boolean isAdminLoggedIn = false;
    public static boolean isHQLoggedIn = false;

    public static void clear() {
        loggedInUserId = null;
        isAdminLoggedIn = false;
        isHQLoggedIn = false;
    }
}