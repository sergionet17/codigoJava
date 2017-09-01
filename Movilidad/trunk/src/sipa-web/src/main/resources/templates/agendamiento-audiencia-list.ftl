<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
  <div class="well sipa-well">
  <h1>Horario de Audiencía</h1>

  <div class="panel-body sipa-m-3">
  <br>
  <#if error??>
    <div class="has-error">
        <span  class="help-block">${error}</span>
    </div>
  </#if>
  <form >
      <fieldset>
       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <#if (agendamientoForm?? && agendamientoForm?size != 0)>
          <div class="panel panel-info">
           <div class="panel-heading">Parametrización de Audiencia de Continuación</div>
              <div class="table-responsive">
              <table class="table table-condensed table-bordered">
                  <thead>
                      <tr class="sipa-tr">
                          <th>Tipo Audiencía</th>
                          <th>Día Inicial</th>
                          <th>Día Final</th>
                          <th>Modificar</th>
                      </tr>
                  </thead>
                 <#list agendamientoForm as agenda>
                  <tr class="sipa-tr">
                      <td>${(agenda.tipo.nombre)!""}</td> 
                      <td>${(agenda.diaInicial)!""}</td> 
                      <td>${(agenda.diaFinal)!""}</td> 
                      <td> <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_MODIFY+'/${agenda.tipo.id}' />" class="btn btn-primary">Modificar</a></td>
                  </tr>
                </#list>
            </table>
          </div>  
        </div>
          <#else>
              <P>No hay parametrización de rangos de tiempo.</P> 
          </#if>
      </fieldset>            
          <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-success">Crear nuevo</a>
    </form>
  </div>
</div>
</@l.main>
