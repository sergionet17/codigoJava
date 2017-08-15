<@l.main>
<div class="well sipa-well">
  <div class="container-fluid">
    <div class="col-md-9">
    <h1>Usuarios</h1>
    <h3>Editar registro de usuario</h3>
    <br>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    <#else>
    <div>
      <form  action="<@s.url '/usuario/edit'/>" id="formEditarUsuario" method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="tipoUsuario" value="${tipoUsuario}" id="tipoUsuario"/>
        <@s.bind "usuarioForm.id"/>
        <input type="hidden" name="${s.status.expression}" value="${(usuarioForm.id)!''}"/>
        <fieldset>
          <legend>Datos de la cuenta de usuario</legend>
          <div class="form-group">
            <#if tipoUsuario = "activeDirectory">
              <label for="username"><span class="sipa-red">*</span> Usuario del directorio activo</label>
            <#else>
              <label for="username"><span class="sipa-red">*</span> Nombre de usuario</label>
            </#if>
            <@s.bind "usuarioForm.username"/>
            <input type="text" class="form-control" name="${s.status.expression}" value="${(usuarioForm.username)!''}" id="username" autocomplete="off" readonly/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
            <input type="hidden" name="password" value="${(usuarioForm.password)!''}" id="password" autocomplete="off"/>
            <input type="hidden" name="passwordAnterior" value="${passwordAnterior}" id="passwordAnterior" autocomplete="off"/>
            <input type="hidden" name="cambioPassword" value="NO" id="cambioPassword" autocomplete="off"/>
          </div>
          <#if tipoUsuario = "usuarioSistema">
            <div class="form-group">
                <label><span class="sipa-red">*</span> Contraseña</label>
                <input type="password" class="form-control" value="" id="passwordBd" autocomplete="off" maxlength="${(constants.VALOR_MAXIMO_PASSWORD)!''}" size="${(constants.VALOR_MAXIMO_PASSWORD)!''}" autocomplete="off"/>
                <@s.bind "usuarioForm.password"/>
                <div class="has-error">
                  <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
            </div>
          </#if>
          <div class="form-group">
            <label for="activo"><span class="sipa-red">*</span> Estado</label>
            <@s.bind "usuarioForm.activo"/>
            <select name="${s.status.expression}" class="form-control">
              <option value="">Seleccione un valor</option>
              <option value="true" <#if usuarioForm.activo>selected</#if>>Activo</option>
              <option value="false" <#if !usuarioForm.activo>selected</#if>>Inactivo</option>
            </select>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="fechaCaducidad"><span class="sipa-red">*</span> Fecha de caducidad</label>
            <@s.bind "usuarioForm.fechaCaducidad"/>
            <input type="text" class="form-control datetimepickerVigencia" name="${s.status.expression}" value="${(usuarioForm.fechaCaducidad?string[constants.DATE_TIME_FORMAT_PATTERN])!''}" id="fechaCaducidad" <#if tipoUsuario="activeDirectory"> readonly </#if>/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <@s.bind "usuarioForm.dependencia.id"/>
            <@l.selectorDependencias dependencias "${s.status.expression}" "${(usuarioForm.dependencia.id)!''}" "${(usuarioForm.dependencia.nombre)!''}"/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
         </div>
          <div class="form-group">
            <label for="cargo"><span class="sipa-red">*</span> Cargo</label>
            <@s.bind "usuarioForm.cargo"/>
            <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_CARGO}" "" "textValidator" "${(usuarioForm.cargo)!''}" />
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="email"><span class="sipa-red">*</span> Email</label>
            <@s.bind "usuarioForm.email"/>
            <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_EMAIL}" "" "textValidator" "${(usuarioForm.email)!''}" />
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="perfil"><span class="sipa-red">*</span> Perfil</label>
            <@s.bind "usuarioForm.perfil.id"/>
            <@s.formSingleSelect "usuarioForm.perfil.id", perfiles, " class='form-control perfil'"/> 
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="autorizador.id">Jefe que autoriza la operación</label>
            <@s.bind "usuarioForm.autorizador.id"/>
            <@s.formSingleSelect "usuarioForm.autorizador.id", personas, " class='chosen-select'"/> 
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="documento">Documento de soporte</label>
            <@s.bind "usuarioForm.documentoSoporte"/>
            <input type="file" class="form-control" name="documento" value="" id="documento" accept="application/pdf" />
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <#if usuarioForm.documentoSoporte??>
          <div class="form-group">
            <a class="btn btn-primary" href="<@s.url '/documento/download/' + usuarioForm.documentoSoporte.id/>" target="_blank">Descargar documento</a>
          </div>
          </#if>
          <div class="form-group">
            <label for="firma">Firma</label>
            <@s.bind "usuarioForm.firma"/>
            <input type="file" class="form-control" name="imgFirma" value="" id="imgFirma" accept="image/*"/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
            <input type="hidden" class="form-control" name="displayFirma" value="${displayFirma}" id="displayFirma"/>
          </div>
          <#if displayFirma = "si">
          <div class="form-group">
            <img src="<@s.url '/usuario/firma/'+ usuarioForm.id/>" alt="firma"/>
          </div>
          </#if>
        </fieldset>
        <fieldset>
          <legend>Datos personales</legend>
          <div class="form-group">
            <label for="persona.tipoPersona"><span class="sipa-red">*</span> Tipo de persona</label>
            <@s.bind "usuarioForm.persona.tipoPersona.id"/>
            <@s.formSingleSelect "usuarioForm.persona.tipoPersona.id", tipospersona, " id ='tipoPersona' class='form-control tipoPersona'"/> 
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="persona.tipoDocumentoIdentidad.id"><span class="sipa-red">*</span> Tipo de documento</label>
            <@s.bind "usuarioForm.persona.tipoDocumentoIdentidad.id"/>
            <@s.formSingleSelect "usuarioForm.persona.tipoDocumentoIdentidad.id", tiposdocumento, " id ='tipodocumento' class='form-control tipodocumento'"/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="persona.numeroDocumentoIdentidad"><span class="sipa-red">*</span> Número de documento</label>
            <@s.bind "usuarioForm.persona.numeroDocumentoIdentidad"/>
            <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_NUMERO_DOCUMENTO}" "" "textValidator" "${(usuarioForm.persona.numeroDocumentoIdentidad)!''}" />
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="persona.primerNombre"><span class="sipa-red">*</span> Primer nombre</label>
            <@s.bind "usuarioForm.persona.primerNombre"/>
            <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_NOMBRE}" "" "textValidator" "${(usuarioForm.persona.primerNombre)!''}" "" "" />
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="persona.segundoNombre">Segundo nombre</label>
            <@s.bind "usuarioForm.persona.segundoNombre"/>
            <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_NOMBRE}" "" "textValidator" "${(usuarioForm.persona.segundoNombre)!''}" "" "" />
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="persona.primerApellido"><span class="sipa-red">*</span> Primer apellido</label>
            <@s.bind "usuarioForm.persona.primerApellido"/>
            <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_NOMBRE}" "" "textValidator" "${(usuarioForm.persona.primerApellido)!''}" "" "" />
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="persona.segundoApellido">Segundo apellido</label>
            <@s.bind "usuarioForm.persona.segundoApellido"/>
            <@l.inputText "${s.status.expression}" "${constants.VALOR_MAXIMO_NOMBRE}" "" "textValidator" "${(usuarioForm.persona.segundoApellido)!''}" "" "" />
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="sipa-bottom-10"><@s.message 'obligatorio.campos'/>
          </div>
        <button class="btn btn-primary" type="button" id="btnEditarUsuario">Guardar</button>
      </form>
    </div>
  </div>
</div>
<@l.script>
  <script type="text/javascript">
    <!-- 
      $("#btnEditarUsuario").click(function() {
          var $form = $('#formEditarUsuario');
          if ($("#tipoUsuario").val() == "usuarioSistema") {
            if($("#passwordBd").val() != "") {
              $("#password").val($("#passwordBd").val());
              $("#cambioPassword").val("SI");
            }
          }
          $form.submit();
      });
-->
  </script>
</@l.script>
</#if>
</@l.main>