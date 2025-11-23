<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>


<div class="login-container">

    <html:form action="/T001">

        <div id="errorZone">
            <html:errors />
        </div>

        <table>
            <tr>
                <td>UserId:</td>

                <td><html:text property="txtUserId"
                        styleClass="inputField" styleId="txtUserId"></html:text></td>
            </tr>

            <tr>
                <td>Password:</td>

                <td><html:password styleId="txtPassword"
                        property="txtPassword"></html:password></td>

            </tr>


        </table>
        <div>
            <html:submit property="btnLogin" value="Login" />
            <input type="button" value="Clear" onclick="clearForm()" />

        </div>


    </html:form>
</div>
