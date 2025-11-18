@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    // 1. Lấy dữ liệu từ form
    String customerId = req.getParameter("customerId"); // có thể null (trường hợp Add)
    CustomerRequest dto = buildCustomerRequest(req);     // bạn tự implement

    // 2. Validate
    Map<String, String> errors = customerValidator.validate(dto);

    if (!errors.isEmpty()) {
        // Nếu lỗi → trả về trang form (không redirect)
        req.setAttribute("errors", errors);
        req.setAttribute("customer", dto);

        // forward lại màn hình T003.jsp
        req.getRequestDispatcher("/WEB-INF/views/T003.jsp").forward(req, resp);
        return; // dừng luôn để tránh chạy xuống dưới
    }

    try {
        // 3. Gọi service
        CustomerResponseDTO result = customerService.saveCustomer(customerId, dto);

        // 4. Redirect để tránh gửi lại form (Post/Redirect/Get)
        resp.sendRedirect(
                req.getContextPath() + "/T003?customerId=" + result.getCustomerId()
        );

    } catch (Exception ex) {
        // 5. Xử lý exception chung
        throw new ServletException("Error saving customer", ex);
    }
}
