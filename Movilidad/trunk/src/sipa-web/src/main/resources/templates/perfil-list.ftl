<@l.main>
<div class="well sipa-well">
  <h1>Perfiles</h1>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    </#if>
    <#if (perfiles?? && perfiles?size != 0)>
    <div class="table-responsive">
      <table class="table table-condensed">
        <thead>
          <tr>
            <th class="col-md-4">Nombre</th>
            <th class="col-md-4">Estado</th>
            <th class="col-md-4">Opción</th>
          </tr>
        </thead>
        <tbody>
          <#list perfiles as perfil>
            <tr>
              <td>
                <#if controller??>
                  <#if controller.hasRole("MODIFICAR_PERFIL")>
                    <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_EDIT + '/' + perfil.id />">${(perfil.nombre)!""}</a>
                  <#else>
                    ${(perfil.nombre)!""}
                  </#if>
                <#else>
                  ${(perfil.nombre)!""}
                </#if>
              </td> 
              <td>${(perfil.getDescripcionEstado())!""}</td>
              <td>
                <a class="btn btn-primary" href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_ESTADO+'/${perfil.id}'/>">Cambiar estado</a>
              </td>
            </tr>
          </#list>
        </tbody>
      </table>
      </div>
      <@l.hasRole permission="CREAR_PERFIL">
      <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-success">Nuevo perfil</a>
    </@l.hasRole>
    <#else>
      <P>No hay perfiles registrados.</P>           
    </#if>
  </div>
</div>
</@l.main>
