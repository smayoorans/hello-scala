<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<fmt:bundle basename="messages">

    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
        <head>
            <title><sitemesh:write property='title'/></title>
            <link href="<c:url value="/resources/css/template.css"/>" rel="stylesheet" type="text/css"/>
        </head>
        <body>
            <%--<%@ include file="/WEB-INF/decorators/header.jsp" %>--%>
            <sitemesh:write property='body'/>
           <%-- <%@ include file="/WEB-INF/decorators/footer.jsp" %>--%>
        </body>
    </html>

</fmt:bundle>
