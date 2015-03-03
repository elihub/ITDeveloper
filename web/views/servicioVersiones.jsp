<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        Izquierda Soap
    </tiles:putAttribute>
    <tiles:putAttribute name="left">
        Versiones
        <table>
            <tr>
                <td>${serv.nombre}</td>
                <td>${serv.descripcion}</td>
                <td>${serv.funcion.nombre}</td>
                <td>${serv.servicioTipo.nombre}</td>
                <td>${serv.area.nombre}</td>
            </tr>
        </table>
        <table class="table">
            <thead>
                <tr>
                    <th colspan="2">Downloads</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${serv.versiones}" var="version" >
                <tr>
                    <td>${version.valor}-${version.nombre}</td>
                    <td>link</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>