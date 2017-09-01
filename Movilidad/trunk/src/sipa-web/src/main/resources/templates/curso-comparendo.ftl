<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>

  <div class="well sipa-well">
    <h1>Cursos Pedagógicos</h1>
    <h3>Inscribir a curso pedagógico</h3>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    </#if>
    <br>
    <fieldset>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <#if (comparendo??)>
      <div class="panel panel-info">
        <div class="panel-heading">Información del comparendo</div>    
         <div class="table-responsive">
          <table class="table table-bordered">
            <thead>
              <tr>
                  <th>Tipo</th>
                  <th>Estado</th>
                  <th>Número</th>
                  <th>Placa</th>
                  <th>Fecha</th>
                  <th>Saldo</th>
                  <th>Interes</th>
                  <th>Total saldo + Interes</th>
              </tr>
            </thead>
              <tr>
                  <td>${(comparendo.tipoComparendo.nombre)!""}</td>
                  <td>${(comparendo.estado.nombre)!""}</td>
                  <td>${(comparendo.numero.numeroComparendoId.numero)!""}</td>
                  <td>${(comparendo.vehiculo.placaVehiculo)!""}</td>
                  <td>${(comparendo.fechaImposicion?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td>
                  <td>$ ${(comparendo.valorNominal)!"0.00"}</td>
                  <td>$0.00</td>
                  <td>$ ${(comparendo.valorNominal)!"0.00"}</td>
              </tr>
            </table>
        </div>
        <#else>
            <P>No hay cursos registrados vigentes.</P>
  	  </div>	
    </#if> 
   </fieldset>
     <p>A continuación se muestra el descuento para el que aplica el comparendo y el valor que debe estar cancelado como requisito para la inscripción</p>
  </div>

  <div>
    <fieldset>
    <div class="table-responsive col-md-4 col-sm-4">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <td><p class="lead">Descuento de ${porcentaje_descuento}%</p></td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>$ ${(descuento)!"0.00"}</td>
                </tr>
            </tbody>
        </table>
        </div>
    </fieldset>
  </div>
  <div>
  <p>Seleccione de la lista en curso pedagógico al que quiere realizar la inscripción</p>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <#if (cursos?? && cursos?size != 0)>
      <div class="panel panel-info">
      <div class="panel-heading">Lista de cursos pedagógicos</div>    
        <div class="table-responsive">
          <table class="table table-bordered">
            <thead>
              <tr>
                  <th>Tema</th>
                  <th>Fecha</th>
                  <th>Hora Inicial</th>
                  <th>Hora Final</th>
                  <th>Instructor</th>
                  <th>Número de Participantes</th>
                  <th>Sede</th>
                  <th>Salón</th>
                  <th>Opción</th>
              </tr>
          </thead>
             <#list cursos as curso>
              <tr>
                  <td>${(curso.tema)!""}</td> 
                  <td>${(curso.fecha?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td> 
                  <td>${(curso.horaInicial)!""}</td> 
                  <td>${(curso.horaFinal)!""}</td> 
                  <td>${(curso.instructor.persona.primerNombre)!""}  ${(curso.instructor.persona.segundoNombre)!""}  ${(curso.instructor.persona.primerApellido)!""}  ${(curso.instructor.persona.segundoApellido)!""}</td> 
                  <td>${(curso.maximoAsistentes)!""}</td> 
                  <td>${(curso.salon.sede.nombre)!""}</td> 
                  <td>${(curso.salon.nombre)!""}</td> 
                  <td>
                  <a class="btn btn-primary" href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_SAVE+'/${curso.id}/${comparendo.numero.numeroComparendoId.numero}'/>">Inscribir</a></td>               
              </tr>
            </#list>
          </table>
        </div>
      </div> 
      <#else>
        <P>No hay cursos registrados vigentes.</P>           
      </#if>
    </fieldset>
  </div>
  <#if (comparendo??)>
    <a class="btn btn-primary" href="<@s.url controllerStatics.CONTROLLER_PATH+controllerStatics.CONTROLLER_CANCELAR+'/${comparendo.numero.numeroComparendoId.numero}'/>">Cancelar</a>
  </#if>
</div>
</@l.main>