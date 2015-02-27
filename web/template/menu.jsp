<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--
<div style="display: inline;padding-left: 1% ">
<!--&nbsp;
<img src="<c:url value="/resources/images/AM-LOGO.jpg" />">
</div>
<div id='cssmenu'>      
<ul> 
    <li><a href='#'><span>Home</span></a></li>
    <li class='active has-sub'><a href='#'><span>Products</span></a>
        <ul>
            <li class='has-sub'><a href='#'><span>Product 1</span></a>
                <ul>
                    <li><a href='#'><span>Sub Product</span></a></li>
                    <li class='last'><a href='#'><span>Sub Product</span></a></li>
                </ul>
            </li>
            <li class='has-sub'><a href='#'><span>Product 2</span></a>
                <ul>
                    <li><a href='#'><span>Sub Product</span></a></li>
                    <li class='last'><a href='#'><span>Sub Product</span></a></li>
                </ul>
            </li>
        </ul>
    </li>
    <li><a href='#'><span>About</span></a></li>
    <li class='last'><a href='#'><span>Contact</span></a></li>
</ul>
</div>
-->
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <!--<img height="50px" src="<c:url value="/resources/images/AM-LOGO.jpg" />">-->
            <a class="navbar-brand" href="#">ITDeveloper</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li  class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Servicios<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">REST</a></li>
                        <li class="divider"></li>
                        <li><a href="<c:url value="/soap" />">SOAP</a></li>
                    </ul>
                </li>
                <li><a href="#apis">Apis</a></li>
                <li><a href="#resources">Resources</a></li>
                <li><a href="#docs">Docs</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>