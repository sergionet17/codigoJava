<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div>&nbsp;</div>
<div class="well sipa-well">
  <div class="container">
    <div class="col-md-11">

        <h1>Lista de Averiguaciones Preliminares</h1>

        <div class="panel panel-default">
            <div class="panel-body">
            </div>
            <div class="table-responsive">
              <div class="input-group">
                    <span class="input-group-addon" id="sizing-addon2">Buscar:  </span>
                    <input id="inputBuscar" type="text" class="form-control" placeholder="Palabra clave ..." aria-describedby="sizing-addon2">
              </div>
              <div class="table-responsive">
              <table class="table table-bordered" id="tableIncosistencias">
                  <thead>
                  <tr>
                      <th>Número</th>
                      <th>Indagado</th>
                      <th>Fecha</th>
                      <th>Origen</th>
                      <th>Tipo Identificacion</th>
                      <th>Indentificacion</th>
                  </tr>
                  </thead>
                  <tbody>
                      <#list averiguacionesPreliminares as averiguacion>
                          <tr>
                              <td>${(averiguacion.numero)!""}</td>
                              <td>${(averiguacion.indagado)!""}</td>
                              <td>${(averiguacion.fecha)!""}</td>
                              <td>${(averiguacion.origen)!""}</td>
                              <td>${(averiguacion.tipoIdentificacion)!""}</td>
                              <td>${(averiguacion.identificacion)!""}</td>
                          </tr>
                      </#list>
                  </tbody>
              </table>
              </div>
            </div>
        </div>
    </div>
  </div>
</div>
</@l.main>