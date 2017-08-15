<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>

<div class="well sipa-well">
<div class="col-md-9">
<h1>Cursos Pedagógicos</h1>
<h3>Registrar asistencia a curso pedagógico</h3>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    </#if>
    <div>
    <div class="panel panel-info">
      <div class="panel-heading">Información del curso pedagógico</div>   
        <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
                <tr class="sipa-tr">
                    <th>Tema</th>
                    <th>Fecha</th>
                    <th>Hora Inicial</th>
                    <th>Hora Final</th>
                    <th>Instructor</th>
                    <th>Sede</th>
                    <th>Salón</th>
                </tr>
            </thead>
        <#if (curso??)>
            <tr class="sipa-tr">
                <td>${(curso.tema)!""}</td> 
                <td>${(curso.fecha?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td> 
                <td>${(curso.horaInicial)!""}</td> 
                <td>${(curso.horaFinal)!""}</td> 
                <td>${(curso.instructor.persona.primerNombre)!""}  ${(curso.instructor.persona.segundoNombre)!""}  ${(curso.instructor.persona.primerApellido)!""}  ${(curso.instructor.persona.segundoApellido)!""}</td> 
                <td>${(curso.salon.sede.nombre)!""}</td> 
                <td>${(curso.salon.nombre)!""}</td> 
            </tr>
        </table>
        </div>
    </div>    
    <#else>
        <P>No hay cursos registrados vigentes.</P>  
    </#if>
    </div>
       <p  class="lead">A continuación se presenta la lista de inscritos. Por favor seleccione a los asistentes que completaron el curso</p>
    <div>
    <form  action="<@s.url '/curso_asistencia/save'/>"  method="post" >
    <fieldset>
       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <#if (comparendos?? && comparendos?size != 0)>
        <div class="panel panel-info">
          <div class="panel-heading">Información del comparendo</div>   
            <div class="table-responsive">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Tipo documento</th>
                        <th>Número documento</th>
                        <th>Nombre</th>
                        <th>Número de comparendo</th>
                        <th>Asistio</th>
                    </tr>
                </thead>
                <#list comparendos as comparendo>
                 <tr>
                    <td>${(comparendo.infractor.documentoIdentidad.tipo.nombre)!""}</td>
                    <td>${(comparendo.infractor.documentoIdentidad.numero)!""}</td>
                    <td>${(comparendo.infractor.persona.primerNombre)!""} ${(comparendo.infractor.persona.segundoNombre)!""} ${(comparendo.infractor.persona.primerApellido)!""} ${(comparendo.infractor.persona.segundoApellido)!""}</td>
                    <td>${(comparendo.numero.numeroComparendoId.numero)!""}</td>
                    <td> <input type="checkbox" id="asistente" value="${(comparendo.id)!""}" name="asistente"/> </td>
                </tr>
                </#list>         
            </table>
           </div>
        </div>    
        <#else>
              <P>No hay cursos registrados vigentes.</P>
        </#if>  
     </fieldset>
     <button class="btn btn-primary">Guardar</button>
    </form>
    </div>
</div>
</div>
</@l.main>

