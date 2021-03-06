<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="<c:url value="/resources/js/formValidation.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.formValidation.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-tooltip.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-confirmation.js" />"></script>
<script>
    $(document).ready(function () {
        //$('[data-toggle="confirmation"]').confirmation();
        $('[data-toggle="confirmation-singleton"]').confirmation({singleton:true});
        //$('[data-toggle="confirmation-popout"]').confirmation({popout: true});
       
        //*MENU navBar lado izquierdo
        $('body').scrollspy({target: '#sidebar', offset: 80});
        var clicked = false;
        $('#mynav li a').click(
                function () {
                    $('#mycontent > section > h2').css('padding-top', 0);
                    $($(this).attr('href') + ' > h2').css('padding-top', '50px');
                    clicked = true;
                }
        );

        $('body').on('activate.bs.scrollspy', function () {
            console.log('scrolling...');
            if (!clicked)
                $('#mycontent > section > h2').css('padding-top', 0);
            clicked = false;
        });


        $('#loginForm').formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                nombre: {
                    validators: {
                        notEmpty: {
                            message: 'El nombre es obligatorio'
                        }
                    }
                }/*,
                 version: {
                 decimal:true,
                 validators: {
                 notEmpty: {
                 message: 'La version es obligatoria'
                 },
                 numeric:{
                 message:'El valor debe ser un numero'
                 }
                 }
                 }*/,
                descripcion: {
                    validators: {
                        notEmpty: {
                            message: 'La descripcion es obligatorio'
                        }
                    }
                }
            }
        });
        $('#loginModal').on('shown.bs.modal', function () {
            /*$('.textArea').val('');*/
            $('#loginForm').find('[name="nombre"]').focus();
        });
        
        $('.form-horizontal').formValidation();

        $('.editResource').bind('click', function (e) {
            e.preventDefault();
            var td1 = $(this).parent().parent().find('td');
            var link = td1.find('a').attr('href');

            var url = ($(this).attr('href'));
            var cat = getURLParameter(url, 'version');
            var typ = getURLParameter(url, 'resource');

            $('#editDownloadModal')
                    .find('.nombre')
                    .prop('name', 'versiones[' + cat + '].resources[' + typ + '].nombreResource').val(td1.text()).end()
                    .find('.dir')
                    .prop('name', 'versiones[' + cat + '].resources[' + typ + '].dirResource').val(link).end()
                    .modal('show');
            $('.form-horizontal').formValidation();
        });
        
        $('.editDocs').bind('click', function (e) {
            e.preventDefault();
            var td1 = $(this).parent().parent().find('td').text();
            var resumen = $(this).closest('tr').next('tr').find('td').text();
            var url = ($(this).attr('href'));
            var docId = getURLParameter(url, 'docs');

            $('#editDocsModal')
                    .find('.nombre')
                    .prop('name', 'docs[' + docId + '].nombreDoc').val(td1).end()
                    .find('.resumen')
                    .prop('name', 'docs[' + docId + '].resumenDoc').val(resumen.trim()).end()
                    .find('.file')
                    .prop('name', 'docs[' + docId + '].filesDocs').removeAttr('required').end()
                    .modal('show');
            $('.form-horizontal').formValidation();
        });
        function getURLParameter(url, name) {
            return (RegExp(name + '=' + '(.+?)(&|$)').exec(url) || [, null])[1];
        };
        
        var i=1; 
        $('#newVersionForm').formValidation()
        // Add button click handler
        .on('click', '.addButton', function() {
            var $template = $('#optionTemplate'),
                $clone    = $template
                                .clone()
                                .removeClass('hide')
                                .removeAttr('id')
                                .attr('id','resource'+i)
                                .insertBefore($template),
                $option = $clone.find('.nombre');
                var indVersion = $option.val();
                var nuevoNombre = 'versiones[' + indVersion + '].resources['+i+'].nombreResource';
                $option.prop('name', nuevoNombre);
                $option.val('');
                
                $option2 = $clone.find('.dir');
                var nuevoFile = 'versiones[' + indVersion + '].resources['+i+'].dirResource';
                $option2.prop('name', nuevoFile);
                
            // Add new field
            $('#newVersionForm').formValidation('addField', $option);
            $('#newVersionForm').formValidation('addField', $option2);
            i++;
        })
        .on('click', '.removeButton', function() {
            if (i > 1) {
                $("#resource" + (i - 1)).remove();
                i--;
            }
        })
        ;
        
    });

