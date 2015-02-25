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
            <p>Otra linea.</p>
        </div>
 
 <!--Comentario-->
 <!--Comentario2-->
 <!--Comentario3-->
 
    </tiles:putAttribute>
</tiles:insertDefinition>
