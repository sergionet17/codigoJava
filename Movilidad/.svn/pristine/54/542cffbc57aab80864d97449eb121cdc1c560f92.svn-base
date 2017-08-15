<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div>&nbsp;</div>
<div class="well sipa-well">
  <div class="container">
    <div class="col-md-11">

        <h2>Reparto de documentos para la Subdireccion de Investigaciones al Transporte</h2>
        <@l.form action="/transporte_publico_reparto/asignar" class="form-horizontal">
        <div class="panel panel-default">
            <div class="panel-body">
              <div class="table-responsive">
                  <div class="input-group">
                        <span class="input-group-addon" id="sizing-addon2">Buscar:  </span>
                        <input id="inputBuscar" type="text" class="form-control" placeholder="Palabra clave ..." aria-describedby="sizing-addon2">
                  </div>
                  <div class="table-responsive">
                  <table class="table table-bordered" id="tableIncosistencias">
                      <thead>
                      <tr>
                          <th>Número de radicado</th>
                          <th>Asunto</th>
                          <th>Fecha</th>
                          <th>Número de informe de infracción</th>
                          <th></th>
                      </tr>
                      </thead>
                      <tbody>
                          <#list documentosInfraccion as docInfraccion>
                              <tr>
                                  <td>${(docInfraccion.radicado)!""}</td>
                                  <td>${(docInfraccion.asunto)!""}</td>
                                  <td>${(docInfraccion.fecha)!""}</td>
                                  <td>${(docInfraccion.radicado)!""}</td>
                                  <td>
                                    <input type="checkbox" value="${(docInfraccion.id)!""}" name="infraccionesSelectedIds" />
                                  </td>
                              </tr>
                          </#list>
                      </tbody>
                  </table>
                  </div>
                </div>
                
                <div class="col-md-6">
                  <div class="form-group sipa-m-3-r">
                    <label class="col-sm-1 control-label" for="suscitadorId">Suscitador</label>
                    <select name="suscitadorId" class="form-control" value="" id="suscitadorId">
                      <#list sustanciadores as sustan>
                         <option value="${sustan.id}" [@s.checkSelected value/]>${sustan.persona.primerNombre + ' ' + sustan.persona.primerApellido}</option>
                      </#list>
                    </select>
                    <div class="has-error">
                      <span class="help-block">Debe seleccionar infracciones y un Suscitador.</span>
                    </div>
                  </div>
                </div>
                <p>
                    <@l.formSubmit label="Asociar" />
                </p>
            </div>
        </div>
        </@l.form>
    </div>
  </div>
</div>
</@l.main>