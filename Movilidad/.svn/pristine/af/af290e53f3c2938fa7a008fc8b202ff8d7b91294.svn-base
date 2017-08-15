<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div>&nbsp;</div>
<div class="well sipa-well">
  <div class="container">
    <div class="col-md-11">

        <h1>Reparto de documentos para la Subdireccion de Investigaciones al Transporte</h1>

        <div class="panel panel-default">
            <div class="panel-body">
            </div>
            <form action="<@s.url '/transporte_publico_reparto/assign' />" class="formEdit" method="post">
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
                                    <input type="checkbox" value="${(docInfraccion.id)!""}" name="selec" />
                                  </td>
                              </tr>
                          </#list>
                      </tbody>
                  </table>
                  </div>
                </div>
                <div>
                <label  class="col-sm-1 control-label" for="sustanciador">Sustanciador</label>
                </div>
                 <div class="col-sm-2">
                    <!--@s.bind "agendamientoForm.diaInicial"/-->
                    <select name="suscitador" class="form-control" value="" id="sustanciador">
                      <#list sustanciadores as sustan>
                       <option value="${sustan}" [@s.checkSelected value/]>${sustan.persona.primerNombre}</option>
                      </#list>
                    </select>
                    <div class="has-error">
                       <span class="help-block">Debe seleccionar infracciones y un Suscitador.</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-2">
                        <button id="guardar" type="submit" class="btn btn-primary btn-md btn-block">Guardar</button>
                    </div>
                 </div>
             </form>
        </div>
    </div>
  </div>
</div>
</@l.main>