package enhance;

package dao;

import model.db.BaseDAO;
import model.dto.UserLoginDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserDAO kế thừa BaseDAO để sử dụng các tiện ích có sẵn.
 */
public class UserDAO extends BaseDAO {

    /**
     * Kiểm tra đăng nhập người dùng.
     */
    public boolean validateLogin(UserLoginDto dto) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isValid = false;

        try {
            conn = getConnection(); // lấy kết nối từ BaseDAO
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getUsername());
            ps.setString(2, dto.getPassword());
            rs = ps.executeQuery();

            if (rs.next()) {
                isValid = true;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi validateLogin: " + e.getMessage());
        } finally {
            closeAll(rs, ps, conn); // dùng hàm có sẵn trong BaseDAO
        }

        return isValid;
    }

    /**
     * Ví dụ sử dụng executeUpdate từ BaseDAO.
     */
    public int registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        return executeUpdate(sql, username, password); // ngắn gọn hơn nhiều
    }
}

