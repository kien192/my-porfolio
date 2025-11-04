package dao;

public class UserDao {

	
	/**
     * Kiểm tra thông tin đăng nhập
     * @param user chứa userId và password
     * @return true nếu đăng nhập hợp lệ, false nếu sai
     */
    public boolean checkLogin(UserLoginDto user) {
        String sql = "SELECT COUNT(*) AS CNT "
                   + "FROM USER_TBL "
                   + "WHERE USERID = ? "
                   + "AND PASSWORD = ? "
                   + "AND EXPIRE_YMD IS NULL";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("CNT");
                    return count == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Có thể log hoặc throw lên tầng controller tuỳ cách bạn xử lý
        }
        return false;
    }
	
	
	
}
