<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        Derecha Soap
    </tiles:putAttribute>
    <tiles:putAttribute name="left">
       
            <table>
                <tr>
                    <td>${api.idApi}</td>
                    <td>${api.nombre}</td>
                </tr>
            </table>
       
    </tiles:putAttribute>
</tiles:insertDefinition>