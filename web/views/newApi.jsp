<%-- 
    Document   : altaEditApi
    Created on : 4/03/2015, 04:39:43 PM
    Author     : Elida Carrillo
--%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Custom styles -->
<!--<link href="<%--<c:url value="/resources/dist/css/prettify.css" />"--%> rel="stylesheet">-->
<link href="<c:url value="/resources/css/form-wizard-and-validation.css" />" rel="stylesheet">
<script src="<c:url value="/resources/dist/js/jquery.bootstrap.wizard.js" />"></script>
<!--<script src="<%--<c:url value="/resources/dist/js/jquery.bootstrap.wizard.min.js" />">--%></script>-->
<!--<script src="<%--<c:url value="/resources/js/formValidation.min.js" />">--%></script>-->
<!--<script src="<%--<c:url value="/resources/js/jquery.validate.js" />">--%></script>-->
<%--<script src="<c:url value="/resources/js/bootstrap.formValidation.js" />"></script>--%>



<script>
    $(document).ready(function () {
        /***********************Dinamic rows tab1**************************/

        var i = 1;
        $("#add_row").click(function () {
            $('#addr' + i).html("<td>" + (i + 1) + "</td>" +
                    "<td><input name='nombreResources' type='text' placeholder='Nombre' class='form-control input-md'  /> </td>"
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



    });</script>        

<div class='container'  style="width: 70%;padding-left: 20%">

    <section id="wizard">
        <div class="page-header">
            <h3>Alta de API</h3>
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
        </div>
    </div>
    <form role="form">
        <div class="row setup-content" id="step-1">
            <div class="col-xs-12">
                <div class="col-md-12">
                    <h3> Step 1</h3>
                    <label  class="control-label" for="nombre" >Nombre:</label>
                    <input type="text" value="${api.nombre}" class="form-control" id="nombre" name="nombre" placeholder="Nombre Api" required="required" >
                    <label for="version">Versión:</label>
                    <input type="text" value="${apiVersion.version}"  class="form-control" id="version" name="version"  placeholder="Versión" required="required">
                    <label for="descripcion">Descripción:</label>
                    <input type="text"  value="${api.descripcion}" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción" required="required">
                    <label for="resumen">Resumen:</label>
                    <textarea value="${api.resumen}" class="form-control" rows="3" name="resumen"></textarea>
                    <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
                </div>
            </div>
        </div>
        <div class="row setup-content" id="step-2">
            <div class="col-xs-12">
                <div class="col-md-12">
                    <h3> Step 2</h3>
                    <div class="form-group">
                        <label class="control-label">First Name</label>
                        <input  maxlength="100" type="text" required="required" class="form-control" placeholder="Enter First Name"  />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Last Name</label>
                        <input maxlength="100" type="text" required="required" class="form-control" placeholder="Enter Last Name" />
                    </div>
                    <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
                </div>
            </div>
        </div>
        <div class="row setup-content" id="step-3">
            <div class="col-xs-12">
                <div class="col-md-12">
                    <h3> Step 3</h3>
                    <div class="form-group">
                        <label class="control-label">Company Name</label>
                        <input maxlength="200" type="text" required="required" class="form-control" placeholder="Enter Company Name" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Company Address</label>
                        <input maxlength="200" type="text" required="required" class="form-control" placeholder="Enter Company Address"  />
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
    </form>
</div>

