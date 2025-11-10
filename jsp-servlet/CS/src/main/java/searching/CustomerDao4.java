public class CustomerDao extends BaseDao {

    public List<Customer> searchCustomer(String name, String sex, String from, String to, int page, int size) {
        List<Customer> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Customer WHERE 1=1");

        List<Object> params = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            sql.append(" AND CustomerName LIKE ?");
            params.add("%" + name + "%");
        }
        if (sex != null && !sex.isEmpty()) {
            sql.append(" AND Sex = ?");
            params.add(Integer.parseInt(sex));
        }
        if (from != null && !from.isEmpty()) {
            sql.append(" AND Birthday >= ?");
            params.add(Date.valueOf(from.replace("/", "-")));
        }
        if (to != null && !to.isEmpty()) {
            sql.append(" AND Birthday <= ?");
            params.add(Date.valueOf(to.replace("/", "-")));
        }

        // Pagination
        sql.append(" ORDER BY CustomerId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        params.add((page - 1) * size);
        params.add(size);

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtils.connect();
            pstmt = conn.prepareStatement(sql.toString());
            BaseDao.setParam(pstmt, params.toArray());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt("CustomerId"));
                c.setCustomerName(rs.getString("CustomerName"));
                c.setSex(rs.getInt("Sex"));
                c.setBirthday(rs.getDate("Birthday"));
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.quit(conn, pstmt, rs); // ✅ Đóng ở đây sau khi đọc xong
        }
        return list;
    }

    public int countCustomer(String name, String sex, String from, String to) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Customer WHERE 1=1");

        List<Object> params = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            sql.append(" AND CustomerName LIKE ?");
            params.add("%" + name + "%");
        }
        if (sex != null && !sex.isEmpty()) {
            sql.append(" AND Sex = ?");
            params.add(Integer.parseInt(sex));
        }
        if (from != null && !from.isEmpty()) {
            sql.append(" AND Birthday >= ?");
            params.add(Date.valueOf(from.replace("/", "-")));
        }
        if (to != null && !to.isEmpty()) {
            sql.append(" AND Birthday <= ?");
            params.add(Date.valueOf(to.replace("/", "-")));
        }

        Object result = BaseDao.executeScalar(sql.toString(), params.toArray());
        return result != null ? ((Number) result).intValue() : 0;
    }

}
