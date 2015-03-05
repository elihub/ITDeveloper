<%-- 
    Document   : altaEditApi
    Created on : 4/03/2015, 04:39:43 PM
    Author     : Elida Carrillo
--%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        Derecha Soap
    </tiles:putAttribute>
    <tiles:putAttribute name="left">        
        <script>
            $(document).ready(function () {
                var i = 1;
                $("#add_row").click(function () {
                    $('#addr' + i).html("<td>" + (i + 1) + "</td><td><input name='name" + i + "' type='text' placeholder='Name' class='form-control input-md'  /> </td><td><input  name='mail" + i + "' type='text' placeholder='Mail'  class='form-control input-md'></td><td><input  name='mobile" + i + "' type='text' placeholder='Mobile'  class='form-control input-md'></td>");

                    $('#tab_logic').append('<tr id="addr' + (i + 1) + '"></tr>');
                    i++;
                });
                $("#delete_row").click(function () {
                    if (i > 1) {
                        $("#addr" + (i - 1)).html('');
                        i--;
                    }
                });

            });

        </script>        
        <!-- Intro Header -->
        <header>
            <div class="divDataTable">
                <div class="page-header">
                    <br>
                    <h1></h1>
                </div>
                <div class="col-md-6">
                    With our portfolio of over 100 APIs and a growing collection 
                    , you can incorporate functions across the entire travel journey 
                    ? from dreaming, to planning, to purchasing and servicing trips.
                </div>                

        </header>
        <!-- About Section -->
        <section id="new" class="content-section">

            <div class="divDataForm">
                <form role="form" method="POST" >
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="{api.nombre}" id="nombre" >
                    </div>
                    <div class="form-group">
                        <label for="funcion">Función</label>
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                Dropdown
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Dropdown link</a></li>
                                <li><a href="#">Dropdown link</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Descripción</label>
                        <textarea class="form-control" rows="3" placeholder="Descripción"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="descripcion">Descripción</label>
                        <div class="row clearfix">
                            <div class="col-md-12 column">
                                <table class="table table-bordered table-hover" id="tab_logic">
                                    <thead>
                                        <tr >
                                            <th class="text-center">
                                                #
                                            </th>
                                            <th class="text-center">
                                                Version
                                            </th>
                                            <th class="text-center">
                                                Nombre
                                            </th>
                                            <th class="text-center">
                                                Valor
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr id='addr0'>
                                            <td>
                                                1
                                            </td>
                                            <td>
                                                <input type="text" name='name0'  placeholder='Version' class="form-control"/>
                                            </td>
                                            <td>
                                                <input type="text" name='mail0' placeholder='Nombre' class="form-control"/>
                                            </td>
                                            <td>
                                                <input type="text" name='mobile0' placeholder='Valor' class="form-control"/>
                                            </td>
                                        </tr>
                                        <tr id='addr1'></tr>
                                    </tbody>
                                </table>
                            </div>
                            <a id="add_row" class="btn btn-default pull-left">Add Row</a><a id='delete_row' class="pull-right btn btn-default">Delete Row</a>
                        </div>

                    </div>

                    <div class="funcion-group">
                        <label for="ejemplo_archivo_1">Adjuntar un archivo</label>
                        <input type="file" id="ejemplo_archivo_1">
                        <p class="help-block">Ejemplo de texto de ayuda.</p>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Activa esta casilla
                        </label>
                    </div>
                    <button type="submit" class="btn btn-default">Enviar</button>
                </form>
            </div>        
        </section>
    </tiles:putAttribute>
</tiles:insertDefinition>