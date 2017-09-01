<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@l.main>
<div class="well sipa-well">
  <div>
  <h1>Cursos Pedagógicos</h1>
  <#if error??>
  <div class="has-error">
      <span  class="help-block">${error}</span>
  </div>
  </#if>
    <#if (cursos?? && cursos?size != 0)>
    <div class="panel panel-info">
    <div class="panel-heading">Consulta de Cursos Pedagógicos</div>
      <div class="table-responsive">
        <table class="table table-bordered">
          <thead>
            <tr class="sipa-tr">
              <th>Tema</th>
              <th>Fecha</th>
              <th>Hora Final</th>
              <th>Hora Inicial</th>
              <th>Instructor</th>
              <th>Número de Participantes</th>
              <th>Sede</th>
              <th>Salón</th>
            </tr>
          </thead>
          <tbody>
            <#list cursos as curso>
              <tr class="sipa-tr">
                <td>${(curso.tema)!""}</td> 
                <td>${(curso.fecha?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td> 
                <td>${(curso.horaInicial)!""}</td> 
                <td>${(curso.horaFinal)!""}</td> 
                <td>${(curso.instructor.persona.primerNombre)!""}  ${(curso.instructor.persona.segundoNombre)!""}  ${(curso.instructor.persona.primerApellido)!""}  ${(curso.instructor.persona.segundoApellido)!""}</td>  
                <td>${(curso.maximoAsistentes)!""}</td> 
                <td>${(curso.salon.sede.nombre)!""}</td> 
                <td>${(curso.salon.nombre)!""}</td> 
              </tr>
            </#list>
          </tbody>
        </table>
        </div>  
      </div>   
      <#else>
        <P>No hay cursos registrados vigentes.</P>        
      </#if>     
      <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-success">Crear nuevo</a>
  </div>
</div>
</@l.main>
