<#import 'comparendo-layout.ftl' as comp />
<@comp.comparendolayout>
    <#if audiencias?? && audiencias?size != 0>
        <table class="table table-hover table-condensed table-bordered">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Abogado</th>
                    <th>Asistente</th>
                    <th>Tipo</th>
                    <th>Fallo</th>
                    <th>Acto</th>
                </tr>
            </thead>
            <tbody>
            <#list audiencias as x>
                <tr>
                    <td>${x.fecha}</td>
                    <td>${x.abogado.persona.obtenerNombreCompleto()}</td>
                    <td>
                        <#if x.apoderado??>
                            ${x.apoderado.obtenerNombreCompleto()}
                        <#else>
                            ${(x.comparendo.infractor.obtenerNombreCompleto())!"<Sin infractor>"}
                        </#if>
                    </td>
                    <td>${x.tipo.nombre}</td>
                    <td>${x.fallo.nombre}</td>
                    <td><a href="<@s.url '/documentos/${x.documento.id}' />"><i class="glyphicon glyphicon-file"></i> Ver documento</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <div class="jumbotron">
            <h1>Apertura de audiencia</h1>
            <p>Este comparendo no cuenta con alguna audiencia. Use los siguientes botones para dar apertura a una audiencia dependiendo de su naturaleza.</p>
            <p>
                <a href="<@s.url controllerStatics.CONTROLLER_PATH + '/${comparendo.id}/audiencia-aceptacion' />" class="btn btn-md btn-success sipa-bottom-10">Audiencia de aceptación</a>
                <a href="<@s.url controllerStatics.CONTROLLER_PATH + '/${comparendo.id}/audiencia-impugnacion-decision' />" class="btn btn-md btn-danger sipa-bottom-10">Audiencia de impugnación</a>
            </p>
        </div>
    </#if>
</@comp.comparendolayout>   