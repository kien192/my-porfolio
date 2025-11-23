package service;

import java.util.List;

import dao.CustomerDao;
import dto.CustomerDto;
import dto.PaginationResult;

public class CustomerService {

    private final CustomerDao customerDao = new CustomerDao();

    public PaginationResult<CustomerDto> searchCustomer(CustomerDto dto) {
        if (dto.getCurrentPage() < 1)
            dto.setCurrentPage(1); 

        int totalRecords = customerDao.countCustomer(dto);
        
        int totalPages = (int) Math.ceil((double) totalRecords / dto.getSizePerPage());
        if (dto.getCurrentPage() > totalPages && totalPages > 0)
            dto.setCurrentPage( totalPages);

        List<CustomerDto> list = customerDao.searchCustomer(dto);

        return new PaginationResult<>(list, dto.getCurrentPage(), totalPages, totalRecords);
    }
}
