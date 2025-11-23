package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBUtil;
import dto.CustomerDto;

public class CustomerDao extends BaseDao {

    public List<CustomerDto> searchCustomer(CustomerDto dto) {
        List<CustomerDto> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT CUSTOMERID, CUSTOMERNAME, BIRTHDAY, SEX, ADDRESS FROM MSTCUSTOMER WHERE DELETE_YMD IS NULL ");

        List<Object> params = new ArrayList<>();

        if (dto.getCustomerName() != null && !dto.getCustomerName().isEmpty()) {
            sql.append("AND CUSTOMERNAME LIKE ? ");
            params.add("%" + dto.getCustomerName() + "%");
        }
        if (dto.getSex() != null && !dto.getSex().isEmpty()) {
            sql.append("AND SEX = ? ");
            params.add(dto.getSex());
        }
        if (dto.getBirthdayFrom() != null && !dto.getBirthdayFrom().isEmpty()) {
            sql.append("AND BIRTHDAY >= ? ");
            params.add(Date.valueOf(dto.getBirthdayFrom().replace("/", "-")));
        }
        if (dto.getBirthdayTo() != null && !dto.getBirthdayTo().isEmpty()) {
            sql.append("AND BIRTHDAY <= ? ");
            params.add(Date.valueOf(dto.getBirthdayTo().replace("/", "-")));
        }

        // Pagination
        sql.append("ORDER BY CUSTOMERID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        params.add((dto.getCurrentPage() - 1) * dto.getSizePerPage());
        params.add(dto.getSizePerPage());

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.connect();
            pstmt = conn.prepareStatement(sql.toString());
            BaseDao.setParam(pstmt, params.toArray());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CustomerDto c = new CustomerDto();
                c.setCustomerId(rs.getInt("CUSTOMERID"));
                c.setCustomerName(rs.getString("CUSTOMERNAME"));
                c.setSex(rs.getString("SEX"));
                c.setBirthday(rs.getString("BIRTHDAY"));
                c.setAddress(rs.getString("ADDRESS"));
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.quit(conn, pstmt, rs); // ✅ Đóng ở đây sau khi đọc xong
        }
        return list;
    }

    public int countCustomer(CustomerDto dto) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM MSTCUSTOMER WHERE DELETE_YMD IS NULL ");

        List<Object> params = new ArrayList<>();
        if (dto.getCustomerName() != null && !dto.getCustomerName().isEmpty()) {
            sql.append("AND CustomerName LIKE ? ");
            params.add("%" + dto.getCustomerName() + "%");
        }
        if (dto.getSex() != null && !dto.getSex().isEmpty()) {
            sql.append("AND Sex = ? ");
            params.add(dto.getSex());
        }
        if (dto.getBirthdayFrom() != null && !dto.getBirthdayFrom().isEmpty()) {
            sql.append("AND Birthday >= ? ");
            params.add(Date.valueOf(dto.getBirthdayFrom().replace("/", "-")));
        }
        if (dto.getBirthdayTo() != null && !dto.getBirthdayTo().isEmpty()) {
            sql.append("AND Birthday <= ? ");
            params.add(Date.valueOf(dto.getBirthdayFrom().replace("/", "-")));
        }

        try {
            Object result = executeScalar(sql.toString(), params.toArray());
            return result != null ? ((Number) result).intValue() : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
