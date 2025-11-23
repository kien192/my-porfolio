<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<div class="search-container">

     <html:form action="/T003">
    <table>
        <tr>
            <td><label for="txtCustomerId">CustomerId</label></td>
            <td><html:text property="txtCustomerId"
                    styleId="txtCustomerId"></html:text></td>
        </tr>
        <tr>
            <td><label for="txtCustomerName">Customer Name</label></td>
            <td><html:text property="txtCustomerName"
                    styleId="txtCustomerName"></html:text></td>
        </tr>
        <tr>
            <td><label for="cboSex">sex</label></td>
            <td><html:text property="cboSex" styleId="cboSex"></html:text></td>
        </tr>
        <tr>
            <td><label for="birthday">birthday</label></td>
            <td><html:text property="birthday" styleId="birthday"></html:text></td>
        </tr>
        <tr>
            <td><label for="email">email</label></td>
            <td><html:text property="email" styleId="email"></html:text></td>
        </tr>
        <tr>
            <td><label for="address">address</label></td>
            <td><html:text property="address" styleId="address"></html:text></td>
        </tr>
    </table>
    </html:form>

</div>