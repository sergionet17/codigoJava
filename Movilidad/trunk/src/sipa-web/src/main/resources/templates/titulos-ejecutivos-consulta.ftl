<#import "titulos-ejecutivos.ftl" as t/>
<@t.template>
    <#if (titulosEjecutivos?? && titulosEjecutivos?size != 0)>
      <div class="table-responsive">
          <table class="table table-condensed">
              <thead>
                  <tr>
                    <th>Fecha titulo</th>
                    <th>Dependecia</th>
                    <th>Valor</th>
                    <th>Estado</th>
                    <th>Fuente Titulo</th>
                  </tr>
              </thead>
              <#list titulosEjecutivos as tituloEjecutivo>
                <tr>
                  <td>${(tituloEjecutivo.fechaTitulo)!""}</td>
                  <td>${(tituloEjecutivo.dependencia.nombre)!""}</td>
                  <td>${(tituloEjecutivo.valor)!""}</td>
                  <td>${(tituloEjecutivo.estado.nombre)!""}</td>
                  <td>${(tituloEjecutivo.fuenteTituloEjecutivo.nombre)!""}</td>
                </tr>
              </#list>
          </table>
          <a href= "<@s.url controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-md btn-success">¡Crear título ahora!</a>
      </div>
    <#else>
      <div class="jumbotron">
        <h1>Aún no hay títulos ejecutivos creados :-(</h1>
        <p class="lead">Registra un nuevo titulo siguiendo el boton de creación de títulos </p>
        <p>
          <a href= "<@s.url controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-lg btn-success">¡Crear titulo ahora!</a>
        </p>
      </div>
    </#if>
</@t.template>
