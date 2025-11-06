package dao;

import java.sql.*;
import java.util.*;
import model.Customer;
import model.db.DBConnector;

public class CustomerDao {

    public List<Customer> searchCustomer(String name, String sex, String from, String to, int page, int size) {
        List<Customer> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT * FROM Customer WHERE 1=1"
        );

        if (name != null && !name.isEmpty()) sql.append(" AND CustomerName LIKE ?");
        if (sex != null && !sex.isEmpty()) sql.append(" AND Sex = ?");
        if (from != null && !from.isEmpty()) sql.append(" AND Birthday >= ?");
        if (to != null && !to.isEmpty()) sql.append(" AND Birthday <= ?");

        // Pagination SQL Server (OFFSET/FETCH)
        sql.append(" ORDER BY CustomerId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            if (name != null && !name.isEmpty()) ps.setString(idx++, "%" + name + "%");
            if (sex != null && !sex.isEmpty()) ps.setInt(idx++, Integer.parseInt(sex));
            if (from != null && !from.isEmpty()) ps.setDate(idx++, Date.valueOf(from.replace("/", "-")));
            if (to != null && !to.isEmpty()) ps.setDate(idx++, Date.valueOf(to.replace("/", "-")));

            // OFFSET = (page - 1) * size
            ps.setInt(idx++, (page - 1) * size);
            ps.setInt(idx++, size);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt("CustomerId"));
                c.setCustomerName(rs.getString("CustomerName"));
                c.setSex(rs.getInt("Sex"));
                c.setBirthday(rs.getDate("Birthday"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countCustomer(String name, String sex, String from, String to) {
        int total = 0;
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Customer WHERE 1=1");

        if (name != null && !name.isEmpty()) sql.append(" AND CustomerName LIKE ?");
        if (sex != null && !sex.isEmpty()) sql.append(" AND Sex = ?");
        if (from != null && !from.isEmpty()) sql.append(" AND Birthday >= ?");
        if (to != null && !to.isEmpty()) sql.append(" AND Birthday <= ?");

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            if (name != null && !name.isEmpty()) ps.setString(idx++, "%" + name + "%");
            if (sex != null && !sex.isEmpty()) ps.setInt(idx++, Integer.parseInt(sex));
            if (from != null && !from.isEmpty()) ps.setDate(idx++, Date.valueOf(from.replace("/", "-")));
            if (to != null && !to.isEmpty()) ps.setDate(idx++, Date.valueOf(to.replace("/", "-")));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}

