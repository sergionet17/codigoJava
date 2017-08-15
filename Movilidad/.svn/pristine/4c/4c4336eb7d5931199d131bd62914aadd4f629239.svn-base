<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div class="col-md-9">
<#if vehiculos??>
    <div class="panel panel-info">
    <!-- Default panel contents -->
        <div class="panel-heading">Consulta de Vehículos</div>
        <div class="panel-body">
        </div>

        <!-- Table -->
        <div class="table-responsive">
        <table class="table table-bordered">
        <thead>
            <tr>
                <th>Placa</th>
                <th>Estado</th>
                <th>Tipo</th>
                <th>Clase</th>
                <th>Propietario</th>
            </tr>
        </thead>
        <tbody>
            <#list vehiculos as vehiculo>
            <tr>
                <td><a href="<@s.url '/vehiculo/general/${vehiculo.id}'  />"">${vehiculo.placaVehiculo}</a></td>
                <td>${(vehiculo.estado.nombre)!""}</td>
                <td>${(vehiculo.tipo.nombre)!""}</td>
                <td>${(vehiculo.clase.nombre)!""}</td>
                <td>${(vehiculo.propietario.primerNombre)!""}&nbsp${(vehiculo.propietario.segundoNombre)!""}&nbsp${(vehiculo.propietario.primerApellido)!""}</td>
            </tr>
            </#list>
        </tbody>
    </table>
    </div>
    </div>
<#else>
    <div class="jumbotron">

        <#if error??>
            <div class="has-error">
                <span  class="help-block">${error}</span>
            </div>
        </#if>
        <#if sinresultados??>
        <h1>Buscar Vehiculo</h1>
        <p>Sipa no encontró coincidencias de acuerdo a los criterios de búsqueda ingresados. Use los criterios de busqueda e intente nuevamente.</p>
        
        <#else>
        <h1>Buscar Vehiculo</h1>
        <p class="lead">Use los criterios para realizar la búsqueda</p>
        </#if>
    </div>
</#if>
</div>
<div class="col-md-3">
    <div class="well sipa-well-search">
        <div class="form-group">
            <@s.bind "vehiculoForm.message"/>
            <div class="has-error">
                <span  class="help-block"><@s.showErrors "<br>"/></span>
            </div>
            <div>
                <h3>Criterios de búsqueda</h3>
                <p>Use los criterios de búsqueda para encontrar el vehículo   que está buscando</p>
                <form action="<@s.url '/vehiculo/consulta/buscar'/>" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                <div class="form-group">
                    <label for="numero">Placa vehículo</label>
                    <@s.bind "vehiculoForm.placaVehiculo"/>
                    <input type="text" name="${s.status.expression}" value="${(vehiculoForm.placaVehiculo)!""}" class="form-control" id="placaVehiculo">
                    <div class="has-error">
                        <span class="help-block"><@s.showErrors "<br>"/></span>
                    </div>
                </div>
                <div class="form-group">
                    <h4><label for="tipo-documento">Propietario del Vehículo</label></h4>
                    <label for="tipo-documento">Tipo documento identidad</label>
                    <@s.bind "vehiculoForm.tipoDocumento"/>
                    <select class="form-control" id="${s.status.expression}" name="${s.status.expression}" >
                        <#if tipoDocumento??>
                            <option value="none">Seleccione una opción</option>
                            <#list tipoDocumento as td>
                                <#if td.sigla?string == ((vehiculoForm.tipoDocumento)!"none")?string >
                                    <option value="${td.sigla}" selected="selected">${td.nombre}</option>
                                <#else>
                                    <option value="${td.sigla}">${td.nombre}</option>
                                </#if>
                            </#list>
                        </#if>
                    </select>
                    <div class="has-error">
                        <span class="help-block"><@s.showErrors "<br>"/></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="documentoIdentidad">Documento de identidad</label>
                    <@s.bind "vehiculoForm.documentoIdentidad"/>
                    <input type="text" class="form-control" id="documentoIdentidad" name="${s.status.expression}" value="${(vehiculoForm.documentoIdentidad)!""}">
                    <div class="has-error">
                        <span class="help-block"><@s.showErrors "<br>"/></span>
                    </div>
                </div>
                <button class="btn btn-primary">Buscar</button>
            </form>
        </div>
    </div>
 </@l.main>