</script>
<div class="row" style="margin-left: 10px">
    <div class="span3 bs-docs-sidebar" id="sidebar">
        <ul id="mynav" class="nav nav-list bs-docs-sidenav affix" data-spy="affix">
            <li><a href="#summary" ><i class="icon-chevron-right"></i> Resumen</a></li>
            <li><a href="#downloads" ><i class="icon-chevron-right"></i> Descargas</a></li>
            <li><a href="#documentation" > <i class="icon-chevron-right"></i> Documentacion</a></li>
            <li><a href="#technology" ><i class="icon-chevron-right"></i> Tecnolog�a</a></li>
        </ul>
    </div>
    <div class="span9" id="mycontent">
        <section id="summary">
            <h2>${soap.nombre}</h2>
            <p>${soap.descripcion}</p>
            <p>${soap.resumen}</p>
            <p>${soap.area.nombre}</p>
            <p>${soap.funcion.nombre}</p>
            <p class="text-center">
                <button class="btn btn-default" data-toggle="modal" data-target="#loginModal">Editar Datos</button>
            </p>

            <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Editar Servicio</h4>
                        </div>

                        <div class="modal-body">
                            <!-- The form is placed inside the body of modal -->
                            <form:form id="loginForm" method="post" class="form-horizontal" modelAttribute="soap">
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Nombre</label>
                                    <div class="col-xs-9">
                                        <input type="hidden" name="general" value="true" />
                                        <input type="text" class="form-control" name="nombre" value="${soap.nombre}" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Descripcion</label>
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control" name="descripcion" value="${soap.descripcion}" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Resumen</label>
                                    <div class="col-xs-9">
                                        <textarea cols="30" rows="10" class="form-control textArea" name="resumen">${soap.resumen}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">�rea</label>
                                    <div class="col-xs-9">
                                        <select name="soapArea" class="form-control">
                                            <c:forEach items="${catArea}" var="itemArea">
                                                <option value="${itemArea.id}">${itemArea.nombre}</option>
                                            </c:forEach>                            
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Funci�n</label>
                                    <div class="col-xs-9">
                                        <select class="form-control" name="soapFuncion">
                                            <c:forEach items="${catFuncion}" var="itemFuncion">
                                                <option value="${itemFuncion.id}" label="${itemFuncion.nombre}">                                
                                            </c:forEach>
                                        </select>    
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-5 col-xs-offset-5">
                                        <button type="submit" class="btn btn-default">Aceptar</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>

            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </section>
        <section id="downloads">
            <h2>DESCARGAS</h2>
            
            <c:forEach items="${soap.versiones}" var="version" varStatus="indVersion">
                <c:set var="totalVersiones" value="${indVersion.count}" />
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="1">${indVersion.index} - v${version.version}</th>
                            <th colspan="3" class="text-right"><a data-href="<c:url value="/servicios/soap/delVersion?version=${indVersion.index}" />" data-toggle="confirmation-singleton" data-placement="top" title="�Est�s seguro de eliminar esta versi�n?" class="remove">Eliminar Version</a></th>
                        </tr>
                        
                    </thead>
                    <tbody>
                        <c:forEach items="${version.resources}" var="resource" varStatus="indResources">
                            <c:set var="totalResources" value="${indResources.count}" />
                            <tr>
                                <td>${resource.nombreResource}</td>
                                <td><a target="_blank" href="${resource.dirResource}"><img src="<c:url value="/resources/images/download2.png" />"/></a></td>
                                <td style="width: 30px"><a href="?version=${indVersion.index}&resource=${indResources.index}" class="editResource"><img src="<c:url value="/resources/images/edit.png" />"></a></td>
                                <td style="width: 30px"><a data-href="<c:url value="/servicios/soap/delDownloads?version=${indVersion.index}&resource=${indResources.index}" />" class="remove" data-toggle="confirmation-singleton" data-placement="top" title="�Est�s seguro de eliminar este registro?"><img src="<c:url value="/resources/images/remove.png" />"></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p class="text-right">
                    <button class="btn btn-default" data-toggle="modal" data-target="#newDownloadModal${indVersion.index}">Agregar Archivo</button>
                </p>
                <div class="modal fade modalResource" id="newDownloadModal${indVersion.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Agregar Archivo</h4>
                            </div>

                            <div class="modal-body">
                                <!-- The form is placed inside the body of modal -->
                                <form:form method="post" class="form-horizontal" modelAttribute="soap" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <input type="hidden" name="resource" value="true" />
                                        <label class="col-xs-3 control-label">Nombre</label>
                                        <div class="col-xs-9">
                                            <input type="text" class="form-control nombre" name="versiones[${indVersion.index}].resources[${totalResources + 0}].nombreResource" required />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-xs-3 control-label">URL</label>
                                        <div class="col-xs-9">
                                            <input type="url" class="form-control dir" name="versiones[${indVersion.index}].resources[${totalResources + 0}].dirResource" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-5 col-xs-offset-5">
                                            <button type="submit" class="btn btn-default">Aceptar</button>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="modal fade modalResource" id="editDownloadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Editar Archivo</h4>
                        </div>

                        <div class="modal-body">
                            <!-- The form is placed inside the body of modal -->
                            <form:form method="post" class="form-horizontal" modelAttribute="soap" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="hidden" name="resource" value="true" />
                                    <label class="col-xs-3 control-label">Nombre</label>
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control nombre" name="nombreResource" required />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Url</label>
                                    <div class="col-xs-9">
                                        <input type="url" class="form-control dir" name="dirResource" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-5 col-xs-offset-5">
                                        <button type="submit" class="btn btn-default">Aceptar</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
                
            <p class="text-left">
                <button class="btn btn-default" data-toggle="modal" data-target="#newVersionModal">Agregar Version</button>
            </p>    
            <div class="modal fade bs-example-modal-lg" id="newVersionModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Nueva Version</h4>
                        </div>

                        <div class="modal-body">
                            <!-- The form is placed inside the body of modal -->
                            <form:form method="post" id="newVersionForm" class="form-horizontal" modelAttribute="soap" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="hidden" name="resource" value="true" />
                                    <label class="col-xs-5 control-label">Version</label>
                                    <div class="col-xs-3">
                                        <input type="text" class="form-control nombre" name="versiones[${totalVersiones}].version" required />
                                    </div>
                                </div>
                                <hr />
                                <div class="form-group">
                                    <label class="col-xs-2 control-label">Nombre:</label>
                                    <div class="col-xs-3">
                                        <input type="text" class="form-control nombre" name="versiones[${totalVersiones}].resources[0].nombreResource" required />
                                    </div>
                                    <label class="col-xs-1 control-label">Url</label>
                                    <div class="col-xs-3">
                                        <input type="url" class="form-control dir" name="versiones[${totalVersiones}].resources[0].dirResource" required/>
                                    </div>
                                </div>
                                <div class="form-group hide" id="optionTemplate">
                                    <label class="col-xs-2 control-label">Nombre:</label>
                                    <div class="col-xs-3">
                                        <input type="text" class="form-control nombre" name="nombreTemplate" required value="${totalVersiones}"/>
                                    </div>
                                    <label class="col-xs-1 control-label">Url</label>
                                    <div class="col-xs-3">
                                        <input type="url" class="form-control dir" name="fileTemplate" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <button type="button" class="btn btn-default addButton"><i class="fa fa-plus">+</i></button>
                                    <button type="button" class="btn btn-default removeButton"><i class="fa fa-plus">-</i></button>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-5 col-xs-offset-5">
                                        <button type="submit" class="btn btn-default">Aceptar</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </section>
        <section id="documentation">
            <h2>DOCUMENTACION</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th colspan="4"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${soap.docs}" var="doc" varStatus="ind">
                        <c:set var="totalVersionesDocs" value="${ind.count}" />
                        <tr>
                            <td>${doc.nombreDoc}</td>
                            <td><a href="<c:url value="/servicios/soap/downloadDocs?version=${ind.index}" />"><img src="<c:url value="/resources/images/download2.png" />"/></a></td>
                            <td style="width: 30px"><a href="?docs=${ind.index}" class="editDocs"><img src="<c:url value="/resources/images/edit.png" />"></a></td>
                            <td style="width: 30px"><a data-href="<c:url value="/servicios/soap/delDocs?doc=${ind.index}" />" class="remove" data-toggle="confirmation-singleton" data-placement="top" title="�Est�s seguro de eliminar este registro?"><img src="<c:url value="/resources/images/remove.png" />"></a></td>
                        </tr>
                        <tr>
                            <td colspan="4" style="border-top:none">
                                <pre class="pre">${doc.resumenDoc}</pre>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p class="text-right">
                <button class="btn btn-default" data-toggle="modal" data-target="#newDocsModal">Agregar Archivo</button>
            </p>
            <div class="modal fade modalResource" id="newDocsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Agregar Archivo</h4>
                        </div>

                        <div class="modal-body">
                            <!-- The form is placed inside the body of modal -->
                            <form:form method="post" class="form-horizontal" modelAttribute="soap" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="hidden" name="documentos" value="true" />
                                    <label class="col-xs-3 control-label">Nombre</label>
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control nombre" name="docs[${totalVersionesDocs}].nombreDoc" required />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Resumen</label>
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control resumen" name="docs[${totalVersionesDocs}].resumenDoc" required />
                                    </div>
                                </div>    
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Archivo</label>
                                    <div class="col-xs-9">
                                        <input type="file" class="file" name="docs[${totalVersionesDocs}].filesDocs" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-5 col-xs-offset-5">
                                        <button type="submit" class="btn btn-default">Aceptar</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade modalResource" id="editDocsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Editar Archivo</h4>
                        </div>
                        <div class="modal-body">
                            <form:form method="post" class="form-horizontal" modelAttribute="soap" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="hidden" name="documentos" value="true" />
                                    <label class="col-xs-3 control-label">Nombre</label>
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control nombre" name="nombreDocs" required />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Resumen</label>
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control resumen" name="resumenDocs" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Archivo</label>
                                    <div class="col-xs-9">
                                        <input type="file" class="file" name="filesDocs"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-5 col-xs-offset-5">
                                        <button type="submit" class="btn btn-default">Aceptar</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </section>
        <section id="technology">
            <h2>TECNOLOGIAS</h2>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </section>
    </div>
</div>