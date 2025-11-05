package enhance;

package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BaseDAO
 *
 * Lớp cha cho tất cả DAO trong ứng dụng.
 * Cung cấp:
 * - Hàm mở kết nối (getConnection)
 * - Hàm đóng kết nối (closeAll)
 * - Hàm tiện ích executeQuery, executeUpdate cơ bản
 *
 * Mục tiêu: giảm lặp code JDBC thuần trong mỗi DAO con.
 */
public abstract class BaseDAO {

    /**
     * Lấy Connection từ DBConnector.
     *
     * @return Connection tới database
     * @throws SQLException nếu không thể kết nối
     */
    protected Connection getConnection() throws SQLException {
        return DBConnector.getConnection();
    }

    /**
     * Đóng tất cả tài nguyên JDBC an toàn.
     *
     * @param rs  có thể null
     * @param ps  có thể null
     * @param conn có thể null
     */
    protected void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
        JDBCUtils.closeQuietly(rs, ps, conn);
    }

    /**
     * Thực thi câu lệnh UPDATE/INSERT/DELETE đơn giản.
     * (Dành cho câu lệnh không cần ResultSet)
     *
     * @param sql  Câu lệnh SQL có thể chứa dấu ?
     * @param params Mảng tham số truyền vào câu lệnh (nếu có)
     * @return số dòng bị ảnh hưởng
     */
    protected int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        int affected = 0;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            // Gán tham số vào statement (nếu có)
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }

            affected = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi executeUpdate: " + e.getMessage());
        } finally {
            closeAll(null, ps, conn);
        }

        return affected;
    }

    /**
     * Hàm mẫu để thực thi SELECT và trả về ResultSet.
     * (Caller có trách nhiệm đọc dữ liệu & đóng tài nguyên)
     *
     * ⚠ Lưu ý:
     * - Dành cho trường hợp bạn cần đọc dữ liệu thủ công.
     * - Sau khi xử lý xong, phải gọi closeAll(rs, ps, conn).
     */
    protected ResultSet executeQuery(Connection conn, PreparedStatement ps, String sql, Object... params) throws SQLException {
        ps = conn.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        return ps.executeQuery();
    }
    