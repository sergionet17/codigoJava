<#--
Propuesta gráfica o diseño número 1.
Envuelve el contenido en la plantilla principal del sistema.
-->
<#macro layout>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><@s.message "app.title"/></title>
    <!-- Bootstrap core CSS -->
    <link href="<@s.url '/static/bootstrap/3.3.7/dist/css/bootstrap.min.css' />" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="<@s.url '/static/bootstrap/3.3.7/dist/css/bootstrap-theme.min.css' />" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!--<link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">-->
    <!-- Datetime Picker -->
    <link href="<@s.url '/static/datetimepicker/2.5.4/jquery.datetimepicker.min.css' />" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- <script src="docs/assets/js/ie-emulation-modes-warning.js"></script>-->
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="<@s.url '/webjars/jquery-ui/1.12.1/jquery-ui.min.css' />">
    <link rel="stylesheet" href="<@s.url '/static/chosen/v1.7.0/bootstrap-chosen.css' />">

  </head>
  <body>
    <div class="container-fluid">
      <div class="row sipa-fondo-1 ">
        <div class="col-md-2 sipa-fondo-1 sipa-p-0">
          <a href="<@s.url '/'/>">
            <img id="sipa-logo" class="img-responsive"
            src="<@s.url '/static/img/logo-alcaldia.png' />" >
          </a>
        </div>
        <div class="col-md-10 sipa-p-0">
          <div id="sipa-top" class="sipa-fondo-1">
            <div>
              <h2 class="sipa-m-0">
              <@s.message 'app.secretaria'/><br/>
              </h2>
              <h3 class="sipa-m-0">
              <@s.message 'app.title'/> - <@s.message 'app.title.descripcion'/>
              </h3>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="navbar navbar-default" role="navigation">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
            <span class="sr-only"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            </button>
          </div>
          <div class="navbar-collapse collapse sidebar-navbar-collapse sipa-p-0">
            <ul class="nav navbar-nav">
              <li class="visible-sm visible-md visible-lg"><a href="#" id="sipa-orb"><span class="glyphicon glyphicon-menu-hamburger sipa-hamburger" aria-hidden="true"></span></a></li>
              <li class="sipa-ml-mr-3"><a href="/sipa" class="btn btn-danger">Tablero de Control</a></li>
              <li role="presentation" class="sipa-ml-mr-3 dropdown"><a href="#" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Bandejas <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="<@s.url '/bandeja/reincidencias' />">Tareas</a></li>
                <li><a href="/sipa/construccion">Correspondencia</a></li>
                <li><a href="/sipa/construccion">Mensajes</a></li>
                <#-- <li><a href="#">Expediente</a></li> -->
              </ul></li>
              <li role="presentation" class="sipa-ml-mr-3 dropdown"><a href="#" class="btn btn-success dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Consultas <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="<@s.url '/comparendo/consulta' />">Comparendos</a></li>
                <li><a href="<@s.url '/persona/consulta' />">Personas</a></li>
                <li><a href="<@s.url '/vehiculo/consulta' />">Vehiculos</a></li>
                <#-- <li><a href="#">Expediente</a></li> -->
              </ul></li>
            </ul>
            <ul>
              <li class="sipa-ml-mr-3 dropdown sipa-li">
                    <a href="#" class="dropdown-toggle btn sipa-user" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${(usuario.email)!'Usuario'} <span class="caret"></span></a>
                    <ul class="dropdown-menu sipa-menu-user">
                      <li><a href="#">Cambiar contraseña</a></li>
                      <li><a href="#">Preferencias</a></li>
                      <li><a href="<@s.url '/navigation/mapa' />">Mapa</a></li>
                      <li role="separator" class="divider"></li>
                      <li><a href="#" onclick="document.getElementById('logoutform').submit();">Salir</a></li>
                      <@l.form action="/logout" id="logoutform">
                      </@l.form>
                    </ul>
                </li>
            </ul>
            <div class="visible-xs">
              <ul class="sipa-menu-list-xs nav navbar-nav">
                <li><a href="#" class="btn btn-primary">Menú</a></li>
                <@l.hasRole permission="ADMIN_ROL">
                <li><a href="<@s.url '/rol'/>" class="btn btn-info">Administrar roles</a></li>
                </@l.hasRole>
                <@l.hasRole permission="ADMIN_PERFIL">
                <li><a href="<@s.url '/perfil'/>" class="btn btn-info">Administrar perfiles</a></li>
                </@l.hasRole>
                <@l.hasRole permission="ADMIN_USUARIO">
                <li><a href="<@s.url '/usuario'/>" class="btn btn-info">Administrar usuarios</a></li>
                </@l.hasRole>
                <@l.hasRole permission="ADMIN_DEPENDENCIA">
                <li><a href="<@s.url '/dependencia'/>" class="btn btn-info">Administrar dependencias</a></li>
                </@l.hasRole>
                <@l.hasRole permission="ADMIN_PARAMETRO">
                <li><a href="<@s.url '/parametro'/>" class="btn btn-info">Parámetros de proceso</a></li>
                </@l.hasRole>
                <@l.hasRole permission="ADMIN_TABLA_REFERENCIA">
                <li><a href="<@s.url '/tablaReferencia'/>" class="btn btn-info">Administrar tablas de referencia</a></li>
                </@l.hasRole>
                <li><a href="/sipa/construccion" class="btn btn-info">Anulación rangos de numeración de comparendos</a></li>
                 <li><a href="/inconsistencias/consulta" class="btn btn-info">Comparendos e inconsistencias</a></li>
                <li><a href="/rangos" class="btn btn-info">Registro rango de numeración de comparendo</a></li>
                 <li><a href="/evidenciaAnulacion" class="btn btn-info">Registrar la evidencia de solicitud <br>de rangos de numeración</a></li>
                <li><a href="/rangosAnulacion" class="btn btn-info">Anulación rangos de numeración de comparendos</a></li>
                <li><a href="<@s.url '/cursos'/>" class="btn btn-info">Registro de cursos</a></li>
                <li><a href="<@s.url '/curso_asistencia'/>" class="btn btn-info">Registrar Asistencia a curso pedagógico</a></li>
                <li><a href="/sipa/agendamiento-audiencia" class="btn btn-info">Parametrizar los periodos de tiempo para audiencias <br> de continuación</a></li>
                <li><a href="/turno-firma/new" class="btn btn-info">Asignación de horarios para firma</a></li>
                <li><a href="/sipa/financiero/pagos/noaplicados" class="btn btn-info">Pagos no aplicados</a></li>
                <li><a href="/sipa/financiero/pagos" class="btn btn-info">Cargar archivo de pagos</a></li>
                <li><a href="/sipa/construccion" class="btn btn-info">Realizar devolución</a></li>
                <li><a href="/sipa/construccion" class="btn btn-info">Recursos de apelación pendientes de fallo</a></li>
              </ul>
            </div>
          </div>
          <div id="sipa-orb-menu" class="hidden">
            <@orbMenu/>
          </div>
        </div>
      </div>
    </div>
    <div class="container-fluid">
      <#if FLASH_MESSAGE_OK??>
      <div class="alert alert-success"><p>${FLASH_MESSAGE_OK}</p></div>
      </#if>
      <#if FLASH_MESSAGE_ERROR??>
      <div class="alert alert-danger"><p>${FLASH_MESSAGE_ERROR}</p></div>
      </#if>
      <#nested/>
    </div>
    <div id="dvConfirmacion" title='<@s.message "app.title"/>'></div>
    <#if _HTML??>${_HTML}</#if>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<@s.url '/webjars/jquery/1.12.4/jquery.min.js' />"></script>
    <script src="<@s.url '/webjars/jquery-ui/1.12.1/jquery-ui.min.js' />"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<@s.url '/static/bootstrap/3.3.7/dist/js/bootstrap.min.js' />"></script>
    <script src="<@s.url '/static/datetimepicker/2.5.4/jquery.datetimepicker.full.min.js' />"></script>
    <script src="<@s.url '/static/multiselect/v2.3.12/multiselect.min.js' />"></script>
    <script src="<@s.url '/static/chosen/v1.7.0/chosen.jquery.min.js' />"></script>

    <script src="<@s.url '/static/schedule/3_0_1/aui/aui-min.js' />"></script>
    <script>
    $( function() {
    // Configura los calendarios fecha y hora
    $('.datetimepicker').datetimepicker({
    <#if constants??>
    format: '${constants.JQUERY_DATETIME_FORMAT_PATTERN}',
    </#if>
    mask: true
    });
    // Configura los calendarios fecha vigencia
    $('.datetimepickerVigencia').datetimepicker({
    <#if constants??>
    format: '${constants.JQUERY_DATETIME_FORMAT_PATTERN}',
    </#if>
    mask: true,
    startDate:new Date(),
    minDate:0
    });
    // Configura los calendarios fecha vigencia
    $('.datepickerVigencia').datetimepicker({
    <#if constants??>
    format: '${constants.JQUERY_DATE_FORMAT_PATTERN}',
    </#if>
    timepicker:false,
    mask: true,
    startDate:new Date(),
    minDate:0
    });
    // Configura los calendarios fecha
    $('.datepicker').datetimepicker({
    <#if constants??>
    format: '${constants.JQUERY_DATE_FORMAT_PATTERN}',
    </#if>
    timepicker:false,
    mask: true
    });
    //Configura los calendarios hora
    $('.timepicker').datetimepicker({
    <#if constants??>
    format: '${constants.JQUERY_TIME_FORMAT_PATTERN}',
    </#if>
    datepicker:false,
    mask: true,
    });
    // Evita que al oprimir el enter en el campo de calendario se envíe el formulario
    // TODO: Hacer lo mismo con todos los campos INPUT
    $('.datetimepicker').keydown(function( event ) {
    if ( event.which == 13 ) {
    event.preventDefault();
    }
    });
    $('.datepicker').keydown(function( event ) {
    if ( event.which == 13 ) {
    event.preventDefault();
    }
    });
    $('.timepicker').keydown(function( event ) {
    if ( event.which == 13 ) {
    event.preventDefault();
    }
    });
    // Configura el evento HOVER sobre el orbe que abre el menú
    $("#sipa-orb").hover(
    function(){
    $("#sipa-orb-menu").toggleClass("hidden");
    $("#sipa-orb-menu").width($(window).width()-30);
    },
    function(){ }
    );
    $("#sipa-orb-menu").hover(
    function(){ },
    function(){ $("#sipa-orb-menu").toggleClass("hidden"); },
    );
    $('.multiselect').multiselect();
    $('.chosen-select').chosen();
    $('.chosen-select-deselect').chosen({ allow_single_deselect: true });
    });
    //Mensaje de confirmacion
    function confirmacion(mensaje, funcionalidadSI, funcionalidadNO) {
    $("#dvConfirmacion").html(mensaje);
    $('#dvConfirmacion').dialog({
    closeOnEscape: true,
    draggable: false,
    show: {effect: "fade"},
    hide: {effect: "fade"},
    resizable: false,
    height:'auto',
    width: 'auto',
    maxWidth: 600,
    modal: true,
    fluid: true,
    buttons: {
    "Sí": {
    text: "Sí",
    id: "button-yes",
    click: function() {
    $(this).dialog("close");
    funcionalidadSI();
    }
    },
    "No": {
    text: "No",
    id: "button-no",
    click: function() {
    if (! funcionalidadNO)
    $(this).dialog("close");
    else
    funcionalidadNO();
    }
    }
    }
    });
    $('div.ui-dialog-buttonset #button-yes').addClass('btn btn-default');
    $('div.ui-dialog-buttonset #button-no').addClass('btn btn-danger');
    $('div.ui-dialog-titlebar').addClass('sipa-fondo-1');
    $('div.ui-dialog-buttonset > button').on('click', function() {
    $(this).attr('disabled', 'true');
    });
    $('button.ui-dialog-titlebar-close').addClass('btn-danger').html('X');
    }
    </script>
    <#if _SCRIPTS??>${_SCRIPTS}</#if>
  </body>
