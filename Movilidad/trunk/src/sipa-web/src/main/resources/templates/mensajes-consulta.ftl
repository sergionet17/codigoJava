<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div class="col-md-9">
<div class="well sipa-well">
  <h1>Listado de Mensajes del sistema</h1>
  <fieldset>
    <p><em> A continuación se muestra el listado de mensajes del sistema </em></p>
   <#if (mensajes?? && mensajes?size != 0)>
       <div class="table-responsive">
         <table class="table table-condensed">
           <thead>
             <tr>
               <th>FECHA DE CREACIÓN</th>
               <th>MENSAJE</th>
             </tr>
           </thead>
           <#list mensajes as mensaje>
           <tr>
             <td>${(mensaje.fechaCreacion)!""}</td>
             <td>${(mensaje.mensaje)!""}</td>
           </tr>
           </#list>
         </table>
       </div>
       <#else>
       <P>No hay mensajes registrados.</P>
       </#if>
  </fieldset>
  </div>
</div>
</@l.main>