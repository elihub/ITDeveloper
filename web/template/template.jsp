<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang=''>
    <head>
        <!--
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/menu/script.js" />"></script>
        <link href="<c:url value="/resources/css/menu.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/header.css"/>" rel="stylesheet">
        <title>JSP Page</title>-->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <!--<link rel="icon" href="../../favicon.ico">-->


        <title>ITDeveloper</title>

        <!-- Bootstrap core CSS -->
        <link href="<c:url value="/resources/dist/css/bootstrap.min.css" />" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="<c:url value="/resources/dist/css/bootstrap-theme.min.css" />" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="<c:url value="/resources/dist/css/sticky-footer.css" />" rel="stylesheet">

        <!-- Custom styles for this template
        <link href="theme.css" rel="stylesheet"> -->

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
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="<c:url value="/resources/dist/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/assets/js/docs.min.js" />"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="<c:url value="/resources/assets/js/ie10-viewport-bug-workaround.js" />"></script>
        <!--Custom styles for my own styles-->
        <link href="<c:url value="/resources/dist/css/style.css" />" rel="stylesheet">
    </head>
    <body >
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="menu" />
        <div class="row">
            <div class="col-md-2">
                <tiles:insertAttribute name="right" />
            </div>
            <div class="col-md-10">
                <tiles:insertAttribute name="left" />
            </div>
        </div>
        <%--<tiles:insertAttribute name="body" />--%>
        <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>
