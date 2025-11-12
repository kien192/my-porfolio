/**
 * Service.
 */
public class CustomerService {

    private final CustomerDao customerDao = new CustomerDao();

    // Validate & phân loại insert/update
    public int saveCustomer(CustomerEditRequestDto dto) throws ValidationException {
        validate(dto);

        if (dto.getCustomerId() == null || dto.getCustomerId().trim().isEmpty()) {
            // Insert
            return customerDao.insertCustomer(dto);
        } else {
            // Edit
            CustomerEditRequestDto oldDto = customerDao.getCustomerById(dto.getCustomerId());
            if (oldDto == null) {
                throw new ValidationException("Customer not found");
            }

            // Dynamic update (chỉ cập nhật các field khác)
            Map<String, Object> updateMap = buildUpdateMap(oldDto, dto);
            if (updateMap.isEmpty()) {
                System.out.println("No changes detected. Skip update.");
                return 0;
            }

            return customerDao.updateDynamic("Customer", updateMap, "customerId", dto.getCustomerId());
        }
    }

    // Validate chung cho cả insert và edit
    private void validate(CustomerEditRequestDto dto) throws ValidationException {
        if (dto.getCustomerName() == null || dto.getCustomerName().trim().isEmpty()) {
            throw new ValidationException("Customer name is required");
        }
        // email, birthday, address, sex validate theo logic bạn đã xây dựng
    }

    // Build map cho dynamic update
    private Map<String, Object> buildUpdateMap(CustomerEditRequestDto oldDto, CustomerEditRequestDto newDto) {
        Map<String, Object> map = new LinkedHashMap<>();

        if (!equals(oldDto.getCustomerName(), newDto.getCustomerName())) {
            map.put("customerName", newDto.getCustomerName());
        }
        if (!equals(oldDto.getSex(), newDto.getSex())) {
            map.put("sex", newDto.getSex());
        }
        if (!equals(oldDto.getBirthday(), newDto.getBirthday())) {
            map.put("birthday", newDto.getBirthday());
        }
        if (!equals(oldDto.getEmail(), newDto.getEmail())) {
            map.put("email", newDto.getEmail());
        }
        if (!equals(oldDto.getAddress(), newDto.getAddress())) {
            map.put("address", newDto.getAddress());
        }
        if (!equals(oldDto.getPcnUser(), newDto.getPcnUser())) {
            map.put("pcnUser", newDto.getPcnUser());
        }
        return map;
    }

    private boolean equals(String a, String b) {
        return (a == null && b == null) || (a != null && a.equals(b));
    }
}

/**
 * Servlet.
 */

@WebServlet("/customer/save")
public class CustomerServlet extends HttpServlet {

    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        CustomerEditRequestDto dto = new CustomerEditRequestDto();
        dto.setCustomerId(request.getParameter("customerId"));
        dto.setCustomerName(request.getParameter("customerName"));
        dto.setSex(request.getParameter("sex"));
        dto.setBirthday(request.getParameter("birthday"));
        dto.setEmail(request.getParameter("email"));
        dto.setAddress(request.getParameter("address"));
        dto.setPcnUser((String) request.getSession().getAttribute("loggedUser"));

        try {
            int result = customerService.saveCustomer(dto);
            if (result > 0) {
                request.setAttribute("message", "Customer saved successfully");
            } else {
                request.setAttribute("message", "No changes made");
            }
        } catch (ValidationException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/views/customerEdit.jsp").forward(request, response);
    }
}
