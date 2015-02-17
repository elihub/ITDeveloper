<%-- 
    Document   : template
    Created on : 17/02/2015, 12:07:03 PM
    Author     : Elida Carrillo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value='/resources/css/main.css' />" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
