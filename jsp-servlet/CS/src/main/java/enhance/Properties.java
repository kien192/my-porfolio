package enhance;

package model.db;

import java.io.IOException;
import java.util.Properties;

/**
 * {@code DBProperties} là lớp chịu trách nhiệm đọc và quản lý
 * các thông tin cấu hình kết nối cơ sở dữ liệu từ file cấu hình {@code DB.properties}.
 * <p>
 * Lớp này sử dụng cơ chế nạp tĩnh (static block) để tải dữ liệu cấu hình
 * ngay khi lớp được khởi tạo lần đầu tiên trong quá trình chạy chương trình.
 * Nhờ vậy, các thông tin cấu hình chỉ cần đọc một lần duy nhất và có thể
 * được truy cập toàn cục thông qua các biến static.
 * </p>
 *
 * <p><b>Ví dụ file cấu hình:</b></p>
 * <pre>
 * db.host=127.0.0.1
 * db.port=3306
 * db.username=root
 * db.password=
 * db.name=handmadestore
 * </pre>
 *
 * <p><b>Ví dụ sử dụng:</b></p>
 * <pre>{@code
 * String url = "jdbc:mysql://" + DBProperties.host + ":" + DBProperties.port + "/" + DBProperties.name;
 * Connection conn = DriverManager.getConnection(url, DBProperties.username, DBProperties.password);
 * }</pre>
 *
 * @author Kiên
 * @version 1.0
 * @since 2025-11-06
 */
public class DBProperties {

    /** 
     * Đối tượng {@link Properties} lưu trữ toàn bộ thông tin cấu hình 
     * được đọc từ file {@code DB.properties}. 
     */
    private static final Properties properties = new Properties();

    // ---------------------------------------------------------------
    // Khối khởi tạo tĩnh (static initializer)
    // ---------------------------------------------------------------

    /**
     * Khối static được thực thi một lần khi lớp được nạp vào bộ nhớ.
     * <p>
     * Tác vụ chính:
     * <ul>
     *   <li>Tải file cấu hình {@code DB.properties} nằm trong thư mục {@code resources}.</li>
     *   <li>Nếu file không tồn tại hoặc không đọc được, ném ra {@link RuntimeException}.</li>
     * </ul>
     * </p>
     */
    static {
        try {
            properties.load(
                    DBProperties.class.getClassLoader().getResourceAsStream("DB.properties")
            );
        } catch (IOException e) {
            throw new RuntimeException("Không thể đọc file cấu hình DB.properties", e);
        }
    }

    // ---------------------------------------------------------------
    // Các thuộc tính cấu hình
    // ---------------------------------------------------------------

    /** Địa chỉ host của cơ sở dữ liệu, ví dụ: {@code 127.0.0.1}. */
    public static final String host = properties.getProperty("db.host");

    /** Cổng (port) kết nối đến cơ sở dữ liệu, ví dụ: {@code 3306}. */
    public static final String port = properties.getProperty("db.port");

    /** Tên người dùng (username) để đăng nhập vào cơ sở dữ liệu. */
    public static final String username = properties.getProperty("db.username");

    /** Mật khẩu (password) để đăng nhập vào cơ sở dữ liệu. */
    public static final String password = properties.getProperty("db.password");

    /** Tên cơ sở dữ liệu (database name) cần kết nối. */
    public static final String name = properties.getProperty("db.name");

    // ---------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------

    /**
     * Constructor trống nhằm ngăn không cho khởi tạo đối tượng {@code DBProperties}.
     * <p>
     * Tất cả thuộc tính và hành vi của lớp này đều là static,
     * do đó việc tạo instance là không cần thiết.
     * </p>
     */
    private DBProperties() {}
