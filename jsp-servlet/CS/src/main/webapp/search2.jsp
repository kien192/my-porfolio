<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, model.Customer" %>
<html>
<head>
<title>Customer Search</title>
<script>
function validateForm() {
  const fromDate = document.getElementById("fromDate").value;
  const toDate = document.getElementById("toDate").value;
  const name = document.getElementById("name").value;
  const regexDate = /^\d{4}\/\d{2}\/\d{2}$/;
  const regexName = /^[a-zA-Z0-9\s]*$/;

  if (!regexName.test(name)) {
    alert("Customer name không được chứa ký tự đặc biệt");
    return false;
  }

  if (fromDate && !regexDate.test(fromDate)) {
    alert("From date phải theo định dạng YYYY/MM/DD");
    return false;
  }

  if (toDate && !regexDate.test(toDate)) {
    alert("To date phải theo định dạng YYYY/MM/DD");
    return false;
  }

  if (fromDate && toDate && fromDate > toDate) {
    alert("From date không thể lớn hơn To date");
    return false;
  }

  return true;
}

function toggleSelectAll(source) {
  const checkboxes = document.getElementsByName("selectRow");
  for (let cb of checkboxes) cb.checked = source.checked;
}
</script>
</head>
<body>
<h2>Customer Search</h2>
<form action="search" method="get" onsubmit="return validateForm()">
  Name: <input type="text" name="name" id="name" value="${param.name}"/>
  Sex: 
  <select name="sex">
    <option value="">All</option>
    <option value="0" ${param.sex=="0"?"selected":""}>Male</option>
    <option value="1" ${param.sex=="1"?"selected":""}>Female</option>
  </select>
  Birthday:
  <input type="text" name="fromDate" id="fromDate" placeholder="YYYY/MM/DD" value="${param.fromDate}"/> ~
  <input type="text" name="toDate" id="toDate" placeholder="YYYY/MM/DD" value="${param.toDate}"/>
  <input type="submit" value="Search"/>
</form>

<table border="1" width="100%">
  <thead>
    <tr>
      <th><input type="checkbox" onclick="toggleSelectAll(this)"/></th>
      <th>ID</th>
      <th>Name</th>
      <th>Sex</th>
      <th>Birthday</th>
    </tr>
  </thead>
  <tbody>
  <%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    if (customers != null && !customers.isEmpty()) {
      for (Customer c : customers) {
  %>
    <tr>
      <td><input type="checkbox" name="selectRow" /></td>
      <td><%= c.getCustomerId() %></td>
      <td><%= c.getCustomerName() %></td>
      <td><%= c.getSex()==0?"Male":"Female" %></td>
      <td><%= c.getBirthday() %></td>
    </tr>
  <%
      }
    } else {
  %>
    <tr><td colspan="5" align="center">No data</td></tr>
  <%
    }
  %>
  </tbody>
</table>

<div style="margin-top:10px;">
  <%
    int page = (Integer) request.getAttribute("page");
    int totalPage = (Integer) request.getAttribute("totalPage");
    if (page > 1) {
  %>
  <a href="search?page=<%=page-1%>&name=<%=request.getParameter("name")%>&sex=<%=request.getParameter("sex")%>">&lt; Previous</a>
  <%
    }
    if (page < totalPage) {
  %>
  <a href="search?page=<%=page+1%>&name=<%=request.getParameter("name")%>&sex=<%=request.getParameter("sex")%>">Next &gt;</a>
  <%
    }
  %>
</div>

<td>
  <a href="edit?id=<%= c.getCustomerId() %>">
    <%= c.getCustomerId() %>
  </a>
</td>
</body>
</html>

