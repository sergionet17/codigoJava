<#import '/comparendo-layout.ftl' as comp />
<@comp.comparendolayout>
<div>&nbsp;</div>
<#if comparendo??>
<div class="well sipa-well">
    <div class="panel panel-info">
        <!-- Default panel contents -->
        <div class="panel-heading">
            <h4>Información general</h4>
        </div>
        <div class="panel-body"></div>
        <!-- Table -->
        <div class="table-responsive">
            <table class="table table-hover table-condensed table-bordered" >
                <tbody>
                    <tr class="sipa-tr">
                        <th>Número</th>
                        <td>${comparendo.numero.valor}</td>
                    </tr>
                    <tr>
                        <th>Fecha imposición</th>
                        <td><@l.dateFormat comparendo.fechaImposicion /></td>
                    </tr>
                    <tr>
                        <th>Fecha notificación</th>
                        <td><@l.dateFormat (comparendo.fechaNotificacion)!'' /></td>
                    </tr>
                    <tr>
                        <th>Nombre</th>
                        <td><a href="<@s.url '/persona/general/${(comparendo.infractor.id)!""}'/>">${(comparendo.infractor.primerNombre)!''}&nbsp ${(comparendo.infractor.segundoNombreNombre)!''} ${(comparendo.infractor.primerApellido)!''}&nbsp ${(comparendo.infractor.segundoApellido)!''}</a></td>
                    </tr>
                    <tr>
                        <th>Documento de identificación</th>
                        <td>${(comparendo.infractor.numeroDocumentoIdentidad)!''}</td>
                    </tr>
                    <tr>
                        <th>Tipo de comparendo</th>
                        <td>${(comparendo.tipoComparendo.nombre)!''}</td>
                    </tr>
                    <tr>
                        <th>Estado del proceso</th>
                        <td>${(comparendo.estado.nombre)!''}</td>
                    </tr>
                    <tr>
                        <th>Decisión</th>
                        <td>${(comparendo.estado.decision)!'--'}</td>
                    </tr>
                    <tr>
                        <th>Placa vehículo</th>
                        <td><a href="<@s.url '/vehiculo/general/${(comparendo.vehiculo.placaVehiculo)!"    "}' />">${(comparendo.vehiculo.placaVehiculo)!''}</a></td>
                    </tr>
                    <tr>
                        <th>Concepto</th>
                        <td>D12 - Cambio de servicio</td>
                    </tr>
                    <tr>
                        <th>Valor nominal</th>
                        <td><@l.currencyFormat (comparendo.valorNominal)!0 /></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    </div>
<#else>
<div class="jumbotron">
    <h1>Consulta de Comparendo</h1>
    <p class="lead">En este momento no fue posible cargar la consulta. Por favor intente más tarde.</p>       
</div> 
</#if>
</@comp.comparendolayout>