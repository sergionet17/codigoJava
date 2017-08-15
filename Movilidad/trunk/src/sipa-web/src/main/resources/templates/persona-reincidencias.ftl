<#import '/persona-layout.ftl' as per />
<@per.personalayout>
<div>&nbsp;</div>
<#if reincidencias??>
    <div class="panel panel-info">
    <!-- Default panel contents -->
        <div class="panel-heading">
            <h5></h5>
        </div>
        <div class="panel-body">
        </div>
        <!-- Table -->
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Fecha detección </th>
                        <th>Fallo</th>
                        <th>Expediente</th>
                    </tr>
                </thead>
                <tbody>
                    <#list reincidencias as r>
                    <tr>
                        <td><@l.dateFormat r.fecha /></td>
                        <td>${(comparendo.fechaImposicion)!""}</td>
                        <td><a href="<@s.url '/documentos/${r.documento.id}' />"><i class="glyphicon glyphicon-file"></i> Ver documento</a></td>
                        <td><a href="<@s.url '/documentos/${r.documento.id}' />"><i class="glyphicon glyphicon-file"></i> Ver documento</a></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
<#else>
    <div class="jumbotron">
      <p>No se encontraron reincidencias asociadas.</p>
    </div>
</div>
</#if>
</@per.personalayout>