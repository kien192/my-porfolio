package dao;

import java.sql.*;
import model.User;

public class UserDao {

    /**
     * Validate user credentials by checking if the userId and password exist in database.
     *
     * @param userId   the user's ID
     * @param password the user's password
     * @return User object if valid, otherwise null
     */
    public User validateUser(String userId, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    user_id, ");
        sql.append("    username, ");
        sql.append("    role ");
        sql.append("FROM users ");
        sql.append("WHERE user_id = ? ");
        sql.append("  AND password = ?");

        try (ResultSet rs = CommonDAO.executeQuery(sql.toString(), userId, password)) {
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Example of inserting a new user.
     */
    public int insertUser(User user) {
        String sql = "INSERT INTO users (user_id, username, password, role) VALUES (?, ?, ?, ?)";
        try {
            return CommonDAO.executeUpdate(sql,
                    user.getUserId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getRole());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Example of getting total user count.
     */
    public int getUserCount() {
        String sql = "SELECT COUNT(*) FROM users";
        try {
            Object result = CommonDAO.executeScalar(sql);
            return result != null ? ((Number) result).intValue() : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
