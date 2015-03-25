<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        <c:import url="/template/menuVertical.jsp" >
           <c:param name="tituloMenuPrincipal" value="Servicios"/>
           <c:param name="tituloMenuSecundario" value="SOAP"/>
        </c:import>
        
    </tiles:putAttribute>
    <tiles:putAttribute name="left">
        <script>
            function format(d) {
                return d.descripcion +
                        '<br /><a href="<c:url value="/servicios/soap/"/>' + d.id + '">Ver documentacion</a>';
            }
            
            $(document).ready(function () {
                //PARA DESPLEGAR SUBMENU AUTOMATICO EN HOVER
                $(function () {
                    $(document).on('mouseenter.collapse', '[data-toggle=collapse]', function (e) {
                        var $this = $(this),
                                href, target = $this.attr('data-target') || e.preventDefault() || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '')
                                ,
                                option = $(target).hasClass('in') ? 'hide' : "show";
                        $('.panel-collapse').not(target).collapse("hide");
                        $(target).collapse(option);
                    });
                });

                var table = $('#example').DataTable({
                    "search": "false",
                    "ajax": "<c:url value="/servicios/soapData" />",
                    "language": {
                        "url": "<c:url value="/resources/js/tableSpanish.json" />"
                    },
                    "columns": [
                        {
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
        <header >
            <div class="page-header">
                <br>
                <h1>SOAP</h1>
            </div>
            <table>
                <tr>
                    <td>${serv.nombre}</td>
                    <td>${serv.descripcion}</td>
                    <td>${serv.funcion.nombre}</td>
                    <td>${serv.area.nombre}</td>
                </tr>
            </table>
            <c:forEach items="${serv.versiones}" var="version" >
                ${version.version}
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
                <a href="newSoap">Nueva Api <img src="../resources/images/new.png"/></a>
            </div>
        </header>
        <!-- About Section -->
        <section id="about" class="content-section">
            <div class="divDataTable">               
            </div>            
        </section>
    </tiles:putAttribute>
</tiles:insertDefinition>
