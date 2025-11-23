package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.LoginForm;

public class LoginAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        LoginForm f = (LoginForm) form;

        if ("admin".equals(f.getTxtUserId()) && "123".equals(f.getTxtPassword())) {
            return mapping.findForward("success");
        } else {
            return mapping.findForward("fail");
        }

    }

}