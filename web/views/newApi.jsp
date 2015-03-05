<%-- 
    Document   : altaEditApi
    Created on : 4/03/2015, 04:39:43 PM
    Author     : Elida Carrillo
--%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Custom styles -->
<link href="<c:url value="/resources/dist/css/prettify.css" />" rel="stylesheet">
<script src="<c:url value="/resources/dist/js/jquery.bootstrap.wizard.js" />" />
<script src="<c:url value="/resources/dist/js/jquery.bootstrap.wizard.min.js" />" />
<script src="<c:url value="/resources/dist/js/prettify.js" />" />

<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        Derecha Soap
    </tiles:putAttribute>
    <tiles:putAttribute name="left">        
        <script>
            $(document).ready(function () {
                /***********************Dinamic rows**************************/
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
                /********************************Wizard steps********************************/
                $('#rootwizard').bootstrapWizard({onNext: function (tab, navigation, index) {
                        if (index == 2) {
                            // Make sure we entered the name
                            if (!$('#name').val()) {
                                alert('You must enter your name');
                                $('#name').focus();
                                return false;
                            }
                        }

                        // Set the name for the next tab
                        $('#tab3').html('Hello, ' + $('#name').val());

                    }, onTabShow: function (tab, navigation, index) {
                        var $total = navigation.find('li').length;
                        var $current = index + 1;
                        var $percent = ($current / $total) * 100;
                        $('#rootwizard').find('.bar').css({width: $percent + '%'});
                    }});

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
                </div>                

        </header>

        <!--<section id="new" class="content-section">-->

        <div id="rootwizard">
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container">
                        <ul>
                            <li><a href="#tab1" data-toggle="tab">First</a></li>
                            <li><a href="#tab2" data-toggle="tab">Second</a></li>
                            <li><a href="#tab3" data-toggle="tab">Third</a></li>
                            <li><a href="#tab4" data-toggle="tab">Forth</a></li>
                            <li><a href="#tab5" data-toggle="tab">Fifth</a></li>
                            <li><a href="#tab6" data-toggle="tab">Sixth</a></li>
                            <li><a href="#tab7" data-toggle="tab">Seventh</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="bar" class="progress progress-striped active">
                <div class="bar"></div>
            </div>
            <div class="tab-content">
                <!-- <form role="form" method="POST" >-->
                <div class="tab-pane" id="tab1">
                    1
                </div>
                <div class="tab-pane" id="tab2">
                    <p>
                        <input type='text' name='name' id='name' placeholder='Enter Your Name'>
                    </p>
                </div>
                <div class="tab-pane" id="tab3">
                    3
                </div>
                <div class="tab-pane" id="tab4">
                    4
                </div>
                <div class="tab-pane" id="tab5">
                    5
                </div>
                <div class="tab-pane" id="tab6">
                    6
                </div>
                <div class="tab-pane" id="tab7">
                    7
                </div>
                <!--</form>-->
                <ul class="pager wizard">
                    <li class="previous first" style="display:none;"><a href="#">First</a></li>
                    <li class="previous"><a href="#">Previous</a></li>
                    <li class="next last" style="display:none;"><a href="#">Last</a></li>
                    <li class="next"><a href="#">Next</a></li>
                </ul>
            </div>	
        </div>







        <!--<form role="form" method="POST" >
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
        </form>-->

        <!-- </section>-->
    </tiles:putAttribute>
</tiles:insertDefinition>