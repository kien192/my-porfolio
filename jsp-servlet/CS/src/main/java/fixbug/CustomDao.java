package dao;

import java.sql.*;

public class CommonDAO {

    public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.connect();
            pstmt = conn.prepareStatement(sql);
            setParam(pstmt, params);
            rs = pstmt.executeQuery();

            // ⚠️ Không đóng conn ở đây, vì ResultSet còn đang được sử dụng.
            // Gọi quit() sau khi đọc xong kết quả ở DAO cụ thể.
            return rs;

        } catch (SQLException e) {
            JDBCUtils.quit(conn, pstmt, rs);
            throw e;
        }
    }

    public static int executeUpdate(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.connect();
            pstmt = conn.prepareStatement(sql);
            setParam(pstmt, params);
            return pstmt.executeUpdate();
        } finally {
            JDBCUtils.quit(conn, pstmt, null);
        }
    }

    public static Object executeScalar(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.connect();
            pstmt = conn.prepareStatement(sql);
            setParam(pstmt, params);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
            return null;
        } finally {
            JDBCUtils.quit(conn, pstmt, rs);
        }
    }

    public static void setParam(PreparedStatement pstmt, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
    }
}
