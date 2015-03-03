<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        Derecha Soap
    </tiles:putAttribute>
    <tiles:putAttribute name="left">

        <table>
            <tr>

                <td><div class="page-header">
                        <h1>${api.nombre}</h1>
                    </div></td>
            </tr>
            <tr>
                <td>${api.descripcion}</td>
                <td>${api.funcion.nombre}</td>
            </tr>
        </table>
        <table class="table">
            <thead>
                <tr>
                    <th colspan="2">Downloads</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${api.versiones}" var="version" >
                    <tr>
                        <td>${version.version} - ${version.valor}</td>
                        <td><a href=""><img src="../../resources/images/download2.png"/></a></td>
                
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>