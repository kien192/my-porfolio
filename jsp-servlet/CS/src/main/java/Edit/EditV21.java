public int updateCustomerDynamic(CustomerEditRequestDto newDto) {
    // 1. Lấy dữ liệu hiện tại
    CustomerEditRequestDto oldDto = getCustomerById(newDto.getCustomerId());
    if (oldDto == null) {
        System.err.println("Customer not found: " + newDto.getCustomerId());
        return 0;
    }

    // 2. Map lưu các cột cần update
    Map<String, Object> updateMap = new LinkedHashMap<>();

    if (!equals(oldDto.getCustomerName(), newDto.getCustomerName())) {
        updateMap.put("customerName", newDto.getCustomerName());
    }
    if (!equals(oldDto.getSex(), newDto.getSex())) {
        updateMap.put("sex", newDto.getSex());
    }
    if (!equals(oldDto.getBirthday(), newDto.getBirthday())) {
        updateMap.put("birthday", newDto.getBirthday());
    }
    if (!equals(oldDto.getEmail(), newDto.getEmail())) {
        updateMap.put("email", newDto.getEmail());
    }
    if (!equals(oldDto.getAddress(), newDto.getAddress())) {
        updateMap.put("address", newDto.getAddress());
    }
    if (!equals(oldDto.getPcnUser(), newDto.getPcnUser())) {
        updateMap.put("pcnUser", newDto.getPcnUser());
    }

    if (updateMap.isEmpty()) {
        System.out.println("No changes detected. Skip update.");
        return 0;
    }

    // 3. Build SQL động
    StringBuilder sql = new StringBuilder("UPDATE Customer SET ");
    List<Object> params = new ArrayList<>();

    for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
        sql.append(entry.getKey()).append(" = ?, ");
        params.add(entry.getValue());
    }

    // xóa dấu phẩy cuối
    sql.setLength(sql.length() - 2);

    sql.append(" WHERE customerId = ?");
    params.add(newDto.getCustomerId());

    // 4. Thực thi
    return executeUpdate(sql.toString(), params.toArray());
}

// helper so sánh null-safe
private boolean equals(String a, String b) {
    if (a == null && b == null) return true;
    if (a == null || b == null) return false;
    return a.equals(b);
}


/*
if (dto.getCustomerId() == null || dto.getCustomerId().isEmpty()) {
    // Insert
    customerDao.insertCustomer(dto);
} else {
    // Edit
    customerDao.updateCustomer(dto);
}

 */