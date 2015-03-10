<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="<c:url value="/resources/js/formValidation.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.formValidation.js" />"></script>
<script>
    $(document).ready(function () {
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

        $('#loginForm').on('success.form.fv', function (e) {
            // Prevent form submission
            /*e.preventDefault();
             
             var validator = $(e.target).data('formValidation');
             $('#loginModal')
             .one('hidden.bs.modal', function () {
             $('#welcomeModal')
             .find('.nombre')
             .html(validator.getFieldElements('nombre').val()).end()
             .modal('show');
             })
             .modal('hide');
             */
        });

        $('#loginModal').on('shown.bs.modal', function () {
            /*$('.textArea').val('');*/
            $('#loginForm').find('[name="nombre"]').focus();
        });
        
        $('.form-horizontal').formValidation();
    });
</script>
<div class="row" style="margin-left: 10px">
    <div class="span3 bs-docs-sidebar" id="sidebar">
        <ul id="mynav" class="nav nav-list bs-docs-sidenav affix" data-spy="affix">
            <li><a href="#summary" ><i class="icon-chevron-right"></i> Resumen</a></li>
            <li><a href="#downloads" ><i class="icon-chevron-right"></i> Descargas</a></li>
            <li><a href="#documentation" > <i class="icon-chevron-right"></i> Documentacion</a></li>
            <li><a href="#technology" ><i class="icon-chevron-right"></i> Tecnología</a></li>
        </ul>
    </div>
    <div class="span9" id="mycontent">
        <section id="summary">
            <h2>${api.nombre}</h2>
            <p>${api.descripcion}</p>
            <p>${api.resumen}</p>
            <p class="text-center">
                <button class="btn btn-default" data-toggle="modal" data-target="#loginModal">Editar Datos</button>
            </p>

            <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Editar Api</h4>
                        </div>

                        <div class="modal-body">
                            <!-- The form is placed inside the body of modal -->
                            <form:form id="loginForm" method="post" class="form-horizontal" commandname="api">
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Nombre</label>
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control" name="nombre" value="${api.nombre}" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Descripcion</label>
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control" name="descripcion" value="${api.descripcion}" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Resumen</label>
                                    <div class="col-xs-9">
                                        <textarea cols="30" rows="10" class="form-control textArea" name="resumen">${api.resumen}</textarea>
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

            <div class="modal fade" id="welcomeModal" tabindex="-1" role="dialog" aria-labelledby="Welcome back" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <h4>Welcome back, <span class="username"></span></h4>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </section>
        <section id="downloads">
            <h2>DESCARGAS</h2>
            <c:forEach items="${api.versiones}" var="version" varStatus="indVersion">
                <c:set var="totalVersiones" value="${indVersion.count}" />
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="4">${indVersion.index} - v${version.version}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${version.resources}" var="resource" varStatus="indResources">
                            <c:set var="totalResources" value="${indResources.count}" />
                            <tr>
                                <td>${version.version} - ${resource.nombreResource}</td>
                                <td><a href=""><img src="<c:url value="/resources/images/download2.png" />"/></a></td>
                                <td style="width: 30px"><a href="" class="edit"><img src="<c:url value="/resources/images/edit.png" />"></a></td>
                                <td style="width: 30px"><a href="<c:url value="/apis/view/downloads?version=${indVersion.index}&resource=${indResources.index}" />" class="remove"><img src="<c:url value="/resources/images/remove.png" />"></a></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p class="text-right">
                    <button class="btn btn-default" data-toggle="modal" data-target="#downloadModal${indVersion.index}">Agregar Archivo</button>
                </p>
                <div class="modal fade" id="downloadModal${indVersion.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Agregar Archivo</h4>
                            </div>

                            <div class="modal-body">
                                <!-- The form is placed inside the body of modal -->
                                <form:form id="form" method="post" class="form-horizontal" modelAttribute="api" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label class="col-xs-3 control-label">Nombre</label>
                                        <div class="col-xs-9">
                                            <input type="text" class="form-control" name="versiones[${indVersion.index}].resources[${totalResources}].nombreResource" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-xs-3 control-label">Archivo</label>
                                        <div class="col-xs-9">
                                            <input type="file" name="versiones[${indVersion.index}].resources[${totalResources}].files" required/>
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
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </section>
        <section id="documentation">
            <h2>DOCUMENTACION</h2>
            <c:forEach items="${api.docs}" var="doc" >
                ${doc.nombreDoc}-${doc.resumenDoc} <br />
            </c:forEach>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </section>
        <section id="technology">
            <h2>Tecnologias</h2>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </section>
    </div>
</div>
