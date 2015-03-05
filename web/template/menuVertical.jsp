<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
            <div class="page-header">
            </div>
            <div id="MainMenu">
            <c:forEach items="${menu}" var="m" varStatus="i">
                <c:if test="${i.index == 0}">
                    <div class="list-group panel" id="subMenu">
                    <a href="#demo" class="list-group-item" data-toggle="collapse" data-parent="#MainMenu">${m.nombre}</a>
                </c:if>
                <c:if test="${i.index == 1}">
                        <div class="collapse ${m.activo}" id="demo">
                        <a href="#sub${i.index}" class="list-group-item list-group-item-success" data-toggle="collapse" data-parent="#subMenu">${m.nombre}<span class="caret"></a>
                        <div class="collapse" id="sub${i.index}">
                        <c:forEach items="${listaServicios}" var="servicio" >
                            <a href="<c:url value="/servicios/versiones/${servicio.id}" />" class="list-group-item">${servicio.nombre}</a>
                        </c:forEach>
                        </div>
                        </div>
                        </div>
                </c:if>
            </c:forEach>
                        
                 
            </div>
    
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
