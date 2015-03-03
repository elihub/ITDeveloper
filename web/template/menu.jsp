<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<nav class="navbar navbar-default navbar-fixed-top" >
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <!--<img height="50px" src="<c:url value="/resources/images/AM-LOGO.jpg" />">-->
            <a class="navbar-brand" href="<c:url value="/" />">ITDeveloper</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li  class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Servicios<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">REST</a></li>
                        <li class="divider"></li>
                        <li><a href="<c:url value="/servicios/soap" />">SOAP</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value="/apis/view"/>">Apis</a></li>
                <li><a href="#resources">Resources</a></li>
                <li><a href="#docs">Docs</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>