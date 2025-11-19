<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<html:html locale="true">
<head>
<html:base />
</head>
<body bgcolor="white">


	<html:form action="T001">

		<div id="errorZone">
			<html:errors />
		</div>

		<table>
			<tr>
				<td>UserId:</td>

				<td><html:text property="txtUserId" styleClass="inputField"
						styleId="txtUserId"></html:text></td>
			</tr>

			<tr>
				<td>Password:</td>

				<td><html:password styleId="txtPassword" property="txtPassword"></html:password></td>

			</tr>


		</table>
		<div>
			<html:submit property="btnLogin" value="Login" />
			<input type="button" value="Clear" onclick="clearForm()" />

		</div>


	</html:form>

</body>
<script>
	function clearForm() {

		//Cách này là bố của chắc chắn: Tóm đầu theo ID và ép về rỗng
		var uInput = document.getElementById("txtUserId");
		var pInput = document.getElementById("txtPassword");

		if (uInput)
			uInput.value = ""; // Ép về rỗng
		if (pInput)
			pInput.value = ""; // Ép về rỗng

		// Xử lý cái error message (cho nó biến mất luôn)
		var errorDiv = document.getElementById("errorZone");
		if (errorDiv) {
			errorDiv.style.display = "none"; // Ẩn đi
			// Hoặc sếp thích xóa nội dung thì dùng: errorDiv.innerHTML = "";
		}

		// Focus lại cho tiện tay gõ
		if (uInput)
			uInput.focus();

	}
</script>
</html:html>
