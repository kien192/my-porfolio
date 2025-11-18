package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dto.UserDto;
import form.LoginForm;
import service.UserService;

public class LoginAction extends Action {

	private UserService userService = new UserService(); // Service layer

	private boolean checkEmptyField(HttpServletRequest request, LoginForm f, String fieldValue, String messageKey) {
		if (fieldValue == null || fieldValue.trim().isEmpty()) {
			f.setLblMessage(getResources(request).getMessage(messageKey));
			return true;
		}
		return false;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		LoginForm f = (LoginForm) form;

		// 1. Check userId rỗng
		if (checkEmptyField(request, f, f.getTxtUserId(), "error.userid.required")) {
			return mapping.findForward("fail");
		}

		// 2. Check password rỗng
		if (checkEmptyField(request, f, f.getTxtPassword(), "error.password.required")) {
			return mapping.findForward("fail");
		}

		// 3. Kiểm tra tài khoản
		UserDto userDto = new UserDto();
		userDto.setUserId(f.getTxtUserId());
		userDto.setPassword(f.getTxtPassword());

		boolean isAuthenticated = userService.authenticateUser(userDto);

		if (!isAuthenticated) {
			f.setLblMessage(getResources(request).getMessage("error.login.invalid"));
			return mapping.findForward("fail");
		}

		// Nếu hợp lệ, lấy thông tin user
		UserDto userInfo = userService.getUserInformationByUserId(f.getTxtUserId());
		request.getSession().setAttribute("user", userInfo);

		return mapping.findForward("success");
	}

}