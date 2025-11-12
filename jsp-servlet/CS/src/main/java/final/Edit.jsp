<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="common/header.jsp" />

<%
    model.Customer c = (model.Customer) request.getAttribute("customer");
    String mode = (String) request.getAttribute("mode");
%>

<h2><%= "add".equals(mode) ? "Add New Customer" : "Edit Customer" %></h2>

<form action="CustomerEditServlet" method="post">
    <input type="hidden" name="mode" value="<%= mode %>">
    <input type="hidden" name="customerId" value="<%= c != null ? c.getId() : "" %>">
    Name: <input type="text" name="name" value="<%= c != null ? c.getName() : "" %>"><br>
    Sex:
    <select name="sex">
        <option value="M" <%= c != null && "M".equals(c.getSex()) ? "selected" : "" %>>Male</option>
        <option value="F" <%= c != null && "F".equals(c.getSex()) ? "selected" : "" %>>Female</option>
    </select><br>
    <input type="submit" value="Save">
</form>
