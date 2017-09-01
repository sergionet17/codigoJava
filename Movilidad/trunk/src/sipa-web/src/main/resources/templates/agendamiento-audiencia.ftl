<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div class="well sipa-well">
  <div class="panel panel-primary">
    <div class="panel-heading">Nuevo Horario de Audiencia</div>
    <div class="panel-body sipa-m-3">
    <br>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    </#if>
    <form class="form-horizontal" action="<@s.url '/agendamiento-audiencia/save'/>"  method="post">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

          <div class="form-group">
            <fieldset>
              <label for="tipo" class="col-sm-2 control-label">Tipo de Audiencia</label>
              <div class="col-sm-6">
                  <@s.bind "agendamientoForm.tipo.id"/>
                  <@s.formSingleSelect "agendamientoForm.tipo", tiposAudiencia, "id ='tipo' class='form-control'"/>            
                  <div class="has-error">
                       <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
              </div>    
            </fieldset>
          </div>
          <div class="form-group">
            <fieldset>
                <label  class="col-sm-2 control-label">Rango de días</label>
                <div>
                <label  class="col-sm-1 control-label" for="diaInicial">Desde</label>
                </div>
                 <div class="col-sm-2">
                    <@s.bind "agendamientoForm.diaInicial"/>
                    <select name="${s.status.expression}" class="form-control" value="${(agendamientoForm.diaInicial)!''}" id="diaInicial">
                     <#if diasForm??>
                      <#list diasForm as dia>
                       <option value="${dia}"[@s.checkSelected value/]>${dia}</option>
                      </#list>
                    </#if>
                    </select>     
                    <div class="has-error">
                       <span class="help-block"><@s.showErrors "<br>"/></span>
                    </div>
                </div> 
                <label  class="col-sm-1 control-label"  for="diaFinal">Hasta</label>
                 <div class="col-sm-2">
                    <@s.bind "agendamientoForm.diaFinal"/>
                    <select name="${s.status.expression}" class="form-control" value="${(agendamientoForm.diaFinal)!''}" id="diaFinal">
                    <#if diasForm??>
                      <#list diasForm as dia>
                        
                          <option value="${dia}"[@s.checkSelected value/]>${dia}</option>
                        
                      </#list>
                    </#if>
                    </select>     
                    <div class="has-error">
                       <span class="help-block"><@s.showErrors "<br>"/></span>
                    </div>
                </div> 
            </fieldset>    
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-primary">Guardar</button>
            </div>
          </div>
      </form>
    </div>
  </div>
</div>
</@l.main>