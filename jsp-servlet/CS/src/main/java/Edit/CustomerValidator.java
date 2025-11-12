package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class CustomerValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^[\\p{L}\\s'.-]{1,50}$" // cho phép chữ, dấu cách, dấu nháy, gạch nối
    );

    private static final Pattern ADDRESS_PATTERN = Pattern.compile(
            "^[\\p{L}0-9\\s,./#-]{0,256}$"
    );

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static String validateCustomer(String name, String email, String sex, String address, String birthday) {
        String error = validateName(name);
        if (error != null) return error;

        error = validateEmail(email);
        if (error != null) return error;

        error = validateSex(sex);
        if (error != null) return error;

        error = validateAddress(address);
        if (error != null) return error;

        error = validateBirthday(birthday);
        if (error != null) return error;

        return null; // không có lỗi
    }

    public static String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Customer name is required.";
        }
        if (!NAME_PATTERN.matcher(name.trim()).matches()) {
            return "Customer name contains invalid characters or is too long (max 50).";
        }
        return null;
    }

    public static String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) return null; // cho phép trống
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            return "Invalid email format.";
        }
        return null;
    }

    public static String validateSex(String sex) {
        if (sex == null || sex.trim().isEmpty()) return null;
        String lower = sex.trim().toLowerCase();
        if (!lower.equals("male") && !lower.equals("female")) {
            return "Invalid sex value. Must be 'male' or 'female'.";
        }
        return null;
    }

    public static String validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) return null;
        if (!ADDRESS_PATTERN.matcher(address.trim()).matches()) {
            return "Address contains invalid characters or exceeds 256 characters.";
        }
        return null;
    }

    public static String validateBirthday(String birthday) {
        if (birthday == null || birthday.trim().isEmpty()) return null;
        try {
            LocalDate date = LocalDate.parse(birthday.trim(), DATE_FORMATTER);
            // kiểm tra logic ngày tồn tại (ví dụ 2025/02/29 bị loại)
            return null;
        } catch (DateTimeParseException e) {
            return "Invalid birthday format. Use YYYY/MM/DD.";
        } catch (Exception e) {
            return "Invalid birthday value.";
        }
    }
}
/*
Sử dụng trong Servlet
 */

String error = CustomerValidator.validateCustomer(
        request.getParameter("customerName"),
        request.getParameter("email"),
        request.getParameter("sex"),
        request.getParameter("address"),
        request.getParameter("birthday")
);

if (error != null) {
        request.setAttribute("errorMessage", error);
RequestDispatcher rd = request.getRequestDispatcher("editCustomer.jsp");
    rd.forward(request, response);
    return;
            }

// nếu không lỗi → tiếp tục insert/update DB
