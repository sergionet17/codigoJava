<@l.main>
<div class="well sipa-well">
  <h1>Dependencias</h1>
  <div class="container-fluid">
    <@l.hasRole permission="CREAR_DEPENDENCIA">
      <div class="pull-left">
        <p>
          <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_CREATE />" class="btn btn-success">Crear dependencia</a>
        </p>
        <#if (dependenciasSinResponsable?? && dependenciasSinResponsable?size != 0)>
          <div class="alert alert-danger">
              <p>
                <@s.message "dependencias.responsable.null"/>
                <#list dependenciasSinResponsable as dependencia>
                  <br>${dependencia.nombre}
                </#list>
              </p>
          </div>
        </#if>
      </div>
    </@l.hasRole>
    <#if (dependencias?? && dependencias?size != 0)>
    <div class="table-responsive">
      <ul class="treeView collapsibleList" id="arbol">
        <#list dependencias as dependencia>
          <@construirArbolDependencias dependencia />
        </#list>
      </ul>
    </div>
    <#else>
      <P>No hay dependencias registradas.</P>           
    </#if>
  </div>
</div>
<@l.script>
  <script type="text/javascript">
    <!-- 
      $( document ).ready( function() {

        collapsibleListGo('#arbol');

        // global var used to disable click event
        var clickDisabled = 0;

        function collapsibleListGo(elementSelector) {

          // search the dom for existance of .collapsibleList
          if ($(elementSelector).length > 0) {

            // iterate through all elementSelector li's
            $(elementSelector + ' li').each(function() {

              // test for children 
              if ($(this).children('ul').length > 0) {
                // console.log('this has children');

                // add class closed
                $(this).addClass('collapsibleListClosed');

                // hide all children
                $(this).children('ul').slideUp();

                $(this).click(function() {
                  if (clickDisabled == 0) {

                    $(this).toggleClass('collapsibleListClosed').toggleClass('collapsibleListOpen');
                    $(this).children('ul').slideToggle();

                    // 100 ms delay to prevent parent item from closing
                    resetClick(100);
                  };
                });

              };
            });
          };
        }; // end collapsibleListGo

        function resetClick(timeoutTime) {
          clickDisabled = 1;

          var resetClickTimeout = setTimeout(function() {
            clickDisabled = 0;  
          }, timeoutTime);
        }
      });
    -->
  </script>
</@l.script>
<#macro construirArbolDependencias rama>
  <li>
    <#if controller??>
      <#if controller.hasRole("MODIFICAR_DEPENDENCIA")>
        <a href="<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_EDIT + '/' + rama.id />">${(rama.nombre)!""}</a>
      <#else>
        ${(rama.nombre)!""}
      </#if>
    <#else>
      ${(rama.nombre)!""}
    </#if>
    <#if (rama.dependencias?? && rama.dependencias?size != 0)>
      <ul>
        <#list rama.dependencias as child>
          <@construirArbolDependencias child />
        </#list>
      </ul>
    </#if>
  </li>
</#macro>
</@l.main>
