package util;

import java.io.IOException;

public class DBProperties {

	private static DBProperties properties = new DBProperties();
	static {
		try {
			/*
			 * Hãy tìm file DB.properties nằm trong classpath (tức là nằm cùng nơi với các .class sau khi biên dịch).
			 * Tomcat sẽ tự copy DB.properties vào: WEB-INF/classes/DB.properties nơi mà ClassLoader của webapp có thể tìm thấy.
			 */
			properties.load(DBProperties.class.getClassLoader().getResourceAsStream("DB.properties"));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static String host = properties.getProperty("db.host");
	public static String port = properties.getProperty("db.port");
	public static String username = properties.getProperty("db.username");
	public static String password = properties.getProperty("db.password");
	public static String name = properties.getProperty("db.name");
}
