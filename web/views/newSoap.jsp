<%-- 
    Document   : altaEditApi
    Created on : 4/03/2015, 04:39:43 PM
    Author     : Elida Carrillo
--%>
<%--<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Custom styles -->
<link href="<c:url value="/resources/css/form-wizard-and-validation.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/formValidation.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.formValidation.js" />"></script>



<script>
    $(document).ready(function () {
        /***********************Dinamic rows tab1**************************/

        var i = 1;
        $("#add_row").click(function () {
            $('#addr' + i).html("<td></td>" +
                    " <td><div class='form-group'><input type='text' name='nombreResources' id='nombreResource'  placeholder='Nombre Archivo' class='form-control' required='required'/></div></td>"
                    + "<td><input  name='files' type='file' id='files'></td>");
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
                    "<td><input name='nombreDocs' type='text' placeholder='Nombre' class='form-control input-md'  /> </td>"
                    + "<td><textarea class='form-control' rows='3' name='resumenDocs'></textarea></td>"
                    + "<td><input name='filesDocs' type='file' id='resource'></td>");
            $('#tab_logic2').append('<tr id="add' + (n + 1) + '"></tr>');
            n++;
        });
        $("#delete_row2").click(function () {
            if (n > 1) {
                $("#add" + (n - 1)).html('');
                n--;
            }
        });

        /*******************************************************************/

        var navListItems = $('div.setup-panel div a'),
                allWells = $('.setup-content'),
                allNextBtn = $('.nextBtn');

        allWells.hide();

        navListItems.click(function (e) {
            e.preventDefault();
            var $target = $($(this).attr('href')),
                    $item = $(this);

            if (!$item.hasClass('disabled')) {
                navListItems.removeClass('btn-primary').addClass('btn-default');
                $item.addClass('btn-primary');
                allWells.hide();
                $target.show();
                $target.find('input:eq(0)').focus();
            }
        });

        allNextBtn.click(function () {
            var curStep = $(this).closest(".setup-content"),
                    curStepBtn = curStep.attr("id"),
                    nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
                    curInputs = curStep.find("input[type='text'],input[type='url']"),
                    isValid = true;

            $(".form-group").removeClass("has-error");
            for (var i = 0; i < curInputs.length; i++) {
                if (!curInputs[i].validity.valid) {
                    isValid = false;
                    $(curInputs[i]).closest(".form-group").addClass("has-error");
                }
            }

            if (isValid)
                nextStepWizard.removeAttr('disabled').trigger('click');
        });

        $('div.setup-panel div a.btn-primary').trigger('click');

        $('#soapAltaForm').formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                files: {
                    validators: {
                        notEmpty: {
                            message: 'El resource es obligatorio'
                        }
                    }
                },
                version: {
                    decimal: true,
                    validators: {
                        notEmpty: {
                            message: 'La version es obligatoria'
                        },
                        numeric: {
                            message: 'El valor debe ser un numero'
                        }
                    }
                }
            }
        });


    });</script>    

