<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<div class="login-container">
    <h1>
        Welcome
        <bean:write name="result" property="totalRecords" />
    </h1>
    <html:form action="/T002.do?method=search">
        <div class="searchForm">



            <html:text property="txtCustomerName"
                styleId="txtCustomerName">

            </html:text>

            <html:select property="cboSex" styleId="cboSex">
                <html:option value=""></html:option>
                <html:option value="0">Male</html:option>
                <html:option value="1">Female</html:option>


            </html:select>


            <html:text property="txtBirthdayFrom"
                styleId="txtBirthdayFrom">

            </html:text>
            <html:text property="txtBirthdayTo"
                styleId="txtBirthdayFrom">

            </html:text>

            <html:submit styleId="btnSearch"></html:submit>
        </div>

        <div class="paginationForm">
            <html:hidden property="page" styleId="targetPage" />


            <logic:equal name="disableFirstPage" value="true">
                <input type="button" value="First" disabled="disabled" />
                <input type="button" value="Previous"
                    disabled="disabled" />
            </logic:equal>

            <logic:notEqual name="disableFirstPage" value="true">
                <input type="button" value="First"
                    onclick="gotoPage('1');" />

                <input type="button" value="Previous"
                    onclick="gotoPage('<bean:write name="preValue" />');" />
            </logic:notEqual>

            <%-- ================= NEXT & LAST BUTTONS ================= --%>
            <logic:equal name="disableLastPage" value="true">
                <input type="button" value="Next" disabled="disabled" />
                <input type="button" value="Last" disabled="disabled" />
            </logic:equal>

            <logic:notEqual name="disableLastPage" value="true">
                <input type="button" value="Next"
                    onclick="gotoPage('<bean:write name="nextValue" />');" />

                <input type="button" value="Last"
                    onclick="gotoPage('<bean:write name="lastValue" />');" />
            </logic:notEqual>


        </div>

    </html:form>

    <div class="selectForm">
        <table>
            <thead>
                <tr>
                    <th>Select</th>
                    <th>CustomerId</th>
                    <th>CustomerName</th>
                    <th>Sex</th>
                    <th>Birthday</th>
                    <th>Address</th>

                </tr>

            </thead>
            <tbody>
                <logic:iterate id="cust" name="customerList">
                    <tr>
                        <td></td>
                        <td><html:link
                                action="/T003.do?method=display"
                                paramId="customerId" paramName="cust"
                                paramProperty="customerId">
                                <bean:write name="cust"
                                    property="customerId" />
                            </html:link></td>
                        <td><bean:write name="cust"
                                property="customerName" /></td>
                        <td><logic:empty name="cust" property="sex">
        &nbsp;
    </logic:empty> <logic:notEmpty name="cust" property="sex">
                                <logic:equal name="cust" property="sex"
                                    value="0">
            Male
        </logic:equal>
                                <logic:equal name="cust" property="sex"
                                    value="1">
            Female
        </logic:equal>
                            </logic:notEmpty></td>
                        <td><bean:write name="cust"
                                property="birthday" /></td>
                        <td><bean:write name="cust"
                                property="address" /></td>

                    </tr>
                </logic:iterate>
            </tbody>
        </table>
    </div>
    
    <html:button property="btnAdd" onclick="location.href='T003.do?method=display'">Add New</html:button>


</div>