<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
            <div class="page-header">
            </div>
            <c:forEach items="menu" var="m">
                ${m}<br />
            </c:forEach>
            <div id="MainMenu">
                <div class="list-group panel" id="subMenu">
                    <a href="#demo4" class="list-group-item" data-toggle="collapse" data-parent="#MainMenu">${param.tituloMenuPrincipal}</a>
                    <div class="collapse in" id="demo4">
                        <a href="#sub" class="list-group-item list-group-item-success" data-toggle="collapse" data-parent="#subMenu">${param.tituloMenuSecundario}<span class="caret"></a>
                        <div class="collapse" id="sub">
                        <c:forEach items="${listaServicios}" var="servicio" >
                            <a href="<c:url value="/servicios/versiones/${servicio.id}" />" class="list-group-item">${servicio.nombre}</a>
                        </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </header>
