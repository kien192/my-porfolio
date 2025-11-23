package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class SecureAction extends DispatchAction {

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

   /*     HttpSession session = request.getSession(false); // false = không tự tạo mới

        if (session == null || session.getAttribute("userAccount") == null) {
            
            return mapping.findForward("loginPage"); 
        }*/

        return super.execute(mapping, form, request, response);
    }
}
