package form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String lblMessage;
	private String txtUserId;
	private String txtPassword;

	public LoginForm() {

	}

	public LoginForm(String lblMessage, String txtUserId, String txtPassword) {
		super();
		this.lblMessage = lblMessage;
		this.txtUserId = txtUserId;
		this.txtPassword = txtPassword;
	}

	public String getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(String lblMessage) {
		this.lblMessage = lblMessage;
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

}
