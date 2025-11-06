package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import dao.CustomerDAO;
import model.Customer;

public class SearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        // Kiểm tra login session
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");

        int page = 1;
        int limit = 15;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int offset = (page - 1) * limit;

        CustomerDAO dao = new CustomerDAO();
        List<Customer> list = dao.search(name, sex, fromDate, toDate, offset, limit);
        int total = dao.countAll(name, sex, fromDate, toDate);

        int totalPage = (int) Math.ceil((double) total / limit);

        request.setAttribute("customers", list);
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("name", name);
        request.setAttribute("sex", sex);
        request.setAttribute("fromDate", fromDate);
        request.setAttribute("toDate", toDate);

        RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
        rd.forward(request, response);
    }
}
