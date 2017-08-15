<@l.main>
<div class="well sipa-well">
  <div class="container-fluid">
    <div class="col-md-9">
    <h1>Usuarios</h1>
    <h3>Crear registro de nuevo usuario</h3>
    <br>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    <#else>
    <div>
      <form  action="<@s.url '/usuario/create'/>" id="formCrearUsuario" method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <fieldset>
          <legend>Datos de la cuenta de usuario</legend>
          <div class="form-group">
            <label for="descripcion"><span class="sipa-red">*</span> Tipo de usuario</label><br>
            <@s.bind "usuarioForm.id"/>
            <input type="hidden" class="form-control" name="${s.status.expression}" value="${(usuarioForm.id)!''}" id="id"/>
            <input type="radio" name="tipoUsuario" value="activeDirectory" <#if tipoUsuario??><#if tipoUsuario="activeDirectory"> checked </#if><#else>checked</#if>> Del directorio activo &nbsp;&nbsp;
            <input type="radio" name="tipoUsuario" value="usuarioSistema" <#if tipoUsuario??><#if tipoUsuario="usuarioSistema"> checked </#if></#if>> Exclusivo del sistema
          </div>
          <div class="form-group">
            <input type="hidden" class="form-control" name="username" value="${(usuarioForm.username)!''}" id="username" autocomplete="off"/>
            <input type="hidden" class="form-control" name="password" value="${(usuarioForm.password)!''}" id="password" autocomplete="off"/>
          </div>
          <#if tipoUsuario??>
          <#if tipoUsuario="activeDirectory">
          <div id="divUsernameAD">
          <#else>
          <div id="divUsernameAD" class="sipa-display">
          </#if>
          <#else>
          <div id="divUsernameAD">
          </#if>
            <div class="form-group">
              <label for="username"><span class="sipa-red">*</span> Usuario del directorio activo</label>
            </div>
            <div class="row">
                <div class="col-xs-6">
                  <input type="text" class="form-control" value="${(usuarioForm.username)!''}" id="usernameAd" autocomplete="off" maxlength="${(constants.VALOR_MAXIMO_USERNAME)!''}" size="${(constants.VALOR_MAXIMO_USERNAME)!''}"
                  placeholder="Buscar Nombre de Usuario" />
                  <@s.bind "usuarioForm.username"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                  <input type="hidden" class="form-control" value="${(usuarioForm.password)!''}" id="passwordAd" autocomplete="off"/>
                </div>
                <div class="col-xs-6">
                  <button class="btn btn-primary" type="button" id="btnBuscarUsuarioAd">Buscar</button>
                </div>
            </div>
          </div>
          <div class="form-group" id="divMsjeUserAd"></div>
          <#if tipoUsuario??>
          <#if tipoUsuario="usuarioSistema">
          <div id="divUsernameBD">
          <#else>
          <div id="divUsernameBD" class="sipa-display">
          </#if>
          <#else>
          <div id="divUsernameBD" class="sipa-display">
          </#if>
            <div class="form-group">
              <label for="username"><span class="sipa-red">*</span> Nombre de usuario</label>
            </div>
            <div class="row">
                <div class="col-xs-6">
                  <input type="text" class="form-control" value="${(usuarioForm.username)!''}" id="usernameBd" autocomplete="off" maxlength="${(constants.VALOR_MAXIMO_USERNAME)!''}" size="${(constants.VALOR_MAXIMO_USERNAME)!''}"/>
                  <@s.bind "usuarioForm.username"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
                <div class="col-xs-6">
                  <button class="btn btn-primary" type="button" id="btnBuscarUsuarioBd">Buscar</button>
                </div>
            </div>
            <div class="form-group" id="divMsjeUserBd"></div>
            <div class="form-group">
              <label for="password"><span class="sipa-red">*</span> Contraseña</label>
              <input type="password" class="form-control" value="" maxlength="${(constants.VALOR_MAXIMO_PASSWORD)!''}" size="${(constants.VALOR_MAXIMO_PASSWORD)!''}" id="passwordBd" autocomplete="off"/>
              <@s.bind "usuarioForm.password"/>
              <div class="has-error">
                <span class="help-block"><@s.showErrors "<br>"/></span>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label for="activo"><span class="sipa-red">*</span> Estado</label>
            <@s.bind "usuarioForm.activo"/>
            <select name="${s.status.expression}" class="form-control">
              <option value="">Seleccione un valor</option>
              <option value="true" <#if usuarioForm.activo??><#if usuarioForm.activo>selected</#if></#if>>Activo</option>
              <option value="false" <#if usuarioForm.activo??><#if !usuarioForm.activo>selected</#if></#if>>Inactivo</option>
            </select>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="fechaCaducidad"><span class="sipa-red">*</span> Fecha de caducidad</label>
            <@s.bind "usuarioForm.fechaCaducidad"/>
            <input type="text" class="form-control datetimepickerVigencia" name="${s.status.expression}" value="${(usuarioForm.fechaCaducidad?string[constants.DATE_TIME_FORMAT_PATTERN])!''}" id="fechaCaducidad" <#if tipoUsuario??><#if tipoUsuario="activeDirectory"> readonly </#if><#else>readonly</#if>/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="dependencia.id">Dependencia</label>
            <@s.bind "usuarioForm.dependencia.id"/>
            <@s.formSingleSelect "usuarioForm.dependencia.id", dependencias, " class='form-control dependencia'"/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
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
            <@s.formSingleSelect "usuarioForm.perfil.id", perfiles, " id ='perfil' class='form-control perfil'"/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
            <label for="autorizador.id">Jefe que autoriza la operación</label>
            <@s.bind "usuarioForm.autorizador.id"/>
            <@s.formSingleSelect "usuarioForm.autorizador.id", personas, " class='chosen-select sipa-select'"/>
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
          <div class="form-group">
            <label for="firma">Firma</label>
            <@s.bind "usuarioForm.firma"/>
            <input type="file" class="form-control" name="imgFirma" value="" id="imgFirma" accept="image/*"/>
            <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
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
        <button class="btn btn-primary" type="button" id="btnCrearUsuario">Guardar</button>
      </form>
    </div>
  </div>
