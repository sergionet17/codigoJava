<@l.main>
<div class="well sipa-well">
<div class="container">
<div class="col-xs-12 col-md-9">
<h1>Perfiles</h1>
<#if (editar == 1)>
  <h3>Editar registro de perfil</h3>
<#else>
  <h3>Crear registro de nuevo perfil</h3>
</#if>
<br>
<#if error??>
  <div class="has-error">
      <span  class="help-block">${error}</span>
  </div>
</#if>
<div>
  <form  action="<@s.url '/perfil'/>" id="formCrearPerfil" method="post" >
    <fieldset>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
          <label for="nombre"><span class="sipa-red">*</span> Nombre</label>
          <input type="hidden" class="form-control" name="editar" value="${editar}" id="editar"/>
          <@s.bind "perfilForm.id"/>
          <input type="hidden" class="form-control" name="${s.status.expression}" value="${(perfilForm.id)!''}" id="id"/>
          <@s.bind "perfilForm.activo"/>
          <input type="hidden" class="form-control" name="${s.status.expression}" 
            value="<#if (perfilForm.activo)??>${perfilForm.activo?string('true','false')}<#else>true</#if>" id="id"/>
          <@s.bind "perfilForm.nombre"/>
          <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_NOMBRE}" "" "textValidator" "${(perfilForm.nombre)!''}" />
          <div class="has-error">
            <span class="help-block"><@s.showErrors "<br>"/></span>
          </div>
        </div>
        <div class="form-group">
          <label for="roles"><span class="sipa-red">*</span> Roles</label>
          <input type="hidden" class="form-control" name="rolesAsString" id="roles"/>
          <@s.bind "perfilForm.roles"/>
          <div class="has-error">
            <span class="help-block"><@s.showErrors "<br>"/></span>
          </div>
          <div class="row">
            <div class="col-xs-5">
                <select name="rolesLista" class="multiselect form-control" size="8" multiple="multiple" data-right="#rolesSeleccionados" data-right-all="#right_All_1" data-right-selected="#right_Selected_1" data-left-all="#left_All_1" data-left-selected="#left_Selected_1">
                  <#if roles??>
                    <#list roles as rol>
                      <option value="${rol.id}"[@s.checkSelected value/]>${rol.name}</option>
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
                <select name="rolesSeleccionados" id="rolesSeleccionados" class="form-control" size="8" multiple="multiple">
                  <#if rolesSeleccionados??>
                    <#list rolesSeleccionados as rol>
                      <option value="${rol.id}"[@s.checkSelected value/]>${rol.name}</option>
                    </#list>
                  </#if>
                </select>
            </div>
        </div>
      </div>
      <div class="sipa-bottom-10"><@s.message 'obligatorio.campos'/>
      </div>
      <button class="btn btn-primary" type="button" id="btnCrearPerfil">Guardar</button>
    </fieldset>
  </form>
</div>
</div>
</div>
<@l.script>
  <script type="text/javascript">
    <!-- 
      $("#btnCrearPerfil").click(function() {
          var $form = $('#formCrearPerfil');
          var roles = '';
          $('#rolesSeleccionados option').each(function(){
              if (roles == '')
                roles = $(this).val();
              else
                roles = roles + ',' + $(this).val();
          }); 
          $('#roles').val(roles);
          $form.submit();
      });
    -->
  </script>
</@l.script>
</@l.main>