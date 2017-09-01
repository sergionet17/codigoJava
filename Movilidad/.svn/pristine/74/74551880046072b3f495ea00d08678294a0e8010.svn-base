<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>

<div class="col-md-6 col-md-offset-3">
<div class="well sipa-well">
    <h1>Modificar Comparendo Inconsistente</h1>
    <div>
        <form  action="<@s.url '/inconsistencias/inconsistencias-guardar/${formatoComparendo.id}'/>"  method="post" >
            <fieldset>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <#if errValidacion??>
                    <div class="form-group">
                        <@s.bind "formatoComparendo.message"/>
                        <div class="has-error">
                            <span  class="help-block"><@s.showErrors "<br>"/></span>
                        </div>
                    </div>
                </#if>
                <#list atributos as atributo>
                  <div class="form-group">
                     <label for="${atributo.idInput}">${atributo.labelname}</label>
                        <@s.bind "${(formatoComparendo[atributo.idInput])}"/>
                       <input type="text" class="form-control" name="${atributo.idInput}" value="${(formatoComparendo[atributo.idInput])}" id="${(atributo.idInput)}"/>
                       <div class="has-error">
                           <span class="help-block"><@s.showErrors "<br>"/></span>
                       </div>
                  </div>
                </#list>
                <button class="btn btn-primary">Guardar</button>
            </fieldset>
        </form>
    </div>
    </div>
    </div>
</@l.main>
