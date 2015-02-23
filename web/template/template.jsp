<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang=''>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/menu/script.js" />"></script>
       <link href="<c:url value="/resources/css/menu.css"/>" rel="stylesheet">
       <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/header.css"/>" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body >
        <div class="page">
        <tiles:insertAttribute name="header" />
        <div class="content">
            <tiles:insertAttribute name="menu" />
            <tiles:insertAttribute name="body" />
        </div>
        <tiles:insertAttribute name="footer" />
    </div>
    </body>
</html>
