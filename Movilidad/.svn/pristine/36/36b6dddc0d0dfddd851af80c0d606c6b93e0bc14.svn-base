<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div>&nbsp;</div>
<div class="well">
        <#if historico??>
        <div class="page-header">
          <h3>Historial de asignación de turnos para firma de las autoridades de tránsito&nbsp</h3>
          <small>A continuación se muestra el listado con las fechas en elas cuales las autoridades han sido asignadas para firmas automáticas destro de SIPA.</small>
        </div>
        <div class="panel panel-info">
        <!-- Default panel contents -->
            <div class="panel-heading">Consulta de Históricos</div>
            <div class="panel-body">    
            </div>

            <!-- Table -->
           
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Autoridad </th>
                            <th>Fecha inicial</th>
                            <th>Fecha final</th>
                            <th>Autoridad suplente</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list historico as h>
                        <tr>
                            <td>${h.autoridad.persona.primerNombre!""}&nbsp${h.autoridad.persona.segundoNombre!""}&nbsp${h.autoridad.persona.primerApellido!""}&nbsp${h.autoridad.persona.segundoApellido!""}</td>
                            <td><@l.dateFormat h.desde /></td>
                            <td><@l.dateFormat h.hasta /></td>
                            <td>${h.suplenteOficial.persona.primerNombre!""}&nbsp${h.suplenteOficial.persona.segundoNombre!""}&nbsp${h.suplenteOficial.persona.primerApellido!""}&nbsp${h.suplenteOficial.persona.segundoApellido!""}</td>
                            <td><#if !h.cerrado><#else>Cerrado</#if></td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
</div>
     <#else>
<div class="well">
        <div class="jumbotron">
            <#if sinResultados??>
                <h1>Histórico de asignación de turnos para firma automática.</h1>
                <p>${sinResultados}</p>
            </#if>
        </div>
    </#if>
</div>
</@l.main>