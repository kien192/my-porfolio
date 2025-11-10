@WebServlet("/customer/search")
public class CustomerSearchServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String from = req.getParameter("from");
        String to = req.getParameter("to");

        int page = 1;
        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        PaginationResult<Customer> result =
                customerService.searchCustomer(name, sex, from, to, page, 15);

        req.setAttribute("pagination", result);
        req.setAttribute("customers", result.getData());
        req.getRequestDispatcher("/WEB-INF/views/customer-list.jsp").forward(req, resp);
    }
}
