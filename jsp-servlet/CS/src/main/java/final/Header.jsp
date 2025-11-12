<%
    // Lấy thông tin breadcrumb context
    String currentPage = (String) request.getAttribute("currentPage");
    if (currentPage == null) currentPage = "";

    // Lấy lại state search (nếu có) để quay lại đúng kết quả
    String searchName = (String) session.getAttribute("searchName");
    String searchSex = (String) session.getAttribute("searchSex");
    String birthdayFrom = (String) session.getAttribute("birthdayFrom");
    String birthdayTo = (String) session.getAttribute("birthdayTo");
    String pageNum = (String) session.getAttribute("pageNum");
%>

<div style="font-family: Arial; margin-bottom: 10px;">
    <%
        if ("login".equals(currentPage)) {
    %>
    Login
    <%
    } else if ("search".equals(currentPage)) {
    %>
    <a href="Login.jsp">Login</a> &gt; Search
    <%
    } else if ("edit".equals(currentPage)) {
        String backToSearch = "CustomerSearchServlet?"
                + "customerName=" + (searchName != null ? java.net.URLEncoder.encode(searchName, "UTF-8") : "")
                + "&sex=" + (searchSex != null ? java.net.URLEncoder.encode(searchSex, "UTF-8") : "")
                + "&birthdayFrom=" + (birthdayFrom != null ? java.net.URLEncoder.encode(birthdayFrom, "UTF-8") : "")
                + "&birthdayTo=" + (birthdayTo != null ? java.net.URLEncoder.encode(birthdayTo, "UTF-8") : "")
                + "&page=" + (pageNum != null ? pageNum : "1");
    %>
    <a href="Login.jsp">Login</a> &gt;
    <a href="<%= backToSearch %>">Search</a> &gt; Edit
    <%
        }
    %>
</div>
<hr/>
