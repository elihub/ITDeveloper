<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<script src="<c:url value="/resources/js/formValidation.min.js" />"></script>
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
                username: {
                    validators: {
                        notEmpty: {
                            message: 'The username is required'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: 'The password is required'
                        }
                    }
                }
            }
        });
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
            <p class="text-center">
                <button class="btn btn-default" data-toggle="modal" data-target="#loginModal">Login</button>
            </p>

            <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Login</h4>
                        </div>

                        <div class="modal-body">
                            <!-- The form is placed inside the body of modal -->
                            <form id="loginForm" method="post" class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Username</label>
                                    <div class="col-xs-5">
                                        <input type="text" class="form-control" name="username" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3 control-label">Password</label>
                                    <div class="col-xs-5">
                                        <input type="password" class="form-control" name="password" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-5 col-xs-offset-3">
                                        <button type="submit" class="btn btn-default">Login</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <p>${api.descripcion}</p>
            <p>${api.resumen}</p>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </section>
        <section id="downloads">
            <h2>DESCARGAS</h2>
            <c:forEach items="${api.versiones}" var="version" >
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="2">v${version.version}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${version.resources}" var="resource" >
                            <tr>
                                <td>${version.version} - ${resource.nombreResource}</td>
                                <td><a href=""><img src="../../resources/images/download2.png"/></a></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
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
