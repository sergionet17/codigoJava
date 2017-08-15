<@l.main>
<div class="well sipa-well">
<h1>Parámetros de proceso</h1>
<div class="container-fluid">
  <#if (categorias?? && categorias?size != 0)>
  <div class="table-responsive">
    <div class="col-md-6 col-xs-12">
      <ul class="treeView collapsibleList" id="arbol">
        <#list categorias as categoria>
          <@construirArbolCategorias categoria />
        </#list>
      </ul>
    </div>
    <div class="col-md-6 col-xs-12" id="detalleParametro"></div>
  </div>
  <#else>
    <P>No hay parámetros registrados.</P>           
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
      function verParametros(clave) {
        var url = "<@s.url controllerStatics.CONTROLLER_PATH_PARAMS />/" + clave;
        $.get(url, function(data) {
            var divContenedor = $("#detalleParametro");
            divContenedor.html("");
            var html = "<h3>" + data.nombre + "</h3>";
            html +="<div class=table-responsive>";
            html += "<table class='table table-bordered'><tr>";
            html +="<thead>";
            html += "<th>Parámetro</th>";
            html += "<th>Descripción</th>";
            html += "<th>Tipo</th>";
            html += "<th>Valor</th>";
            html += "<@l.hasRole permission='MODIFICAR_PARAMETRO'><th>Opción</th></@l.hasRole>";
            html += "</thead>";
            html += "</tr>";
            html +="</div>"
            $.each(data.parametros, function(i, param) {
              html += "<tr>";
              html += "<td>" + param.nombre + "</td>";
              html += "<td>" + param.descripcion + "</td>";
              html += "<td>" + param.tipo + "</td>";
              if (typeof param.valores[0] == 'object')
                if (param.tipo == "File") {
                  html += "<td><a class='btn btn-primary' href=\"<@s.url controllerStatics.CONTROLLER_PATH_DOWNLOAD + '/" + param.valores[0].id + "'/>\" target='_blank'>Descargar</a></td>";
                } else if (param.tipo == "Object") {
                  html += "<td></td>";
                } else {
                  html += "<td>" + param.valores[0].valor + "</td>";
                }
              else
                html += "<td></td>";
              html += "<@l.hasRole permission='MODIFICAR_PARAMETRO'><td><a class='btn btn-primary' href='<@s.url controllerStatics.CONTROLLER_PATH_EDIT + '/" + param.clave + "' />'>Editar</a></td></@l.hasRole>";
              html += "</tr>";
            });
            html += "</table>";
            divContenedor.html(html);
        });
      }
    -->
  </script>
</@l.script>
<#macro construirArbolCategorias rama>
  <li>
    <a style="cursor: pointer;" onclick="verParametros('${rama.clave}')">${(rama.nombre)!""}</a>
    <#if (rama.categorias?? && rama.categorias?size != 0)>
      <ul>
        <#list rama.categorias as child>
          <@construirArbolCategorias child />
        </#list>
      </ul>
    </#if>
  </li>
</#macro>
</@l.main>
