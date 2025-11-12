<!-- /common/header.jsp -->
<%
    String breadcrumb = (String) request.getAttribute("breadcrumb");
    if (breadcrumb == null) breadcrumb = "Login";
%>

<div style="background: #eee; padding: 8px;">
    <%= breadcrumb %>
</div>
<hr/>
