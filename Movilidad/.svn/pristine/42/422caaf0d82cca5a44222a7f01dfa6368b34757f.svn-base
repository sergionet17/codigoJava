<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>

<div class="well sipa-well">
  <div class="container">
    <div class="col-md-9">
    <h1>Cursos Pedagógicos</h1>
    <br>
     <fieldset>

      <form  action="<@s.url '/curso/curso_registro'/>"  method="post" >
       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <#if (cursos?? && cursos?size != 0)>

          <p  class="lead">Lista de cursos pedagógicos</p>
    <div class="table-responsive">
          <table class="table table-bordered" border="1">
              <thead>
                  <tr>
                      <th>Tema</th>
                      <th>Fecha</th>
                      <th>Hora</th>
                      <th>Instructor</th>
                      <th>Número de Participantes</th>
                      <th>Sede</th>
                      <th>Salón</th>
                  </tr>
              </thead>

        <p>Resultados para cursos</p>

          <#list cursos as curso>

          <tr>
              <td>${(curso.tema)!""}</td> 
              <td>${(curso.fecha)!""}</td> 
              <td>${(curso.hora)!""}</td> 
              <td>${(curso.instructor.persona.primerNombre)!""}</td> 
              <td>${(curso.maximoAsistentes)!""}</td> 
              <td>${(curso.salon.sede.nombre)!""}</td> 
              <td>${(curso.salon.nombre)!""}</td> 

          </tr>
          </#list>
          </table>
          </div>
          <#else>
              <P>No hay cursos registrados vigentes.</P>           
          </#if>
           <button class="btn btn-success">Crear nuevo</button>
        </form>
      </fieldset>
    </div>
  </div>
</div>
</@l.main>
