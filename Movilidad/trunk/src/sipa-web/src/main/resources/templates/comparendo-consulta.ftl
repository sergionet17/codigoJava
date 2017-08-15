<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@l.main>
<div>&nbsp;</div>
<div class="col-md-9">
    <#if comparendos??>
    <div class="panel panel-info">
    <!-- Default panel contents -->
        <div class="panel-heading">Consulta de Comparendos</div>
        <div class="panel-body">    
        </div>

        <!-- Table -->
       
        <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th># Número Comparendo </th>
                    <th>Fecha imposición</th>
                    <th>Tipo</th>
                    <th>Estado</th>
                    <th>Placa</th>
                    <th class="text-right">Valor nominal</th>
                </tr>
            </thead>
            <tbody>
                <#list comparendos as comparendo>
                <tr>
                    <td><a href="<@s.url controllerStatics.CONTROLLER_PATH_EXPEDIENTE />/${comparendo.id}">${(comparendo.numero.valor)!"<Sin número>"}</a></td>
                    <td><@l.dateFormat comparendo.fechaImposicion /></td>
                    <td>${(comparendo.tipoComparendo.nombre)!""}</td>
                    <td>${(comparendo.estado.nombre)!""}</td>
                    <td>${(comparendo.vehiculo.placaVehiculo)!""}</td>
                    <td class="text-right"><@l.currencyFormat (comparendo.valorNominal)!0 /></td>
                </tr>
                </#list>
            </tbody>
        </table>
        </div>
    </div>
    <#else>
    <div class="jumbotron">
        
        <#if sinresultados??>
        <h1>Buscar comparendos</h1>
        <p>Sipa no encontró coincidencias de acuerdo a los criterios de búsqueda ingresados. Use los criterios de busqueda e intente nuevamente.</p>
        <#else>
        <h1>Buscar comparendos</h1>
        <p class="lead">Use los criterios para realizar la búsqueda</p>
        </#if>
       
    </div>
    </#if>
</div>
<div class="col-md-3">
    <div class="well sipa-well-search">
        <h3>Criterios de búsqueda</h3>
        <p>Use los criterios de búsqueda para encontrar los comparendos que está buscando</p>
        <form action="<@s.url '/comparendo/consulta/buscar' />" method="get">
            <div class="form-group">
                <label for="numeroComparendo">Número comparendo</label>
                <@s.bind "comparendoForm.numeroComparendo"/>
                <input type="text" class="form-control" name="${s.status.expression}" value="${(comparendoForm.numeroComparendo)!''}" id="numeroComparendo"/>
                <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
            </div>
            <div class="form-group">
            <label for="tipo-documento">Tipo documento identidad</label>
            <@s.bind "comparendoForm.tipoDocumento"/>
            <select class="form-control" id="${s.status.expression}" name="${s.status.expression}">
                <#if tipoDocumento??>
                    <option value=""></option>
                    <#list tipoDocumento as td>
                        <#if comparendoForm.tipoDocumento?? && td.id == comparendoForm.tipoDocumento >
                            <option value="${td.id}" selected="selected">${td.sigla}-${td.nombre}</option>
                        <#else>
                            <option value="${td.id}">${td.sigla}-${td.nombre}</option>
                        </#if>
                    </#list>
                </#if>
            </select>
            <div class="has-error">
                <span class="help-block"><@s.showErrors "<br>"/></span>
           </div>
            </div>
            <div class="form-group">
                <label for="documento">Documento de identidad</label>
                <@s.bind "comparendoForm.numeroIdentificacion"/>
                <input type="text" class="form-control" name="${s.status.expression}" value="${(comparendoForm.numeroIdentificacion)!''}" id="numeroIdentificacion"/>
                <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
            </div>
            <div class="form-group">
                <label for="placa">Placa</label>
                <@s.bind "comparendoForm.placa"/>
                <input type="text" class="form-control" name="${s.status.expression}" value="${(comparendoForm.placa)!''}" id="placa">
                <div class="has-error">
                    <span  class="help-block"><@s.showErrors "<br>"/></span>
                </div>
            </div>
            <@s.bind "comparendoForm.message"/>
            <div class="has-error">
                <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
            <div>&nbsp;</div>
            <button class="btn btn-primary">Buscar</button>
        </form>
    </div>
</div>
</@l.main>