<div class='container'  style="width: 70%;padding-left: 20%">

    <section id="wizard">
        <div class="page-header">
            <h3>Alta de Servicio</h3>
        </div> 
    </section>

    <div class="stepwizard">
        <div class="stepwizard-row setup-panel">
            <div class="stepwizard-step">
                <a href="#step-1" type="button" class="btn btn-primary btn-circle">1</a>
                <p>Step 1</p>
            </div>
            <div class="stepwizard-step">
                <a href="#step-2" type="button" class="btn btn-default btn-circle" disabled="disabled">2</a>
                <p>Step 2</p>
            </div>
            <div class="stepwizard-step">
                <a href="#step-3" type="button" class="btn btn-default btn-circle" disabled="disabled">3</a>
                <p>Step 3</p>
            </div>
            <div class="stepwizard-step">
                <a href="#step-4" type="button" class="btn btn-default btn-circle" disabled="disabled">4</a>
                <p>Step 4</p>
            </div>
        </div>
    </div>
    <form:form role="form" method="POST" enctype="multipart/form-data" id="soapAltaForm" modelAttribute="soap" >
        <div class="row setup-content" id="step-1">
            <div class="col-xs-12">
                <div class="col-md-12">
                    <h3> Datos Generales del Servicio</h3>
                    <div class="form-group">
                        <label  class="control-label" for="nombre" >Nombre:</label>
                        <form:input type="text" path="nombre" class="form-control" id="nombre" name="nombre" placeholder="Nombre Servicio" required="required" />                            
                    </div>                    
                    <div class="form-group">
                        <label class="control-label" for="area">Área:</label>
                        <select name="soapArea" class="form-control" >
                            <c:forEach items="${catArea}" var="itemArea">
                                <option value="${itemArea.id}">${itemArea.nombre}</option>
                            </c:forEach>                            
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="funcion">Función:</label>
                        <select class="form-control" id="funcion" name="soapFuncion">
                            <c:forEach items="${catFuncion}" var="itemFuncion">
                                <option value="${itemFuncion.id}" label="${itemFuncion.nombre}">                                
                                </c:forEach>
                        </select>                       
                    </div>
                    <div class="form-group">
                        <label for="version">Versión:</label>
                        <input type="text" value="1.0"  class="form-control" id="version" name="version"  placeholder="Versión" required="required">
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Descripción:</label>
                        <form:input type="text"  path="descripcion" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción" required="required"/>
                    </div>
                    <div class="form-group">
                        <label for="resumen">Resumen:</label>
                        <form:textarea path="resumen" class="form-control" rows="3" name="resumen"></form:textarea>
                        </div>
                        <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
                    </div>
                </div>
            </div>
            <div class="row setup-content" id="step-2">
                <div class="col-xs-12">
                    <div class="col-md-12">
                        <h3>Resources</h3>                    
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
                                <tr>
                                    <td>
                                        Request Schema
                                    </td>
                                    <td>
                                        <div class="form-group"><input type="text" name='nombreResources' id='nombreResource'  placeholder='Nombre Archivo' class="form-control" required="required"/></div>
                                    </td>
                                    <td>
                                        <div class="form-group"><input type="url" id="files" name="files" class="form-control" required="required" ></div>
                                    </td>                         
                                </tr>
                                <tr>
                                    <td>
                                        Response Schema
                                    </td>
                                    <td>
                                        <div class="form-group"><input type="text" name='nombreResources' id='nombreResource'  placeholder='Nombre Archivo' class="form-control" required="required"/></div>
                                    </td>
                                    <td>
                                        <div class="form-group"><input type="url" id="files" name="files" class="form-control" required="required" ></div>
                                    </td>                         
                                </tr>
                                <tr id='addr0'>
                                    <td>
                                        WSDL
                                    </td>
                                    <td>
                                        <div class="form-group"><input type="text" name='nombreResources' id='nombreResource'  placeholder='Nombre Archivo' class="form-control" required="required"/></div>
                                    </td>
                                    <td>
                                        <div class="form-group"><input type="url" id="files" name="files" class="form-control" required="required" ></div>
                                    </td>                         
                                </tr>
                                <tr id='addr1'></tr>
                            </tbody>                                    
                            <tfoot>
                            <a id="add_row" class="btn btn-default pull-left">Nuevo</a>
                            <a id='delete_row' class="pull-right btn btn-default">Eliminar</a>
                            </tfoot>
                        </table>                    
                        <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
                    </div>
                </div>
            </div>
             <div class="row setup-content" id="step-3">
                 <div class="col-xs-12">
                     <div class="col-md-12">
                         <h3>Documentación</h3>
                         <div class="form-group">
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
                                             <input type="text" name='nombreDocs'  placeholder='Nombre' class="form-control"/>
                                         </td>
                                         <td>
                                             <textarea name ="resumenDocs" class="form-control" rows="3"></textarea>
                                         </td>
                                         <td>
                                             <input type="file" name="filesDocs" id="resource">
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
                         <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
                     </div>
                 </div>
             </div>
            <div class="row setup-content" id="step-4">
                <div class="col-xs-12">
                    <div class="col-md-12">
                        <h3> Step 4</h3>
                        <button class="btn btn-success btn-lg pull-right" type="submit">Finish!</button>
                    </div>
                </div>
            </div>
    </form:form>

</div>


