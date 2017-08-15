<#import '/comparendo-layout.ftl' as comp />
<@comp.comparendolayout>
    <!-- Table -->
    <div class="table-responsive">
        <table class="table table-hover table-condensed table-bordered">
        <thead>
        <tr>
            <th>Tipo</th>
            <!--th>Número</th-->
            <th>Fecha</th>
            <th>Contenido</th>
        </tr>
        </thead>
        <tbody>
        <#list relaciones as x>
            <tr>
                <td>${x.documento.tipoDocumental.nombre}</td>
                <!--td>${x.documento.id}</td-->
                <td>${x.documento.fechaCreacion}</td>
                <td><a href="<@s.url '/documentos/${x.documento.id}' />"><i class="glyphicon glyphicon-file"></i> Ver documento</a></td>
            </tr>
        </#list>
        </tbody>
        </table>
    </div>
</@comp.comparendolayout>