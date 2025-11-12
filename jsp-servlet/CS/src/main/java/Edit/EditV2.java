public CustomerEditRequestDto getCustomerById(String customerId) {
    String sql = "SELECT customerName, sex, birthday, email, address, pcnUser " +
            "FROM Customer WHERE customerId = ?";
    List<CustomerEditRequestDto> list = executeQuery(sql, customerId); // giả sử executeQuery trả List
    return list.isEmpty() ? null : list.get(0);
}


private boolean isChanged(CustomerEditRequestDto oldDto, CustomerEditRequestDto newDto) {
    if (!equals(oldDto.getCustomerName(), newDto.getCustomerName())) return true;
    if (!equals(oldDto.getSex(), newDto.getSex())) return true;
    if (!equals(oldDto.getBirthday(), newDto.getBirthday())) return true;
    if (!equals(oldDto.getEmail(), newDto.getEmail())) return true;
    if (!equals(oldDto.getAddress(), newDto.getAddress())) return true;
    if (!equals(oldDto.getPcnUser(), newDto.getPcnUser())) return true;
    return false;
}

private boolean equals(String a, String b) {
    if (a == null && b == null) return true;
    if (a == null || b == null) return false;
    return a.equals(b);
}


public int updateCustomer(CustomerEditRequestDto newDto) {
    CustomerEditRequestDto oldDto = getCustomerById(newDto.getCustomerId());
    if (oldDto == null) {
        System.err.println("Customer not found: " + newDto.getCustomerId());
        return 0;
    }

    if (!isChanged(oldDto, newDto)) {
        System.out.println("No changes detected. Skip update.");
        return 0;
    }

    StringBuilder sql = new StringBuilder("UPDATE Customer SET ");
    List<Object> params = new ArrayList<>();

    if (!equals(oldDto.getCustomerName(), newDto.getCustomerName())) {
        sql.append("customerName = ?, ");
        params.add(newDto.getCustomerName());
    }
    if (!equals(oldDto.getSex(), newDto.getSex())) {
        sql.append("sex = ?, ");
        params.add(newDto.getSex());
    }
    if (!equals(oldDto.getBirthday(), newDto.getBirthday())) {
        sql.append("birthday = ?, ");
        params.add(newDto.getBirthday());
    }
    if (!equals(oldDto.getEmail(), newDto.getEmail())) {
        sql.append("email = ?, ");
        params.add(newDto.getEmail());
    }
    if (!equals(oldDto.getAddress(), newDto.getAddress())) {
        sql.append("address = ?, ");
        params.add(newDto.getAddress());
    }
    if (!equals(oldDto.getPcnUser(), newDto.getPcnUser())) {
        sql.append("pcnUser = ?, ");
        params.add(newDto.getPcnUser());
    }

    // cắt dấu phẩy cuối
    sql.setLength(sql.length() - 2);
    sql.append(" WHERE customerId = ?");
    params.add(newDto.getCustomerId());

    return executeUpdate(sql.toString(), params.toArray());
}
