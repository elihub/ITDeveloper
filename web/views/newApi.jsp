<%-- 
    Document   : altaEditApi
    Created on : 4/03/2015, 04:39:43 PM
    Author     : Elida Carrillo
--%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Custom styles -->
<link href="<c:url value="/resources/dist/css/prettify.css" />" rel="stylesheet">
<script src="<c:url value="/resources/dist/js/jquery.bootstrap.wizard.js" />"></script>
<!--<script src="<c:url value="/resources/dist/js/jquery.bootstrap.wizard.min.js" />"></script>-->
<script src="<c:url value="/resources/dist/js/prettify.js" />"></script>

<script>
    $(document).ready(function () {
        /***********************Dinamic rows tab1**************************/
        var i = 1;
        $("#add_row").click(function () {
            $('#addr' + i).html("<td>" + (i + 1) + "</td>" +
                    "<td><input name='name" + i + "' type='text' placeholder='Nombre' class='form-control input-md'  /> </td>"
                    + "<td><input  name='resource" + i + "' type='file' id='resource'></td>");

            $('#tab_logic').append('<tr id="addr' + (i + 1) + '"></tr>');
            i++;
        });
        $("#delete_row").click(function () {
            if (i > 1) {
                $("#addr" + (i - 1)).html('');
                i--;
            }
        });
        /***********************Dinamic rows tab2**************************/
        var n = 1;
        $("#add_row2").click(function () {
            $('#add' + n).html("<td>" + (n + 1) + "</td>" +
                    "<td><input name='name" + n + "' type='text' placeholder='Nombre' class='form-control input-md'  /> </td>"
                    + "<td><textarea class='form-control' rows='3'></textarea></td>"
                    + "<td><input  name='resource" + n + "' type='file' id='resource'></td>");

            $('#tab_logic2').append('<tr id="add' + (n + 1) + '"></tr>');
            n++;
        });
        $("#delete_row2").click(function () {
            if (n > 1) {
                $("#add" + (n - 1)).html('');
                n--;
            }
        });


        /********************************Wizard steps********************************/
        $('#rootwizard').bootstrapWizard({onNext: function (tab, navigation, index) {
                /*if (index == 2) {
                 // Make sure we entered the name
                 if (!$('#name').val()) {
                 alert('You must enter your name');
                 $('#name').focus();
                 return false;
                 }
                 }*/

                // Set the name for the next tab
                //$('#tab3').html('Hello, ' + $('#name').val());

            }, onTabShow: function (tab, navigation, index) {
                var $total = navigation.find('li').length;
                var $current = index + 1;
                var $percent = ($current / $total) * 100;
                $('#rootwizard .progress-bar').css({width: $percent + '%'});
            }});
        window.prettyPrint && prettyPrint()
        function submit(){
            alert("fin")
        }
    });



</script>        

<div class='container'  style="width: 70%;padding-left: 20%">

    <section id="wizard">
        <div class="page-header">
            <h3>Alta de API</h3>
        </div>

        <div id="rootwizard">
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container">
                        <ul>
                            <li><a href="#tab1" data-toggle="tab">Nombre de la API</a></li>                                    
                            <li><a href="#tab2" data-toggle="tab">Subir Resour(es)</a></li>
                            <li><a href="#tab3" data-toggle="tab">Subir Documentación</a></li>
                            <li><a href="#tab4" data-toggle="tab">Finalizar</a></li>

                        </ul>
                    </div>
                </div>
            </div>
            <div id="bar" class="progress">
                <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
            </div>
            <div class="tab-content">
                <form role="form" class="tab-content" action="new" method="POST">
                    <div class="tab-pane" id="tab1">
                        <label for="nombre">Nombre:</label>
                        <input type="text" value="${api.nombre}" class="form-control" id="nombre" name="nombre" placeholder="Nombre Api">
                        <label for="version">Versión:</label>
                        <input type="text" value="${api.versiones.get(0).version}" class="form-control" id="version" name="version"  placeholder="Versión">
                        <label for="descripcion">Descripción:</label>
                        <input type="text"  value="${api.descripcion}" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción">
                        <label for="resumen">Resumen:</label>
                        <textarea value="${api.resumen}" class="form-control" rows="3"></textarea>
                    </div>
                        <jsp:useBean id="listVersion" class="com.aeromexico.tideveloper.models.Api" scope="request"/>
                        <jsp:setProperty name ="listVersion" property="version" value="1"/>
                        <jsp:setProperty name ="listVersion" property="version" value="2"/>
                        
                    <div class="tab-pane" id="tab2">
                        <!--<p>
                            <input type='text' name='name' id='name' placeholder='Enter Your Name'>
                        </p>-->
                        <table class="table table-bordered table-hover" id="tab_logic">
                            <thead>
                                <tr >
                                    <th class="text-center">
                                        #
                                    </th>
                                    <th class="text-center">
                                        Nombre
                                    </th>                                   
                                    <th class="text-center">
                                        Resource
                                    </th>                                            
                                </tr>
                            </thead>
                            <tbody>
                                <tr id='addr0'>
                                    <td>
                                        1
                                    </td>
                                    <td>
                                        <input type="text" name=''  placeholder='Nombre Archivo' class="form-control"/>
                                    </td>
                                    <td>
                                        <input type="file" id="resource">
                                    </td> 

                                </tr>
                                <tr id='addr1'></tr>
                            </tbody>                                    
                            <tfoot>
                            <a id="add_row" class="btn btn-default pull-left">Nuevo</a>
                            <a id='delete_row' class="pull-right btn btn-default">Eliminar</a>
                            </tfoot>
                        </table>


                    </div>
                    <div class="tab-pane" id="tab3">
                        <table class="table table-bordered table-hover" id="tab_logic2">
                            <thead>
                                <tr >
                                    <th class="text-center">
                                        #
                                    </th>
                                    <th class="text-center">
                                        Nombre Archivo
                                    </th>
                                    <th class="text-center">
                                        Resumen
                                    </th>
                                    <th class="text-center">
                                        Archivo
                                    </th>                                            
                                </tr>
                            </thead>
                            <tbody>
                                <tr id='add0'>
                                    <td>
                                        1
                                    </td>
                                    <td>
                                        <input type="text" name=''  placeholder='Nombre' class="form-control"/>
                                    </td>
                                    <td>
                                        <textarea class="form-control" rows="3"></textarea>
                                    </td>
                                    <td>
                                        <input type="file" id="resource">
                                    </td> 

                                </tr>
                                <tr id='add1'></tr>
                            </tbody>                                    
                            <tfoot>
                            <a id="add_row2" class="btn btn-default pull-left">Nuevo</a>
                            <a id='delete_row2' class="pull-right btn btn-default">Eliminar</a>
                            </tfoot>
                        </table>
                    </div>
                    <div class="tab-pane" id="tab4">                       
                        <input class="btn btn-default" type="submit" onclick="" value="Guardar">
                    </div>

                    <ul class="pager wizard">
                        <li class="previous first" style="display:none;"><a href="#">First</a></li>
                        <li class="previous"><a href="#">Previous</a></li>
                        <li class="next last" style="display:none;"><a href="#">Last</a></li>
                        <li class="next"><a href="#">Next</a></li>
                    </ul>

                </form>
            </div>
        </div>

    </section>
</div>

