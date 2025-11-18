<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<html:html locale="true">
<head>
<html:base />
</head>
<body bgcolor="white">


	<html:form action="T001">

		<div>
			<bean:write name="loginForm" property="lblMessage" />
		</div>

		<table>
			<tr>
				<td>UserId:</td>

				<td><html:text property="txtUserId" styleClass="inputField"
						styleId="txtUserId"></html:text></td>



			</tr>

			<tr>
				<td>UserId:</td>

				<td><html:password property="txtPassword"></html:password></td>

			</tr>


		</table>
		<div>
			<html:submit property="btnLogin" value="Login" />
			<html:reset property="btnClear" value="Clear" />

		</div>


	</html:form>

</body>
</html:html>
