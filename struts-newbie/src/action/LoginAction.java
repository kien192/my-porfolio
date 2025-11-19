package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import dto.UserDto;
import form.LoginForm;
import service.UserService;

public class LoginAction extends Action {

	private UserService userService = new UserService(); // Service layer


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		LoginForm f = (LoginForm) form;

		if ("admin".equals(f.getTxtUserId()) && "123".equals(f.getTxtPassword())) {
            return mapping.findForward("success");
        } else {
            // Sai mật khẩu -> Bắn lỗi ra html:errors
            ActionMessages errors = new ActionMessages();
            // "error.login.failed" là key trong properties file
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.login.failed"));
            saveErrors(request, errors);
            
            return mapping.findForward("fail");
        }

//		boolean isAuthenticated = userService.authenticateUser(userDto);
//
//		if (!isAuthenticated) {
//			return mapping.findForward("fail");
//		}
//
//		// Nếu hợp lệ, lấy thông tin user
//		UserDto userInfo = userService.getUserInformationByUserId(f.getTxtUserId());
//		request.getSession().setAttribute("user", userInfo);
//
//		return mapping.findForward("success");
	}
	
}