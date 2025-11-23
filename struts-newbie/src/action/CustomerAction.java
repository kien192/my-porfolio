package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dto.CustomerDto;
import dto.PaginationResult;
import form.CustomerForm;
import service.CustomerService;

public class CustomerAction extends SecureAction {

    private CustomerService service = new CustomerService();

    public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        return search(mapping, form, request, response);

    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {

        CustomerForm f = (CustomerForm) form;

        String reqPage = request.getParameter("page");

        if (reqPage != null && !reqPage.isEmpty()) {
            try {
                f.setPage((Integer.parseInt(reqPage)));
            } catch (NumberFormatException e) {
                /* Ignore */ }
        }

        CustomerDto dto = new CustomerDto();
        dto.setCustomerName(f.getTxtCustomerName());
        dto.setCurrentPage((f.getPage()));
        dto.setSex(f.getCboSex());
        dto.setBirthdayFrom(f.getTxtBirthdayFrom());
        dto.setBirthdayTo(f.getTxtBirthdayTo());
        dto.setSizePerPage(f.getSizePerPage());

        /**
         * Logic disableFirstPage.
         * 
         * 
         */
        PaginationResult<CustomerDto> result = service.searchCustomer(dto);

        int curr = result.getCurrentPage();
        int total = result.getTotalPages();
        int records = result.getTotalRecords();

        boolean empty = (records == 0);

        request.setAttribute("result", result);
        request.setAttribute("customerList", result.getData());

        request.setAttribute("disableDelete", empty);
        request.setAttribute("disableFirstPage", empty || curr <= 1);
        request.setAttribute("disableLastPage", empty || curr >= total);

        request.setAttribute("preValue", curr > 1 ? curr - 1 : 1);
        request.setAttribute("nextValue", curr < total ? curr + 1 : total);
        request.setAttribute("lastValue", total);

        return mapping.findForward("success");
    }
    
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        
        return mapping.findForward("success");
    }
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        
        return mapping.findForward("success");
    }

}
