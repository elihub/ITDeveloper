<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="right">
        Derecha
    </tiles:putAttribute>
    <tiles:putAttribute name="left">
        Izquierda
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="body">
            <h1>Home page !</h1> 
            <p>The time on the server is ${serverTime}.</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore
                magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                <a href="../../redirect.jsp"></a>
                <a href="<c:url value="/views/bootstrap.jsp"/>">Bootstrap</a>
                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</P>
        </div>
 
 <!--Comentario-->
 <!--Comentario2-->
 <!--Comentario3-->
 
    </tiles:putAttribute>
</tiles:insertDefinition>