</html>
</#macro>
<#--
Construye el menú emergente
-->
<#macro orbMenu>
<div id="triangle" role="soy_un_triangulo" class="visible-md visible-lg"></div>
<div class="well visible-sm visible-md visible-lg sipa-well">
  <@menuOption url="/rol" permission="ADMIN_ROL" imgProperty="menu.img.admin.rol" label="menu.admin.rol.title.rol" />
  <@menuOption url="/perfil" permission="ADMIN_PERFIL" imgProperty="menu.img.admin.perfil" label="menu.admin.rol.title.perfil" />
  <@menuOption url="/usuario" permission="ADMIN_USUARIO" imgProperty="menu.img.admin.usuario" label="menu.admin.rol.title.usuario" />
  <@menuOption url='/dependencia' permission="ADMIN_DEPENDENCIA" imgProperty="menu.img.admin.dependencia" label="menu.admin.rol.title.dependencia" />
  <@menuOption url='/parametro' permission="ADMIN_PARAMETRO" imgProperty="menu.img.admin.parametro" label="menu.admin.rol.title.parametro" />
  <@menuOption url='/tablaReferencia' permission="ADMIN_TABLA_REFERENCIA" imgProperty="menu.img.admin.tablaReferencia" label="menu.admin.rol.title.tablaReferencia" />
  <@menuOption url='/inconsistencias/consulta' permission="" imgProperty="menu.img.admin.inconsistencias" label="menu.admin.rol.title.inconsistencias" />
  <@menuOption url='/rangos' permission="" imgProperty="menu.img.admin.rango" label="menu.admin.rol.title.rango" />
  <@menuOption url='/evidenciaAnulacion' permission="" imgProperty="menu.img.admin.rangos" label="menu.admin.rol.title.rangos" />
  <@menuOption url='/rangosAnulacion' permission="" imgProperty="menu.img.admin.anulacion" label="menu.admin.rol.title.anulacion" />
  <@menuOption url='/cursos' permission="" imgProperty="menu.img.admin.cursos" label="menu.admin.rol.title.cursos" />
  <@menuOption url='/curso_asistencia' permission="" imgProperty="menu.img.admin.asistencia" label="menu.admin.rol.title.asistencia" />
  <@menuOption url='/agendamiento-audiencia' permission="" imgProperty="menu.img.admin.tiempo" label="menu.admin.rol.title.tiempo" />
  <@menuOption url='/turno-firma/new' permission="" imgProperty="menu.img.admin.horario" label="menu.admin.rol.title.horario" />
  <@menuOption url='/financiero/pagos/noaplicados' permission="" imgProperty="menu.img.admin.pago" label="menu.admin.rol.title.pago" />
  <@menuOption url='/financiero/pagos' permission="" imgProperty="menu.img.admin.cargaPagos" label="menu.admin.rol.title.cargaPagos" />
  <@menuOption url='/sipa/construccion' permission="" imgProperty="menu.img.admin.devolucion" label="menu.admin.rol.title.devolucion" />
  <@menuOption url='/sipa/construccion' permission="" imgProperty="menu.img.admin.fallo" label="menu.admin.rol.title.fallo" />

