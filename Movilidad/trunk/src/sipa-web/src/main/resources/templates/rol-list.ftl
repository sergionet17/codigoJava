<@l.main>
<div class="well sipa-well">
  <h1>Roles</h1>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    </#if>
    <#if (roles?? && roles?size != 0)>
    <div class="table-responsive">
      <table class="table table-condensed">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Estado</th>
            <th>Opción</th>
          </tr>
        </thead>
        <tbody>
          <#list roles as rol>
            <tr>
              <td>
                <#if controller??>
                  <#if controller.hasRole("MODIFICAR_ROL")>
                    <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_EDIT + '/' + rol.id />">${(rol.name)!""}</a>
                  <#else>
                    ${(rol.name)!""}
                  </#if>
                <#else>
                  ${(rol.name)!""}
                </#if>
              </td> 
              <td>${(rol.descripcion)!""}</td>
              <td>${(rol.getDescripcionEstado())!""}</td>
              <td>
                <a class="btn btn-primary" href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_ESTADO+'/${rol.id}'/>">Cambiar estado</a>
              </td>
            </tr>
          </#list>
        </tbody>
      </table>
      </div>
      <@l.hasRole permission="CREAR_ROL">
      <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-success">Nuevo rol</a>
    </@l.hasRole>
    <#else>
      <P>No hay roles registrados.</P>           
    </#if>
  </div>
</div>
</@l.main>
