public static String validateBirthday(String birthDay) {
    if (birthDay == null || birthDay.trim().isEmpty()) {
        return "Birthday cannot be empty";
    }

    LocalDate date;
    try {
        date = LocalDate.parse(birthDay.trim(), DATE_FORMATTER);
    } catch (DateTimeParseException e) {
        return "Birthday must be in format yyyy/MM/dd";
    }

    // Không cho ngày ở tương lai
    if (date.isAfter(LocalDate.now())) {
        return "Birthday cannot be in the future";
    }

    // Không cho ngày quá cũ (như 1800)
    if (date.isBefore(LocalDate.of(1900, 1, 1))) {
        return "Birthday is too old";
    }

    // OK
    return null;
}
