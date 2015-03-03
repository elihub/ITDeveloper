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
        <!-- Bootstrap theme -->
        <link href="<c:url value="/resources/dist/css/bootstrap-theme.min.css" />" rel="stylesheet">
        <!-- Custom styles -->
        <link href="<c:url value="/resources/dist/css/sticky-footer.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/table.css"/>" rel="stylesheet">
        <!--Others-->
        <link href="<c:url value="/resources/dist/css/grayscale.css" />" rel="stylesheet">
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
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>-->
        <script src="<c:url value="/resources/js/jquery-1.9.1.js" />"></script>
        <script src="<c:url value="/resources/js/dataTables.min.js" />"></script>
        <script src="<c:url value="/resources/dist/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/assets/js/docs.min.js" />"></script>        
        <script src="<c:url value="/resources/dist/js/jquery.easing.min.js" />"></script>
        <script src="<c:url value="/resources/dist/js/grayscale.js" />"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="<c:url value="/resources/assets/js/ie10-viewport-bug-workaround.js" />"></script>
        <!--Custom styles for my own styles-->
        <link href="<c:url value="/resources/dist/css/style.css" />" rel="stylesheet">
    </head>
    <body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="menu" />
        <tiles:insertAttribute name="content" />
        <tiles:insertAttribute name="footer" />
    </body>
</html>
