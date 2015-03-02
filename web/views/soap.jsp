<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        Izquierda Soap
    </tiles:putAttribute>
    <tiles:putAttribute name="left">
        SOAP
        <table>
            <tr>
                <td>${serv.nombre}</td>
                <td>${serv.descripcion}</td>
                <td>${serv.funcion.nombre}</td>
                <td>${serv.servicioTipo.nombre}</td>
                <td>${serv.area.nombre}</td>
            </tr>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>