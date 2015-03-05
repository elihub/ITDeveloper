<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="row">
        <div class="span3 bs-docs-sidebar">
            <ul class="nav nav-list bs-docs-sidenav">
                <li><a href="#summary"><i class="icon-chevron-right"></i> Resumen</a></li>
                <li><a href="#downloads"><i class="icon-chevron-right"></i> Descargas</a></li>
                <li><a href="#documentation"><i class="icon-chevron-right"></i> Documentacion</a></li>
                <li><a href="#tecnology"><i class="icon-chevron-right"></i> Tecnología</a></li>
            </ul>
        </div>
        <div class="span9">
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
        </div>
    </div>
</div>