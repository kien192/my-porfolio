package action;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import dao.CustomerDao;
import model.Customer;

public class SearchServlet extends HttpServlet {
    private static final int PAGE_SIZE = 15;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String name = request.getParameter("customerName");
        String sex = request.getParameter("sex");
        String fromDate = request.getParameter("birthdayFrom");
        String toDate = request.getParameter("birthdayTo");

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            // mặc định trang 1
        }

        CustomerDao dao = new CustomerDao();
        List<Customer> list = dao.searchCustomer(name, sex, fromDate, toDate, page, PAGE_SIZE);
        int totalCount = dao.countCustomer(name, sex, fromDate, toDate);

        int totalPage = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        request.setAttribute("customers", list);
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("name", name);
        request.setAttribute("sex", sex);
        request.setAttribute("fromDate", fromDate);
        request.setAttribute("toDate", toDate);

        RequestDispatcher rd = request.getRequestDispatcher("SearchResult.jsp");
        rd.forward(request, response);
    }
}

