package dto;

public class CustomerDto {

    private int customerId;
    private String customerName;
    private String birthday;
    private String email;
    private String sex;
    private int psnUser;
    private String address;
    private int currentPage;
    private int sizePerPage;
    private String birthdayFrom;
    private String birthdayTo;

    public CustomerDto() {
        super();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
   /* public String getSexKey() {
        if (this.sex == null) return "gender.unknown";
        String s = String.valueOf(this.sex).trim();
        if ("0".equals(s)) return "gender.0";
        if ("1".equals(s)) return "gender.1";
        return "gender.unknown";
    }*/

    public int getPsnUser() {
        return psnUser;
    }

    public void setPsnUser(int psnUser) {
        this.psnUser = psnUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSizePerPage() {
        return sizePerPage;
    }

    public void setSizePerPage(int sizePerPage) {
        this.sizePerPage = sizePerPage;
    }

    public String getBirthdayFrom() {
        return birthdayFrom;
    }

    public void setBirthdayFrom(String birthdayFrom) {
        this.birthdayFrom = birthdayFrom;
    }

    public String getBirthdayTo() {
        return birthdayTo;
    }

    public void setBirthdayTo(String birthdayTo) {
        this.birthdayTo = birthdayTo;
    }

    public CustomerDto(int customerId, String customerName, String birthday, String email, String sex, int psnUser,
            String address, int currentPage, int sizePerPage, String birthdayFrom, String birthdayTo) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
        this.birthday = birthday;
        this.email = email;
        this.sex = sex;
        this.psnUser = psnUser;
        this.address = address;
        this.currentPage = currentPage;
        this.sizePerPage = sizePerPage;
        this.birthdayFrom = birthdayFrom;
        this.birthdayTo = birthdayTo;
    }

}
