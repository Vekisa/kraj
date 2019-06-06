package modul.zuul.model;

public class Regex {

    public static String stringLong = "[0-9a-zA-Z._ ]{3,30}";
    public static String stringShort = "[0-9a-zA-Z._ ]{1,30}";
    public static String serialNumber = "[0-9]{1,50}";
    public static String password = "[a-zA-Z0-9_!?*#/]*";
    public static String username = "[a-zA-Z0-9_]*";
    public static String flNames = "[a-zA-Z]*";
    public static String email = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

}
