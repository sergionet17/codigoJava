<#import '/persona-layout.ftl' as per />
<@per.personalayout>
<div>&nbsp;</div>
<#if comparendos??>
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
                        <th># Número Comparendo </th>
                        <th>Fecha imposición</th>
                        <th>Tipo</th>
                        <th>Estado</th>
                        <th>Placa</th>
                        <th>Saldo en cartera</th>
                    </tr>
                </thead>
                <tbody>
                    <#list comparendos as comparendo>
                    <tr>
                        <td><a href="<@s.url '/comparendo/general/${comparendo.id}'  />"">${(comparendo.numero.valor)!"<Sin número>"}</a></td>
                        <td>${(comparendo.fechaImposicion)!""}</td>
                        <td>${(comparendo.tipoComparendo.nombre)!""}</td>
                        <td>${(comparendo.estado.nombre)!""}</td>
                        <td><a href="<@s.url '/vehiculo/general/${(comparendo.vehiculo.placaVehiculo)!""}'  />"">${(comparendo.vehiculo.placaVehiculo)!""}</td>
                        <td class="text-right"><#if comparendo.saldoCartera??>${(comparendo.saldoCartera)}<#else>Error al cargar el saldo</#if></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
<#else>
    <div class="jumbotron">
      <p>Sipa no encontró comparendos asociados.</p>
    </div>
</div>
</#if>
</@per.personalayout>