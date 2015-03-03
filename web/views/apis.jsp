<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script>
    function format(d) {
        return 'Full name: ' + d.nombre + ' ' + d.descripcion + '<br>' +
                'Salary: ' + d.idUsuario + '<br>' +
                'The child row can contain any data you wish, including links, images, inner tables etc.';
    }

    $(document).ready(function () {
        var dt = $('#example').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": "apisJson",
            "columns": [
                {
                    "class": "details-control",
                    "orderable": false,
                    "data": "null",
                    "defaultContent": ""
                },
                {"data": "id"},
                {"data": "nombre"},
                {"data": "descripcion"},
                {"data": "idUsuario"}
            ],
            "order": [[1, 'asc']]
        });

        // Array to track the ids of the details displayed rows
        var detailRows = [];

        $('#example tbody').on('click', 'tr td:first-child', function () {
            var tr = $(this).closest('tr');
            var row = dt.row(tr);
            var idx = $.inArray(tr.attr('id'), detailRows);

            if (row.child.isShown()) {
                tr.removeClass('details');
                row.child.hide();

                // Remove from the 'open' array
                detailRows.splice(idx, 1);
            }
            else {
                tr.addClass('details');
                row.child(format(row.data())).show();

                // Add to the 'open' array
                if (idx === -1) {
                    detailRows.push(tr.attr('id'));
                }
            }
        });

        // On each draw, loop over the `detailRows` array and show any child rows
        dt.on('draw', function () {
            $.each(detailRows, function (i, id) {
                $('#' + id + ' td:first-child').trigger('click');
            });
        });
    });
</script>

<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        Derecha Soap
    </tiles:putAttribute>
    <tiles:putAttribute name="left">
        <div class="page-header">
            <h1>APIs</h1>
        </div>
        <div class="col-md-6">
            With our portfolio of over 100 SOAP APIs and a growing collection of 
            REST APIs, you can incorporate services across the entire travel journey 
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

    </tiles:putAttribute>
</tiles:insertDefinition>