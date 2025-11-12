public class CustomerDao extends BaseDao {

    public int insertCustomer(CustomerEditRequestDto dto) {
        StringBuilder sql = new StringBuilder("INSERT INTO Customer (");
        StringBuilder placeholders = new StringBuilder("VALUES (");

        List<Object> params = new ArrayList<>();

        // Tùy chọn thêm cột nếu có dữ liệu
        if (isNotEmpty(dto.getCustomerName())) {
            sql.append("customerName, ");
            placeholders.append("?, ");
            params.add(dto.getCustomerName().trim());
        }
        if (isNotEmpty(dto.getSex())) {
            sql.append("sex, ");
            placeholders.append("?, ");
            params.add(dto.getSex().trim());
        }
        if (isNotEmpty(dto.getBirthday())) {
            sql.append("birthday, ");
            placeholders.append("?, ");
            params.add(dto.getBirthday().trim());
        }
        if (isNotEmpty(dto.getEmail())) {
            sql.append("email, ");
            placeholders.append("?, ");
            params.add(dto.getEmail().trim());
        }
        if (isNotEmpty(dto.getAddress())) {
            sql.append("address, ");
            placeholders.append("?, ");
            params.add(dto.getAddress().trim());
        }
        if (isNotEmpty(dto.getPcnUser())) {
            sql.append("pcnUser, ");
            placeholders.append("?, ");
            params.add(dto.getPcnUser().trim());
        }

        // Xóa dấu phẩy cuối
        if (params.isEmpty()) {
            System.err.println("⚠️ Không có dữ liệu nào để insert.");
            return 0;
        }

        sql.setLength(sql.length() - 2);
        placeholders.setLength(placeholders.length() - 2);

        sql.append(") ").append(placeholders).append(")");

        return executeUpdate(sql.toString(), params.toArray());
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
