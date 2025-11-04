package controller;

import jakarta.servlet.http.HttpServlet;

public class UserController extends HttpServlete {

	
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        req.setCharacterEncoding("UTF-8");
	        String userId = req.getParameter("userId");
	        String password = req.getParameter("password");

	        UserLoginDto dto = new UserLoginDto(userId, password);
	        UserDao dao = new UserDao();

	        boolean isValid = dao.checkLogin(dto);

	        if (isValid) {
	            // Lưu session, chuyển trang
	            HttpSession session = req.getSession();
	            session.setAttribute("userId", userId);
	            resp.sendRedirect("home.jsp");
	        } else {
	            // Quay lại login.jsp với thông báo lỗi
	            req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
	            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
	        }
	    }
}
