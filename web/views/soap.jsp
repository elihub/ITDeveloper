<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        <c:forEach items="${menu}" var="m">
            ${m} <br />
        </c:forEach>
        <ul role="menu" aria-labelledby="dropdownMenu" style="display: block; position: static; margin-bottom: 5px; *width: 180px;">
            <li><a tabindex="-1" href="#">Action</a></li>
            <li><a tabindex="-1" href="#">Another action</a></li>
            <li><a tabindex="-1" href="#">Something that is a really really really long string here</a></li>
            <li class="divider"></li>
            <li class="dropdown-submenu left-submenu"> <a tabindex="-1" href="#">More options</a>
                <ul class="dropdown-menu">
                    <li><a tabindex="-1" href="#">shorter things</a></li>
                    <li><a tabindex="-1" href="#">shorter things</a></li>
                    <li><a tabindex="-1" href="#">shorter things</a></li>
                    <li><a tabindex="-1" href="#">Second level link</a></li>
                    <li><a tabindex="-1" href="#">Second level link</a></li>
                    <li><a tabindex="-1" href="#">Second level link</a></li>
                    <li><a tabindex="-1" href="#">Second level link</a></li>
                </ul>
            </li>
        </ul>

        <div id="MainMenu">
            <div class="list-group panel">
                <a href="#demo3" class="list-group-item list-group-item-success" data-toggle="collapse" data-parent="#MainMenu">Item 3</a>
                <div id="demo3">
                    <a href="#SubMenu1" class="list-group-item" data-toggle="collapse" data-parent="#SubMenu1">Subitem 1 <i class="fa fa-caret-down"></i></a>
                    <div class="collapse list-group-submenu" id="SubMenu1">
                        <a href="#" class="list-group-item" data-parent="#SubMenu1">Subitem 1 a</a>
                        <a href="#" class="list-group-item" data-parent="#SubMenu1">Subitem 2 b</a>
                        <a href="#SubSubMenu1" class="list-group-item" data-toggle="collapse" data-parent="#SubSubMenu1">Subitem 3 c <i class="fa fa-caret-down"></i></a>
                        <div class="collapse list-group-submenu list-group-submenu-1" id="SubSubMenu1">
                            <a href="#" class="list-group-item" data-parent="#SubSubMenu1">Sub sub item 1</a>
                            <a href="#" class="list-group-item" data-parent="#SubSubMenu1">Sub sub item 2</a>
                        </div>
                        <a href="#" class="list-group-item" data-parent="#SubMenu1">Subitem 4 d</a>
                    </div>
                    <a href="javascript:;" class="list-group-item">Subitem 2</a>
                    <a href="javascript:;" class="list-group-item">Subitem 3</a>
                </div>
                <a href="#demo4" class="list-group-item list-group-item-success" data-toggle="collapse" data-parent="#MainMenu">Item 4</a>
                <div class="collapse" id="demo4">
                    <a href="" class="list-group-item">Subitem 1</a>
                    <a href="" class="list-group-item">Subitem 2</a>
                    <a href="" class="list-group-item">Subitem 3</a>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
    <tiles:putAttribute name="left">
        <script>
            function format(d) {
                return d.descripcion +
                        '<br /><a href="<c:url value="/servicios/versiones/"/>' + d.id + '">Ver documentacion</a>';
            }
            $(document).ready(function () {
                var table = $('#example').DataTable({
                    "ajax": "<c:url value="/servicios/soapData" />",
                    "language": {
                        "url": "<c:url value="/resources/js/tableSpanish.json" />"
                    },
                    "columns": [
                        {
                            "title": '',
                            "className": 'details-control',
                            "orderable": false,
                            "data": null,
                            "defaultContent": ''
                        },
                        {"data": 'id'},
                        {"data": 'nombre'},
                        {"data": 'area.nombre'},
                        {"data": 'funcion.nombre'}
                    ],
                    "order": [[1, 'asc']]
                });

                // Add event listener for opening and closing details
                $('#example tbody').on('click', 'td.details-control', function () {
                    var tr = $(this).closest('tr');
                    var row = table.row(tr);

                    if (row.child.isShown()) {
                        // This row is already open - close it
                        row.child.hide();
                        tr.removeClass('shown');
                    }
                    else {
                        // Open this row
                        row.child(format(row.data())).show();
                        tr.addClass('shown');
                    }
                });
            });

        </script>
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
        <c:forEach items="${serv.versiones}" var="version" >
            ${version.nombre}
            </br>
            ${version.valor}
            </br>
        </c:forEach>
        <div class="divDataTable">
            <table id="example" class="display" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th></th>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Area</th>
                        <th>Funcion</th>
                    </tr>
                </thead>

                <tfoot>
                    <tr>
                        <th></th>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Area</th>
                        <th>Funcion</th>
                    </tr>
                </tfoot>
            </table>
        </div>
        <div class="container">
            <h1>Spring MVC 3.1 Demo Endpoints</h1>
            <c:forEach items="${handlerMethods}" var="entry">
                <div>
                    <hr>
                    <p><strong>${entry.value}</strong></p>      
                </div>
                <div class="span-3 colborder">
                    <p>
                        <span class="alt">Patterns:</span><br> 
                        <c:if test="${not empty entry.key.patternsCondition.patterns}">
                            ${entry.key.patternsCondition.patterns}
                        </c:if>
                    </p>
                </div>
            </c:forEach>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>