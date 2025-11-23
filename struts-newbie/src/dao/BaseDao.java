package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBUtil;

public abstract class BaseDao {

    public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.connect();
            pstmt = conn.prepareStatement(sql);
            setParam(pstmt, params);
            rs = pstmt.executeQuery();

            return rs;

        } catch (SQLException e) {
            DBUtil.quit(conn, pstmt, rs);
            throw e;
        }
    }

    public static int executeUpdate(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.connect();
            pstmt = conn.prepareStatement(sql);
            setParam(pstmt, params);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.quit(conn, pstmt, null);
        }
    }

    public static Object executeScalar(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.connect();
            pstmt = conn.prepareStatement(sql);
            setParam(pstmt, params);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
            return null;
        } finally {
            DBUtil.quit(conn, pstmt, rs);
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
