@WebServlet("/CustomerSearchServlet")
public class CustomerSearchServlet extends HttpServlet {
    private CustomerDao dao = new CustomerDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("customerName");
        String sex = request.getParameter("sex");
        String from = request.getParameter("birthdayFrom");
        String to = request.getParameter("birthdayTo");
        String page = request.getParameter("page");

        // Lưu lại vào session để breadcrumb quay lại dùng
        HttpSession session = request.getSession();
        session.setAttribute("searchName", name);
        session.setAttribute("searchSex", sex);
        session.setAttribute("birthdayFrom", from);
        session.setAttribute("birthdayTo", to);
        session.setAttribute("pageNum", page);

        java.util.List<Customer> list = dao.search(name, sex, from, to, page);
        request.setAttribute("customerList", list);
        request.setAttribute("currentPage", "search");
        request.getRequestDispatcher("/customerSearch.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("Delete".equals(action)) {
            String[] ids = request.getParameterValues("deleteIds");
            if (ids != null) {
                for (String id : ids) {
                    dao.deleteById(id);
                }
            }

            // Giữ lại param khi redirect
            String name = request.getParameter("customerName");
            String sex = request.getParameter("sex");
            String from = request.getParameter("birthdayFrom");
            String to = request.getParameter("birthdayTo");
            String page = request.getParameter("page");

            response.sendRedirect("CustomerSearchServlet?customerName=" +
                    URLEncoder.encode(name != null ? name : "", "UTF-8") +
                    "&sex=" + URLEncoder.encode(sex != null ? sex : "", "UTF-8") +
                    "&birthdayFrom=" + URLEncoder.encode(from != null ? from : "", "UTF-8") +
                    "&birthdayTo=" + URLEncoder.encode(to != null ? to : "", "UTF-8") +
                    "&page=" + (page != null ? page : "1"));
        }
    }
}
