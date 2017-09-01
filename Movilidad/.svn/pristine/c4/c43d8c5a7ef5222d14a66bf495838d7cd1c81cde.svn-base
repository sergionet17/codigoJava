<@l.main>
<div class="well sipa-well">
  <div class="container">
    <div class="col-md-9">
    <h1>Roles</h1>
    <#if (editar == 1)>
      <h3>Editar registro de rol</h3>
    <#else>
      <h3>Crear registro de nuevo rol</h3>
    </#if>
    <br>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    </#if>
      <div>
        <form  action="<@s.url '/rol'/>" id="formCrearRol" method="post" >
          <fieldset>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              <div class="form-group">
                <label for="name"><span class="sipa-red">*</span> Nombre</label>
                <input type="hidden" class="form-control" name="editar" value="${editar}" id="editar"/>
                <@s.bind "rolForm.id"/>
                <input type="hidden" class="form-control" name="${s.status.expression}" value="${(rolForm.id)!''}" id="id"/>
                <@s.bind "rolForm.activo"/>
                <input type="hidden" class="form-control" name="${s.status.expression}" 
                  value="<#if (rolForm.activo)??>${rolForm.activo?string('true','false')}<#else>true</#if>" id="id"/>
                <@s.bind "rolForm.name"/>
                <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_NOMBRE}" "" "textValidator" "${(rolForm.name)!''} " />
                <div class="has-error">
                  <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
              </div>
              <div class="form-group">
                <label for="descripcion">Descripción</label>
                <@s.bind "rolForm.descripcion"/>
                <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_DESCRIPCION}" "" "textValidator" "${(rolForm.descripcion)!''}" />
                <div class="has-error">
                  <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
              </div>
              <div class="form-group">
                <label for="permisos"><span class="sipa-red">*</span> Permisos</label>
                <input type="hidden" class="form-control" name="permisosAsString" id="permisos"/>
                <@s.bind "rolForm.permisos"/>
                <div class="has-error">
                  <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
                <div class="row">
                  <div class="col-xs-5">
                      <select name="permisosLista" class="multiselect form-control" size="8" multiple="multiple" data-right="#permisosSeleccionados" data-right-all="#right_All_1" data-right-selected="#right_Selected_1" data-left-all="#left_All_1" data-left-selected="#left_Selected_1">
                        <#if permisos??>
                          <#list permisos as permiso>
                            <option value="${permiso.nombre}"[@s.checkSelected value/]>${permiso.nombre}${(' - ' + permiso.descripcion)!""}</option>
                          </#list>
                        </#if>
                      </select>
                  </div>
                  
                  <div class="col-xs-2 sipa-p-0">
                      <button type="button" id="right_All_1" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
                      <button type="button" id="right_Selected_1" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
                      <button type="button" id="left_Selected_1" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
                      <button type="button" id="left_All_1" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
                  </div>
                  
                  <div class="col-xs-5">
                      <select name="permisosSeleccionados" id="permisosSeleccionados" class="form-control" size="8" multiple="multiple">
                        <#if permisosSeleccionados??>
                          <#list permisosSeleccionados as permiso>
                            <option value="${permiso.nombre}"[@s.checkSelected value/]>${permiso.nombre}${(' - ' + permiso.descripcion)!""}</option>
                          </#list>
                        </#if>
                      </select>
                  </div>
              </div>
            </div>
            <div class="sipa-bottom-10"><@s.message 'obligatorio.campos'/>
            </div>
            <button class="btn btn-primary" type="button" id="btnCrearRol">Guardar</button>
          </fieldset>
        </form>
      </div>
    </div>
  </div>
</div>
<@l.script>
  <script type="text/javascript">
    <!-- 
      $("#btnCrearRol").click(function() {
          var $form = $('#formCrearRol');
          var permisos = '';
          $('#permisosSeleccionados option').each(function(){
              if (permisos == '')
                permisos = $(this).val();
              else
                permisos = permisos + ',' + $(this).val();
          }); 
          $('#permisos').val(permisos);
          $form.submit();
      });
    -->
  </script>
</@l.script>
</@l.main>