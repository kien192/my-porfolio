public CustomerResponseDTO saveCustomer(String customerId, CustomerRequest cr) {
    CustomerResponseDTO result;

    try {
        // Trường hợp 1: Thêm mới customer
        if (customerId == null || customerId.trim().isEmpty()) {
            int newId = customerDao.addCustomer(cr);

            // newId = 0 hoặc -1 thì xem như thêm thất bại
            if (newId <= 0) {
                throw new RuntimeException("Failed to add new customer");
            }

            result = customerDao.getCustomer(newId);
        }
        else {
            // Trường hợp 2: Chỉnh sửa customer
            int updatedRows = customerDao.updateCustomer(customerId, cr);

            if (updatedRows == 0) {
                throw new RuntimeException("Customer not found or update failed");
            }

            result = customerDao.getCustomer(Integer.parseInt(customerId));
        }
    } catch (Exception ex) {
        throw new RuntimeException("Error while saving customer", ex);
    }

    return result;
}