</div>
</#macro>
<#--
Construye una opción de menú
@param url @optional
Es la dirección o url al que debe apuntar la opción. Esta dirección se normaliza automáticamente
anteponiendo el contexto de aplicación.
@param label @optional
Es el código en el archivo messages.properties en el que se encuentra la etiqueta del enlace
@param descProperty @optional
Es el código en el archivo messages.properties en el que se encuentra la descripción
@param imgProperty @optional
Es el código en el archivo messages.properties en el que se encuentra la dirección o url
de la imagen de la opción. Esta url se normaliza automáticamente para anteponer el nombre
de contexto de la aplicación.
@param permission @optional
Es el listado de permisos que el usuario debe tener para que esta opción de menú sea visible
-->
<#macro menuOption url="/" label="menu.label.default" descProperty="menu.desc.default" imgProperty="menu.img.default" permission="">
<#assign muestro = true />
<#if permission?? && permission != "">
<#assign respuesta><@l.hasRole permission="${permission}">yes</@l.hasRole></#assign>
<#assign muestro = (respuesta == "yes") />
</#if>
<#if muestro>
<#assign imgUrl><@s.message imgProperty /></#assign>
<a href="<@s.url '${url}'/>">
  <div class="sipa-box col-md-3 col-sm-12">
    <div class="pull-left">
      <img style="max-height: 70px" src="<@s.url imgUrl />">
    </div>
    <div>
      <div style="font-size: 0.8em"><strong><@s.message label /></strong></div>
      <p style="font-size: 0.8em"><@s.message descProperty /></p>
    </div>
  </div>
</a>
</#if>
</#macro>
