package service;

import dao.UserDAO;
import model.User;
import java.sql.SQLException;

public class AuthService {
    private UserDAO userDAO = new UserDAO();

    public User authenticate(String userId, String password) {
        try {
            return userDAO.validateUser(userId, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
