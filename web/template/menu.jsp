<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>




<div style="display: inline;padding-left: 1% ">
    <!--&nbsp;-->
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