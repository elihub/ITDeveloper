<%-- 
    Document   : templateIndex
    Created on : 5/03/2015, 09:44:01 AM
    Author     : Elida Carrillo
--%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang=''>
    <head>
       
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../resources/images/favicon.ico">


        <title>ITDeveloper</title>

        <!-- Bootstrap core CSS -->
        <link href="<c:url value="/resources/dist/css/bootstrap.min.css" />" rel="stylesheet">        
        <!--Others-->
        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="<c:url value="../assets/js/ie-emulation-modes-warning.js" />"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Bootstrap core JavaScript
    ================================================== -->
       
    </head>
    <body>
       
        <tiles:insertAttribute name="content" />
        
    </body>
</html>
