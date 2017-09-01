<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@l.main>
<div class="well sipa-well">
  <div class="container">
    <div class="col-md-9">
      <h1>Resumen y Confirmación de Eliminacíon de Rangos</h1>
        <fieldset>
               <p><em>A continuación se muestra el listado de númeracion de comparendos que seran eliminados</em></p>
               <#if (numerosComparendosAnular?? && numerosComparendosAnular?size != 0)>
               <div class="table-responsive">
                  <table class="table table-condensed">
                    <thead>
                      <tr>
                        <th>Valor</th>
                        <th>Consecutivo</th>
                        <th>Estado</th>
                      </tr>
                    </thead>
                    <#list numerosComparendosAnular as numeroComparendo>
                      <tr>
                        <td>${(numeroComparendo.valor)!""}</td>
                        <td>${(numeroComparendo.consecutivo)!""}</td>
                        <td>${(numeroComparendo.estado.nombre)!""}</td>
                      </tr>
                    </#list>
                  </table>
                  </div>
                <#else>
                    <P>No hay rangos de numeración registrados para anular.</P>
                </#if>


                <p><em>A continuación se muestra el listado de Coparendos seran eliminados</em></p>
                <#if (comparendosAnular?? && comparendosAnular?size != 0)>
                <div class="table-responsive">
                   <table class="table table-condensed">
                     <thead>
                       <tr>
                         <th>Valor</th>
                         <th>Consecutivo</th>
                         <th>Estado</th>
                         <th>fecha de imposicion</th>
                         <th>Placa verhiculos<th>
                       </tr>
                     </thead>
                     <#list comparendosAnular as comparendo>
                       <tr>
                         <td>${(comparendo.numero.valor)!""}</td>
                         <td>${(comparendo.numero.consecutivo)!""}</td>
                         <td>${(comparendo.estado.nombre)!""}</td>
                         <td>${(comparendo.fechaImposicion)!""}</td>
                         <td>${(comparendo.vehiculo.placaVehiculo)!""}</td>
                       </tr>
                     </#list>
                   </table>
                   </div>
                 <#else>
                     <P>No hay rangos de numeración registrados para anular.</P>
                 </#if>
                <a href="<@s.url controllerStatics.CONTROLLER_PATH />" class="btn btn-success">Registrar nuevo rango</a>
       </fieldset>
    </div>
  </div>
</div>

</@l.main>
