package dao;

import java.sql.*;
import java.util.*;
import model.Customer;

public class CustomerDAO {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=YourDB";
        String user = "sa";
        String pass = "yourpassword";
        return DriverManager.getConnection(url, user, pass);
    }

    public List<Customer> search(String name, String sex, String fromDate, String toDate, int offset, int limit) {
        List<Customer> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT customer_id, customer_name, sex, birthday " +
            "FROM customer WHERE 1=1 "
        );

        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND customer_name LIKE ? ");
        }
        if (sex != null && !sex.isEmpty()) {
            sql.append(" AND sex = ? ");
        }
        if (fromDate != null && !fromDate.isEmpty()) {
            sql.append(" AND birthday >= ? ");
        }
        if (toDate != null && !toDate.isEmpty()) {
            sql.append(" AND birthday <= ? ");
        }

        sql.append(" ORDER BY customer_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ");

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            if (name != null && !name.trim().isEmpty()) ps.setString(idx++, "%" + name + "%");
            if (sex != null && !sex.isEmpty()) ps.setInt(idx++, Integer.parseInt(sex));
            if (fromDate != null && !fromDate.isEmpty()) ps.setDate(idx++, Date.valueOf(fromDate));
            if (toDate != null && !toDate.isEmpty()) ps.setDate(idx++, Date.valueOf(toDate));
            ps.setInt(idx++, offset);
            ps.setInt(idx++, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getString("customer_id"));
                c.setCustomerName(rs.getString("customer_name"));
                c.setSex(rs.getInt("sex"));
                c.setBirthday(rs.getDate("birthday"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countAll(String name, String sex, String fromDate, String toDate) {
        int total = 0;
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM customer WHERE 1=1 ");
        if (name != null && !name.trim().isEmpty()) sql.append(" AND customer_name LIKE ? ");
        if (sex != null && !sex.isEmpty()) sql.append(" AND sex = ? ");
        if (fromDate != null && !fromDate.isEmpty()) sql.append(" AND birthday >= ? ");
        if (toDate != null && !toDate.isEmpty()) sql.append(" AND birthday <= ? ");

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            if (name != null && !name.trim().isEmpty()) ps.setString(idx++, "%" + name + "%");
            if (sex != null && !sex.isEmpty()) ps.setInt(idx++, Integer.parseInt(sex));
            if (fromDate != null && !fromDate.isEmpty()) ps.setDate(idx++, Date.valueOf(fromDate));
            if (toDate != null && !toDate.isEmpty()) ps.setDate(idx++, Date.valueOf(toDate));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
