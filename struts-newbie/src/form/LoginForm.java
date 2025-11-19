package form;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String txtUserId;
	private String txtPassword;

	public LoginForm() {

	}

	public String getTxtUserId() {
		return txtUserId;
	}

	public void setTxtUserId(String txtUserId) {
		this.txtUserId = txtUserId;
	}

	public String getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		// 1. Gọi validate gốc để Struts tự check
		ActionErrors allErrors = super.validate(mapping, request);

		// Nếu không có lỗi thì trả về luôn
		if (allErrors == null || allErrors.isEmpty()) {
			return allErrors;
		}

		// 2. Tạo list lỗi mới
		ActionErrors singleError = new ActionErrors();

		// 3. Lấy lỗi đầu tiên tìm thấy
		java.util.Iterator properties = allErrors.properties();

		if (properties.hasNext()) {
			String firstProperty = (String) properties.next();

			java.util.Iterator messages = allErrors.get(firstProperty);
			if (messages.hasNext()) {
				ActionMessage msg = (ActionMessage) messages.next();

				// Chỉ add duy nhất lỗi này vào list mới
				singleError.add(firstProperty, msg);
			}
		}

		// 4. Trả về
		return singleError;
	}

}
