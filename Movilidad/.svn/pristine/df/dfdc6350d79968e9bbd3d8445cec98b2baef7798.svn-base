<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div class="well sipa-well">
<div class="container">
<div>
<h1>Cursos Pedagógicos</h1>
<#if error??>
  <div class="has-error">
      <span  class="help-block">${error}</span>
  </div>
</#if>
  <form >
    <fieldset>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <#if (cursos?? && cursos?size != 0)>
        <div class="panel panel-info">
         <div class="panel-heading">Consulta de Cursos Pedagógico Vigentes</div>
            <div class="table-responsive">
            <table class="table table-condensed table-bordered">
                <thead>
                    <tr class="sipa-tr">
                        <th>Tema</th>
                        <th>Fecha</th>
                        <th>Hora Inicial</th>
                        <th>Hora Final</th>
                        <th>Instructor</th>
                        <th>Número de Participantes</th>
                        <th>Sede</th>
                        <th>Salón</th>
                    </tr>
                </thead>
               <#list cursos as curso>
                <tr class="sipa-tr">
                    <td><a href="<@s.url '/curso_asistencia/consultar/${curso.id}'/>"">${(curso.tema)!""}</a></td> 
                    <td>${(curso.fecha?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td> 
                    <td>${(curso.horaInicial)!""}</td> 
                    <td>${(curso.horaFinal)!""}</td> 
                    <td>${(curso.instructor.persona.primerNombre)!""}  ${(curso.instructor.persona.segundoNombre)!""}  ${(curso.instructor.persona.primerApellido)!""}  ${(curso.instructor.persona.segundoApellido)!""}</td> 
                    <td>${(curso.maximoAsistentes)!""}</td> 
                    <td>${(curso.salon.sede.nombre)!""}</td> 
                    <td>${(curso.salon.nombre)!""}</td> 
                </tr>
              </#list>
          </table>
        </div>  
      </div>
        <#else>
            <P>No hay cursos registrados vigentes.</P>           
        </#if>
    </fieldset>     
  </form>
</div>
</div>
</div>
</@l.main>
