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
                        /*{data: null,
                         orderable: false,
                         defaultContent: '<a href="" class="edit"><img src="../resources/images/edit.png"></a>'},
                         {data: null,
                         orderable: false,
                         defaultContent: '<a href="" class="remove"><img src="../resources/images/remove.png"></a>'}*/

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
            // Delete a record            


        </script>        
        <!-- Intro Header -->
        <header>
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
                            <!--<th></th>
                            <th></th>-->

                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th></th>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <!--<th></th>
                            <th></th>-->

                        </tr>
                    </tfoot>
                </table>
                <a href="new">Nueva Api <img src="../resources/images/new.png"/></a>

        </header>
        <!-- About Section -->
        <section id="new" class="content-section">

            <!--<div class="divDataForm">
                <form role="form">
                    <div class="form-group">
                        <label for="ejemplo_email_1">Email</label>
                        <input type="email" class="form-control" id="ejemplo_email_1"
                               placeholder="Introduce tu email">
                    </div>
                    <div class="form-group">
                        <label for="ejemplo_password_1">Contraseña</label>
                        <input type="password" class="form-control" id="ejemplo_password_1" 
                               placeholder="Contraseña">
                    </div>
                    <div class="form-group">
                        <label for="ejemplo_archivo_1">Adjuntar un archivo</label>
                        <input type="file" id="ejemplo_archivo_1">
                        <p class="help-block">Ejemplo de texto de ayuda.</p>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Activa esta casilla
                        </label>
                    </div>
                    <button type="submit" class="btn  btn-default ">Enviar</button>
                </form>
            </div>  -->       
        </section>
    </tiles:putAttribute>
</tiles:insertDefinition>