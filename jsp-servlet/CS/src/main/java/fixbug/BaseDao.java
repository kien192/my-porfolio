package dao;

import util.DBConnection;
import java.sql.*;

/**
 * BaseDAO là lớp cơ sở dùng chung cho các lớp DAO khác.
 * <p>
 * Cung cấp các phương thức tiện ích để thực thi câu lệnh SQL
 * bao gồm SELECT, INSERT, UPDATE, DELETE và các truy vấn scalar.
 * <p>
 * Tất cả các phương thức đều sử dụng {@link PreparedStatement}
 * để tránh lỗi SQL Injection và hỗ trợ truyền tham số động.
 *
 * @author Kien
 * @since 1.0
 */
public class BaseDAO {

    /**
     * Thực thi câu lệnh SELECT và trả về đối tượng {@link ResultSet}.
     * <p>
     * Caller phải tự đóng ResultSet sau khi đọc dữ liệu để giải phóng kết nối.
     *
     * @param sql    Câu lệnh SQL có thể chứa dấu "?" làm placeholder cho tham số.
     * @param params Danh sách tham số truyền vào tương ứng với thứ tự dấu "?".
     * @return ResultSet chứa kết quả truy vấn.
     * @throws SQLException Nếu có lỗi khi kết nối hoặc thực thi SQL.
     *
     * @example
     * <pre>
     * try (ResultSet rs = executeQuery("SELECT * FROM M_USER WHERE USER_ID = ?", "A001")) {
     *     if (rs.next()) {
     *         System.out.println(rs.getString("USER_NAME"));
     *     }
     * }
     * </pre>
     */

    /**
     * Executes a SQL SELECT statement and returns the result as a ResultSet.
     *
     * <p>This method is used when you need to retrieve multiple rows from the database,
     * such as user lists, search results, etc.</p>
     *
     * @param query  The SQL SELECT query to execute.
     * @param params The parameters to be bound to the prepared statement (if any).
     * @return A ResultSet containing the query results.
     * @throws SQLException If a database access error occurs or the SQL statement is invalid.
     *
     * <p><b>Example:</b></p>
     * <pre>
     * ResultSet rs = CommonDAO.executeQuery("SELECT * FROM users WHERE role = ?", "admin");
     * </pre>
     */
    protected ResultSet executeQuery(String sql, Object... params) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        setParams(ps, params);
        return ps.executeQuery();
    }

    /**
     * Thực thi câu lệnh INSERT, UPDATE hoặc DELETE.
     * <p>
     * Phương thức này tự động đóng kết nối sau khi hoàn thành.
     *
     * @param sql    Câu lệnh SQL cần thực thi.
     * @param params Tham số truyền vào tương ứng với các dấu "?" trong SQL.
     * @return Số dòng bị ảnh hưởng (thường > 0 nếu thành công).
     * @throws SQLException Nếu có lỗi khi kết nối hoặc thực thi SQL.
     *
     * @example
     * <pre>
     * int rows = executeUpdate("UPDATE M_USER SET PASSWORD = ? WHERE USER_ID = ?", "1234", "A001");
     * if (rows > 0) {
     *     System.out.println("Cập nhật thành công!");
     * }
     * </pre>
     */
    /**
     * Executes a SQL INSERT, UPDATE, or DELETE statement.
     *
     * <p>This method is used when you want to modify data in the database.</p>
     *
     * @param query  The SQL statement to execute (INSERT, UPDATE, or DELETE).
     * @param params The parameters to be bound to the prepared statement (if any).
     * @return The number of affected rows.
     * @throws SQLException If a database access error occurs or the SQL statement is invalid.
     *
     * <p><b>Example:</b></p>
     * <pre>
     * int rows = CommonDAO.executeUpdate("DELETE FROM users WHERE user_id = ?", "U001");
     * </pre>
     */

    protected int executeUpdate(String sql, Object... params) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, params);
            return ps.executeUpdate();
        }
    }

    /**
     * Thực thi truy vấn trả về duy nhất một giá trị (scalar),
     * ví dụ COUNT(*), MAX(), MIN(), SUM(), v.v...
     * <p>
     * Phương thức tự động đóng kết nối sau khi đọc giá trị đầu tiên.
     *
     * @param sql    Câu lệnh SQL cần thực thi.
     * @param params Tham số truyền vào tương ứng với dấu "?" trong SQL.
     * @return Giá trị đầu tiên trong kết quả truy vấn hoặc {@code null} nếu không có dữ liệu.
     * @throws SQLException Nếu có lỗi khi kết nối hoặc thực thi SQL.
     *
     * @example
     * <pre>
     * Object result = executeScalar("SELECT COUNT(*) FROM M_USER WHERE DELETE_YMD IS NULL");
     * int count = result != null ? ((Number) result).intValue() : 0;
     * </pre>
     */
    /**
     * Executes a SQL SELECT query and returns the first column of the first row in the result set.
     *
     * <p>This method is commonly used when you expect a single value as a result,
     * such as a COUNT, MAX, or a specific column lookup.</p>
     *
     * @param query  The SQL SELECT query to execute.
     * @param params The parameters to be bound to the prepared statement (if any).
     * @return The first column of the first row, or null if no result is found.
     * @throws SQLException If a database access error occurs or the SQL statement is invalid.
     *
     * <p><b>Example:</b></p>
     * <pre>
     * int count = (int) CommonDAO.executeScalar("SELECT COUNT(*) FROM users");
     * </pre>
     */

    protected Object executeScalar(String sql, Object... params) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, params);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getObject(1);
        }
        return null;
    }

    /**
     * Gán giá trị cho từng tham số trong {@link PreparedStatement}.
     * <p>
     * Là phương thức nội bộ, không nên gọi trực tiếp từ bên ngoài DAO.
     *
     * @param ps     PreparedStatement cần gán giá trị.
     * @param params Danh sách giá trị cần gán (có thể rỗng).
     * @throws SQLException Nếu có lỗi khi set tham số.
     */
    /**
     * Binds parameters to a PreparedStatement.
     *
     * <p>This is an internal utility used by all query methods
     * to safely set parameters for SQL statements.</p>
     *
     * @param pstmt  The PreparedStatement object.
     * @param params The parameters to bind.
     * @throws SQLException If parameter binding fails.
     *
     * <p><b>Example:</b></p>
     * <pre>
     * PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE user_id = ?");
     * CommonDAO.setParam(pstmt, "U001");
     * </pre>
     */

    private void setParams(PreparedStatement ps, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
    }
}
