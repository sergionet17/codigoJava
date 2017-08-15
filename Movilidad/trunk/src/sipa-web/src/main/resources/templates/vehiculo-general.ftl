<#import '/vehiculo-layout.ftl' as vehi />
<@vehi.vehiculolayout>
<div>&nbsp;</div>
<#if vehiculo??>
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
                    <th>Placa</th>
                    <td>${vehiculo.placaVehiculo}</td>
                </tr>
                <tr>
                    <th>Estado</th>
                    <td>${(vehiculo.estado.nombre)!""}</td>
                </tr>
                <tr>
                    <th>Clase</th>
                    <td>${vehiculo.clase.nombre!""}</td>
                </tr>
                <tr>
                    <th>Propietario</th>
                    <td><a href="<@s.url '/persona/general/${(vehiculo.propietario.id)!""}'  />"">${vehiculo.propietario.primerNombre!""}&nbsp ${vehiculo.propietario.segundoNombre!""}&nbsp ${vehiculo.propietario.primerApellido!""}&nbsp ${vehiculo.propietario.segundorApellido!""}</a></td>
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
</@vehi.vehiculolayout>