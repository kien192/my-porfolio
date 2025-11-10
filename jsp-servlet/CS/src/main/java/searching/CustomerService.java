public class CustomerService {
    private final CustomerDao customerDao = new CustomerDao();

    public List<Customer> searchCustomer(String name, String sex, String from, String to, int page, int size) {
        // Business rule example: đảm bảo page và size hợp lệ
        if (page < 1) page = 1;
        if (size < 1 || size > 100) size = 10;

        // Có thể thêm logic kiểm tra ngày tháng
        if (from != null && to != null && from.compareTo(to) > 0) {
            throw new IllegalArgumentException("Ngày bắt đầu không được lớn hơn ngày kết thúc");
        }

        // Gọi DAO
        return customerDao.searchCustomer(name, sex, from, to, page, size);
    }

    public void addCustomer(Customer c) {
        // Kiểm tra dữ liệu đầu vào
        if (c.getCustomerName() == null || c.getCustomerName().isEmpty()) {
            throw new IllegalArgumentException("Tên khách hàng không được để trống");
        }

        customerDao.insertCustomer(c);
    }

    public class CustomerService {
        private final CustomerDao customerDao = new CustomerDao();

        public PaginationResult<Customer> searchCustomer(
                String name, String sex, String from, String to, int page, int size
        ) {
            if (page < 1) page = 1;
            if (size < 1) size = 15;

            int totalRecords = customerDao.countCustomer(name, sex, from, to);
            int totalPages = (int) Math.ceil((double) totalRecords / size);
            if (page > totalPages && totalPages > 0) page = totalPages;

            List<Customer> list = customerDao.searchCustomer(name, sex, from, to, page, size);

            return new PaginationResult<>(list, page, totalPages, totalRecords);
        }
    }

}
