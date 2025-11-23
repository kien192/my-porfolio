package db;

import java.io.IOException;

public class Properties {

    private static final java.util.Properties properties = new java.util.Properties();

    static {
        try {
            properties.load(Properties.class.getClassLoader().getResourceAsStream("DB.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Không thể đọc file cấu hình DB.properties", e);
        }
    }

    // ---------------------------------------------------------------
    // Các thuộc tính cấu hình
    // ---------------------------------------------------------------

    public static final String host = properties.getProperty("db.host");

    public static final String port = properties.getProperty("db.port");

    public static final String username = properties.getProperty("db.username");

    public static final String password = properties.getProperty("db.password");

    public static final String name = properties.getProperty("db.name");

    private Properties() {
    }

}
