<#import "rangos.ftl" as r/>
<@r.template>
  <#if (rangos?? && rangos?size != 0)>

    <div class="table-responsive">
      <table class="table table-condensed">
        <thead>
          <tr>
            <th>Intervalo</th>
            <th>Evidencia</th>
            <th>Organismo</th>
            <th>Tipo Rango numeración</th>
          </tr>
        </thead>
        <#list rangos as rango>
        <tr>
          <td>[${(rango.inicio)!""}, ${(rango.fin)!""}]</td>
          <td><@l.documentoLink documento=rango.respuestaSolicitud /></td>
          <td>${(rango.organismoTransito.nombre)!""}</td>
          <td>${(rango.tipoRangoNumeracion.nombre)!""}</td>
        </tr>
        </#list>
      </table>
      <a href= "<@s.url controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-md btn-success">¡Crear rango ahora!</a>
    </div>
  <#else>
    <div class="jumbotron">
      <h1>Aún no hay rangos :-(</h1>
      <p class="lead">Registra un rango de numeración siguiendo el botón de creación de rangos.</p>
      <p>
        <a href= "<@s.url controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-lg btn-success">¡Crear rango ahora!</a>
      </p>
    </div>
  </#if>
</@r.template>
