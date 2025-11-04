package util;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	/*
	 * Các lớp dao con chỉ can tao
	 *  : Connection conn = DBConnector.getConnection();
	 *  
	 *  
	 *   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
  <script src="<%=request.getContextPath()%>/js/login.js"></script>
	 */
	 private static final String URL = "jdbc:sqlserver://" 
		        + DBProperties.host + ":" + DBProperties.port 
		        + ";databaseName=" + DBProperties.name 
		        + ";encrypt=false;trustServerCertificate=true";

		    private static final String USER = DBProperties.username;
		    private static final String PASS = DBProperties.password;

		    static {
		        try {
		            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        } catch (ClassNotFoundException e) {
		            throw new RuntimeException("SQL Server Driver not found", e);
		        }
		    }

		    public static DBConnector getConnection() throws SQLException {
		        return DriverManager.getConnection(URL, USER, PASS);
		    }

}
