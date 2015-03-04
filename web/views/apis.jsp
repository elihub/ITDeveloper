<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        Derecha Soap
    </tiles:putAttribute>
    <tiles:putAttribute name="left">        
        <script>
            function format(d) {
                return d.descripcion + "  " + "<a href='<c:url value="view/" />" + d.id + "'>Ver documentacion</a>";
            }
            $(document).ready(function () {
                var table = $('#example').DataTable({
                    "ajax": "<c:url value="apisJson" />",
                    "columns": [
                        {
                            "title": '',
                            "className": 'details-control',
                            "orderable": false,
                            "data": 'valor',
                            "defaultContent": ''
                        },
                        {"data": 'id'},
                        {"data": 'nombre'},
                        {"data": 'descripcion'},
                        {"data": 'idUsuario'}
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
        <!-- Intro Header -->
        <header >
            <div class="divDataTable">
                <div class="page-header">
                    <br>
                    <h1>APIs</h1>
                </div>
                <div class="col-md-6">
                    With our portfolio of over 100 APIs and a growing collection 
                    , you can incorporate functions across the entire travel journey 
                    ? from dreaming, to planning, to purchasing and servicing trips.
                </div>
                <table id="example" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>IdUsuario</th>
                        </tr>
                    </thead>

                    <tfoot>
                        <tr>
                            <th></th>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>IdUsuario</th>
                        </tr>
                    </tfoot>
                </table>
                Nueva Api  <a href="#new"><img src="../resources/images/new.png"/></a>

        </header>
        <!-- About Section -->
        <section id="new" class="content-section">
           
                <div class="divDataTable">
                    <h2>About Grayscale</h2>
                    <p>Grayscale is a free Bootstrap 3 theme created by Start Bootstrap. It can be yours right now, simply download the template on <a href="http://startbootstrap.com/template-overviews/grayscale/">the preview page</a>. The theme is open source, and you can use it for any purpose, personal or commercial.</p>
                    <p>This theme features stock photos by <a href="http://gratisography.com/">Gratisography</a> along with a custom Google Maps skin courtesy of <a href="http://snazzymaps.com/">Snazzy Maps</a>.</p>
                    <p>Grayscale includes full HTML, CSS, and custom JavaScript files along with LESS files for easy customization.</p>
                </div>            
        </section>
    </tiles:putAttribute>
</tiles:insertDefinition>