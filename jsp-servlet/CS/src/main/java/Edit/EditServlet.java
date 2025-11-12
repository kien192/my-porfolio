package controller;

import java.io.IOException;
import java.util.regex.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Customer;
import dao.CustomerDAO;

public class CustomerEditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("customerName");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String birthday = request.getParameter("birthday"); // bạn đã có validate JS

        // ======================
        // 1️⃣ Validate phía server
        // ======================

        String error = null;

        // a. Customer Name
        if (name == null || name.trim().isEmpty()) {
            error = "Customer Name is required.";
        } else if (!name.matches("^[A-Za-zÀ-ỹ\\s\\-']{2,50}$")) {
            error = "Customer Name is invalid.";
        }

        // b. Email (optional)
        if (error == null && email != null && !email.trim().isEmpty()) {
            if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                error = "Invalid email format.";
            }
        }

        // c. Address (optional)
        if (error == null && address != null && !address.trim().isEmpty()) {
            if (!address.matches("^[A-Za-z0-9À-ỹ\\s,./\\\\-]{0,256}$")) {
                error = "Address contains invalid characters.";
            }
        }

        // d. Sex không cần check, vì lấy từ select → an toàn
        // (nếu cần chuẩn hóa null)
        if (sex == null || sex.trim().isEmpty()) sex = null;

        // ======================
        // 2️⃣ Xử lý kết quả
        // ======================
        if (error != null) {
            // Gửi lỗi lại cho trang Edit.jsp
            request.setAttribute("errorMsg", error);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/customer/Edit.jsp");
            rd.forward(request, response);
            return;
        }

        // Nếu tới đây: dữ liệu hợp lệ → tiếp tục save DB
        Customer customer = new Customer();
        customer.setName(name.trim());
        customer.setEmail(email != null ? email.trim() : null);
        customer.setSex(sex);
        customer.setAddress(address != null ? address.trim() : null);
        customer.setBirthday(birthday);

        CustomerDAO dao = new CustomerDAO();
        dao.saveOrUpdate(customer);

        // Quay lại trang Search hoặc thông báo success
        response.sendRedirect("searchCustomer");
    }
}C