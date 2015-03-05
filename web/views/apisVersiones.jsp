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
            </tr>
        </table>
        <c:forEach items="${api.versiones}" var="version" >
        <table class="table">
            <thead>
                <tr>
                    <th colspan="2">v${version.version}</th>
                </tr>
            </thead>
            <tbody>
                    <c:forEach items="${version.resources}" var="resource" >
                    <tr>
                        <td>${version.version} - ${resource.nombre}</td>
                        <td><a href=""><img src="../../resources/images/download2.png"/></a></td>
                
                    </tr>
                    </c:forEach>
            </tbody>
        </table>
        </c:forEach>
    </tiles:putAttribute>
</tiles:insertDefinition>