package form;

import org.apache.struts.action.ActionForm;

public class CustomerForm extends ActionForm {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String txtCustomerName;
    private String cboSex;
    private String txtBirthdayFrom;
    private String txtBirthdayTo;
    private int page;
    private final int sizePerPage = 5;

    public CustomerForm() {
        super();
    }

    public void resetForm() {
        this.txtCustomerName = null;
        this.cboSex = null;
        this.txtBirthdayFrom = null;
        this.txtBirthdayTo = null;
        this.page = 1;

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

    public String getTxtBirthdayFrom() {
        return txtBirthdayFrom;
    }

    public void setTxtBirthdayFrom(String txtBirthdayFrom) {
        this.txtBirthdayFrom = txtBirthdayFrom;
    }

    public String getTxtBirthdayTo() {
        return txtBirthdayTo;
    }

    public void setTxtBirthdayTo(String txtBirthdayTo) {
        this.txtBirthdayTo = txtBirthdayTo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSizePerPage() {
        return sizePerPage;
    }

}