</div>
</div>
<@l.script>
  <script type="text/javascript">
    <!--
      $("#btnCrearUsuario").click(function() {
          var $form = $('#formCrearUsuario');
          if ($("input[name=tipoUsuario]:checked").val() == "activeDirectory") {
            $("#username").val($("#usernameAd").val());
            $("#password").val($("#passwordAd").val());
          } else {
            $("#username").val($("#usernameBd").val());
            $("#password").val($("#passwordBd").val());
          }
          $form.submit();
      });

      $("input[name=tipoUsuario]").click(function() {
          if($(this).val() == 'activeDirectory') {
            $("#divUsernameAD").show();
            $("#divUsernameBD").hide();
            $("#divMsjeUserAd").html("");
            $("#divMsjeUserBd").html("");
            $("#usernameAd").val("");
            $('#fechaCaducidad').prop('readonly', true);
          } else {
            $("#divUsernameAD").hide();
            $("#divUsernameBD").show();
            $("#divMsjeUserAd").html("");
            $("#divMsjeUserBd").html("");
            $("#usernameBd").val("");
            $('#fechaCaducidad').prop('readonly', false);
          }
      });

      //Funcionalidad para validar si el usuario ya existe
      $("#btnBuscarUsuarioBd").click(function() {
        var username = $("#usernameBd").val();
        $("#divMsjeUserBd").html("");
        if(username!=''){
          var url = "<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_USERNAME />/" + username;
            $.get(url, function(userData) {
                if(typeof userData == "object"){
                  $("#usernameBd").val("");
                  $("#divMsjeUserBd").html(
                    '<div class="alert alert-danger">' +
                      username + ' ya se encuentra registrado' +
                    '</div>'
                  );
                } else {
                  $("#divMsjeUserBd").html(
                    '<div class="alert alert-success">' +
                      username + ' se encuentra disponible para registro' +
                    '</div>'
                  );
                }

            });
          }
      });
      //Funcionalidad para obtener el usuario del directorio activo
      $("#btnBuscarUsuarioAd").click(function() {
          var username = $("#usernameAd").val();
        $("#divMsjeUserAd").html("");
        if(username!=''){
          var url = "<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_LDAP />/" + username;
            $.get(url, function(userData) {
                if(typeof userData == "object"){
                  $("#divMsjeUserAd").html(
                    '<div class="alert alert-success">' +
                      username + ' se encuentra disponible para registro' +
                    '</div>'
                  );
                  $("#passwordAd").val(userData.userPassword.replace("{SHA}", ""));
                  $("#fechaCaducidad").val(userData.pwdAccountLockedTime);
                  var split = userData.sn.split(" ");
                  if (split.length == 4) {
                    $("#persona_primerNombre").val(split[0]);
                    $("#persona_segundoNombre").val(split[1]);
                    $("#persona_primerApellido").val(split[2]);
                    $("#persona_segundoApellido").val(split[3]);
                  } else if (split.length == 3) {
                    $("#persona_primerNombre").val(split[0]);
                    $("#persona_segundoNombre").val(split[1]);
                    $("#persona_primerApellido").val(split[2]);
                  } else if (split.length == 2) {
                    $("#persona_primerNombre").val(split[0]);
                    $("#persona_primerApellido").val(split[1]);
                  } else {
                    $("#persona_primerNombre").val(split[0]);
                  }
                } else {
                  $("#usernameAd").val("");
                  $("#divMsjeUserAd").html(
                    '<div class="alert alert-danger">' +
                      username + ' ya se encuentra registrado en la base de datos o no se encuentra en el directorio activo' +
                    '</div>'
                  );
                }
            });
          } else {
            //En caso de que el usuario este vacio
          }
      });
-->
  </script>
</@l.script>
</#if>

</@l.main>
