<@l.main>
<div class="well sipa-well">
  <div class="container">
    <div class="col-md-9">
    <h1>Dependencias</h1>
    <#if (editar == 1)>
      <h3>Editar registro de dependencia</h3>
    <#else>
      <h3>Crear registro de nueva dependencia</h3>
    </#if>
    <br>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    </#if>
    <div>
      <form  action="<@s.url '/dependencia'/>" id="formCrearDependencia" method="post" >
        <fieldset>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
              <label for="name"><span class="sipa-red">*</span> Nombre</label>
              <input type="hidden" class="form-control" name="editar" value="${editar}" id="editar"/>
              <@s.bind "dependenciaForm.id"/>
              <input type="hidden" class="form-control" name="${s.status.expression}" value="${(dependenciaForm.id)!''}" id="id"/>
              <@s.bind "dependenciaForm.nombre"/>
              <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_DEPENDENCIA}" "" "textValidator" "${(dependenciaForm.nombre)!''}" />
              <div class="has-error">
                <span class="help-block"><@s.showErrors "<br>"/></span>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label for="dependencia.id">Dependencia a la que pertenece</label>
            <@s.bind "dependenciaForm.dependencia.id"/>
            <@s.formSingleSelect "dependenciaForm.dependencia.id", dependencias, " class='chosen-select'"/> 
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="responsable.id">Responsable</label>
            <@s.bind "dependenciaForm.responsable.id"/>
            <@s.formSingleSelect "dependenciaForm.responsable.id", usuarios, " class='chosen-select usuario'"/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="sipa-bottom-10"><@s.message 'obligatorio.campos'/>
          </div>
          <button class="btn btn-primary" type="button" id="btnCrearDependencia">Guardar</button>
        </fieldset>
      </form>
    </div>
  </div>
</div>
<@l.script>
  <script type="text/javascript">
    <!--
      $("#btnCrearDependencia").click(function() {
          var $form = $('#formCrearDependencia');
          //Se realiza validacion del usuario
          var usuario = $(".usuario").val();
          if(usuario != "") {
            var dependenciaId = $("#id").val();
            var valor = 0;
            if (dependenciaId != "")
              valor = dependenciaId
            var url = "<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_USER_VALID />/" + usuario + "/" + valor;
             $.get(url, function(data) {
                $('#cuerpoMensaje').html("");
                if (data != "") {
                  confirmacion(
                    data,
                    function() {
                      $form.submit();
                    }
                  );
                } else {
                  $form.submit();
                }
            });
          } else {
            $form.submit();
          }
      });
    -->
  </script>
</@l.script>
</@l.main>
