<@l.main>
 <div class="col-md-9">
  <h1>Alarmas</h1>
   <#if (alarmas?? && alarmas?size != 0)>
    <div class="table-responsive">
      <table class="table table-condensed">
        <thead>
          <tr>
            <th>Clave</th>
            <th>Descripcion</th>
          </tr>
        </thead>
        <tbody>
          <#list alarmas as alarma>
            <tr>
              <td>${(alarma.clave)!""}</td>
              <td>${(alarma.descripcion)!""}</td>
              <td>	
               <a href="<@s.url "/editar"+"/TAREAS_EN_PROGRESO" />" class="btn btn-primary">Ver Alarma</a>
              </td>
            </tr>
          </#list>
        </tbody>
      </table>
    </div>
  </div>
  <#else>
    <P>No hay alarmas registradas.</P>.           
  </#if>
</@l.main>
