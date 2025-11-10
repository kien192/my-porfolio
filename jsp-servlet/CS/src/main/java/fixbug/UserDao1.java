package dao;

import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO {

    public User validateUser(String userId, String password) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append("    USER_ID, ");
        sql.append("    USER_NAME, ");
        sql.append("    PASSWORD ");
        sql.append("FROM ");
        sql.append("    M_USER ");
        sql.append("WHERE 1 = 1 ");
        sql.append("  AND DELETE_YMD IS NULL ");
        sql.append("  AND USER_ID = ? ");
        sql.append("  AND PASSWORD = ? ");

        try (ResultSet rs = executeQuery(sql.toString(), userId, password)) {
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("USER_ID"));
                user.setUserName(rs.getString("USER_NAME"));
                user.setPassword(rs.getString("PASSWORD"));
                return user;
            }
        }
        return null;
    }

    // UserDao.java
    public User validateUser1(String userId, String password) {
        String sql = "SELECT user_id, username FROM users WHERE user_id = ? AND password = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1️⃣ Mở kết nối
            conn = JDBCUtils.connect();

            // 2️⃣ Chuẩn bị statement và truyền param
            pstmt = conn.prepareStatement(sql);
            CommonDAO.setParam(pstmt, userId, password);

            // 3️⃣ Thực thi SELECT — ResultSet phụ thuộc vào conn
            rs = pstmt.executeQuery();

            // 4️⃣ Đọc dữ liệu (ResultSet còn "sống" vì Connection chưa đóng)
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5️⃣ Đóng tất cả sau khi đọc xong
            JDBCUtils.quit(conn, pstmt, rs);
        }

        return null;
    }

}
