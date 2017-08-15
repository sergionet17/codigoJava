<@l.main>
<div class="well sipa-well">
  <h1>Usuarios</h1>
    <#if (usuarios?? && usuarios?size != 0)>
    <div class="table-responsive">
      <table class="table table-condensed">
        <thead>
          <tr>
            <th>Usuario</th>
            <th>Nombre</th>
            <th>Dependencia</th>
            <th>Cargo</th>
            <th>Perfil</th>
            <th>Fecha caducidad</th>
            <th>Estado</th>
            <th>Opción</th>
          </tr>
        </thead>
        <tbody>
          <#list usuarios as usuario>
            <tr>
              <td>
                <#if controller??>
                  <#if controller.hasRole("MODIFICAR_USUARIO")>
                    <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_EDIT + '/' + usuario.id />">${(usuario.username)!""}</a>
                  <#else>
                    ${(usuario.username)!""}
                  </#if>
                <#else>
                  ${(usuario.username)!""}
                </#if>
              </td> 
              <td>${(usuario.persona.obtenerNombreCompleto())!""}</td>
              <td>${(usuario.dependencia.nombre)!""}</td>
              <td>${(usuario.cargo)!""}</td>
              <td>${(usuario.perfil.nombre)!""}</td>
              <td>${(usuario.fechaCaducidad)!""}</td>
              <td>${(usuario.getDescripcionEstado())!""}</td>
              <td>
                <a class="btn btn-primary" href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_ESTADO+'/${usuario.id}'/>">Cambiar estado</a>
              </td>
            </tr>
          </#list>
        </tbody>
      </table>
    </div>
    <@l.hasRole permission="CREAR_USUARIO">
    <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-success">Nuevo usuario</a>
  </@l.hasRole>
  </div>
  <#else>
    <P>No hay usuarios registrados.</P>.           
  </#if>
</@l.main>
