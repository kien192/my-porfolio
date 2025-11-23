<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<html:html locale="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
<html:base />
</head>

<body>

    <div id="header">
        <tiles:insert attribute="header" />
        <div id="breadcrumb">
            <tiles:insert attribute="breadcrumb" />
        </div>
    </div>

    <div id="content">
        <tiles:insert attribute="content" />
    </div>

    <div id="footer">
        <tiles:insert attribute="footer" />
    </div>

  <tiles:importAttribute name="js" scope="page" ignore="true"/>

    <logic:present name="js">
        <script type="text/javascript" src="<html:rewrite page='/'/><bean:write name='js'/>"></script>
    </logic:present>

</body>
</html:html>
