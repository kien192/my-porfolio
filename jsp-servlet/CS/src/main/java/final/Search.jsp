<%@ include file="/common/header.jsp" %>

<!-- FORM 1: SEARCH CONDITION -->
<form action="CustomerSearchServlet" method="get">
    <label>Customer Name:</label>
    <input type="text" name="customerName" value="<%= request.getParameter("customerName") != null ? request.getParameter("customerName") : "" %>"/>

    <label>Sex:</label>
    <input type="text" name="sex" value="<%= request.getParameter("sex") != null ? request.getParameter("sex") : "" %>"/>

    <label>Birthday From:</label>
    <input type="text" name="birthdayFrom" value="<%= request.getParameter("birthdayFrom") != null ? request.getParameter("birthdayFrom") : "" %>"/>

    <label>Birthday To:</label>
    <input type="text" name="birthdayTo" value="<%= request.getParameter("birthdayTo") != null ? request.getParameter("birthdayTo") : "" %>"/>

    <input type="hidden" name="page" value="1"/>
    <input type="submit" value="Search"/>
</form>

<hr/>

<!-- FORM 2: TABLE + DELETE + ADD NEW -->
<form action="CustomerSearchServlet" method="post">
    <table border="1" width="100%">
        <tr>
            <th>Select</th><th>ID</th><th>Name</th><th>Sex</th><th>Birthday</th>
        </tr>

        <%
            java.util.List<Customer> list = (java.util.List<Customer>) request.getAttribute("customerList");
            if (list != null) {
                for (Customer c : list) {
        %>
        <tr>
            <td><input type="checkbox" name="deleteIds" value="<%= c.getCustomerId() %>"/></td>
            <td><a href="CustomerEditServlet?customerId=<%= c.getCustomerId() %>"><%= c.getCustomerId() %></a></td>
            <td><%= c.getCustomerName() %></td>
            <td><%= c.getSex() %></td>
            <td><%= c.getBirthday() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <!-- Giữ lại state search -->
    <input type="hidden" name="customerName" value="<%= request.getParameter("customerName") %>"/>
    <input type="hidden" name="sex" value="<%= request.getParameter("sex") %>"/>
    <input type="hidden" name="birthdayFrom" value="<%= request.getParameter("birthdayFrom") %>"/>
    <input type="hidden" name="birthdayTo" value="<%= request.getParameter("birthdayTo") %>"/>
    <input type="hidden" name="page" value="<%= request.getParameter("page") %>"/>

    <input type="submit" name="action" value="Delete"/>
    <input type="button" value="Add New" onclick="goAddNew()"/>
</form>

<script>
    function goAddNew() {
        const form = document.forms[1]; // form thứ 2
        const params = new URLSearchParams();
        params.append('customerName', form.customerName.value);
        params.append('sex', form.sex.value);
        params.append('birthdayFrom', form.birthdayFrom.value);
        params.append('birthdayTo', form.birthdayTo.value);
        params.append('page', form.page.value);
        window.location.href = 'CustomerEditServlet?' + params.toString();
    }
</script>
