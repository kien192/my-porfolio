package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import dao.UserDao;
import model.User;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        boolean valid = userDao.validateUser(userId, password);

        if (valid) {
            User user = userDao.getUserById(userId);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30 * 60); // 30 phút
            response.sendRedirect("search");
        } else {
            request.setAttribute("error", "Sai UserID hoặc Password!");
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        }
    }
}
