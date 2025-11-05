package enhance;

public class DBUtil {
	package model.db;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	/**
	 * JDBCUtils
	 *
	 * Lớp tiện ích giúp đóng tài nguyên JDBC (Connection, PreparedStatement, ResultSet)
	 * tránh lặp lại code trong mỗi DAO.
	 *
	 * - Phù hợp với JDK cũ (chưa có try-with-resources)
	 * - Giúp mã DAO ngắn gọn, rõ ràng hơn
	 */
	public class JDBCUtils {

	    /**
	     * Đóng ResultSet, PreparedStatement, Connection (nếu không null).
	     * Mỗi lệnh đóng đều nằm trong khối try riêng biệt để không ảnh hưởng lẫn nhau.
	     *
	     * @param rs  ResultSet có thể null
	     * @param ps  PreparedStatement có thể null
	     * @param conn Connection có thể null
	     */
	    public static void closeQuietly(ResultSet rs, PreparedStatement ps, Connection conn) {
	        // Đóng ResultSet
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                System.err.println("Không thể đóng ResultSet: " + e.getMessage());
	            }
	            rs = null;
	        }

	        // Đóng PreparedStatement
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                System.err.println("Không thể đóng PreparedStatement: " + e.getMessage());
	            }
	            ps = null;
	        }

	        // Đóng Connection
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                System.err.println("Không thể đóng Connection: " + e.getMessage());
	            }
	            conn = null;
	        }
	    }

	    // Ngăn không cho tạo instance
	    private JDBCUtils() {}
	}


}
