package form;

import org.apache.struts.action.ActionForm;

public class SearchForm extends ActionForm {
        /**
     * 
     */
    private static final long serialVersionUID = 1L;
        private String txtCustomerId;
        private String txtCustomerName;
        private String cboSex;
        private String birthday;
        private String email;
        private String address;
        public SearchForm() {
            super();
        }
        public String getTxtCustomerId() {
            return txtCustomerId;
        }
        public void setTxtCustomerId(String txtCustomerId) {
            this.txtCustomerId = txtCustomerId;
        }
        public String getTxtCustomerName() {
            return txtCustomerName;
        }
        public void setTxtCustomerName(String txtCustomerName) {
            this.txtCustomerName = txtCustomerName;
        }
        public String getCboSex() {
            return cboSex;
        }
        public void setCboSex(String cboSex) {
            this.cboSex = cboSex;
        }
        public String getBirthday() {
            return birthday;
        }
        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
        public String getEmail() {
            return email;
        }

    public void setEmail(String email) {
        this.email = email;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        
        
        
        
}
