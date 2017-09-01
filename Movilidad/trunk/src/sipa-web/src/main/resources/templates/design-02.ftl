<#--
Propuesta gráfica o diseño número 1.
Envuelve el contenido en la plantilla principal del sistema.
-->
<#macro layout>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><@s.message "app.title" /></title>

    <!-- Bootstrap -->
    <link href="<@s.url '/static/bootstrap/3.3.7-d02/dist/css/bootstrap.min.css'/>" rel="stylesheet">
     <link rel="stylesheet" href="<@s.url '/static/chosen/v1.7.0/bootstrap-chosen.css' />">
    <link href="<@s.url '/static/datetimepicker/2.5.4/jquery.datetimepicker.min.css' />" rel="stylesheet">
    <link rel="stylesheet" href="<@s.url '/webjars/jquery-ui/1.12.1/jquery-ui.min.css' />">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="<@s.url '/webjars/html5shiv/3.7.3/html5shiv.min.js'/>"></script>
      <script src="<@s.url '/webjars/respond/1.4.2/respond.min.js'/>"></script>
    <![endif]-->
  </head>
  <body>
  	<@heading />
  	<@nav />
  	<#assign _CONTAINER><#if _FLUID??>container-fluid<#else>container</#if></#assign>
  	<div class="${_CONTAINER}">
  		<#if FLASH_MESSAGE_OK??>
	    <div class="alert alert-success"><p>${FLASH_MESSAGE_OK}</p></div>
	    </#if>
	    <#if FLASH_MESSAGE_ERROR??>
	    <div class="alert alert-danger"><p>${FLASH_MESSAGE_ERROR}</p></div>
	    </#if>
	    <div id="dvConfirmacion" title='<@s.message "app.title"/>'></div>
    	<#nested/>
    </div>

    ${_HTML!""}

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<@s.url '/webjars/jquery/1.12.4/jquery.min.js'/>"></script>
    <script src="<@s.url '/webjars/jquery-ui/1.12.1/jquery-ui.min.js' />"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<@s.url '/static/bootstrap/3.3.7-d02/dist/js/bootstrap.min.js'/>"></script>

    <script src="<@s.url '/static/datetimepicker/2.5.4/jquery.datetimepicker.full.min.js' />"></script>
    <script src="<@s.url '/static/multiselect/v2.3.12/multiselect.min.js' />"></script>
    <script src="<@s.url '/static/chosen/v1.7.0/chosen.jquery.min.js' />"></script>
    <script src="<@s.url '/static/schedule/3_0_1/aui/aui-min.js' />"></script>

    <script type="text/javascript">
    	$(function () {
		  $('[data-toggle="popover"]').popover();
		  $('.has-id').click(function(){
		  	$('.is-id', this).removeClass("hidden");
		  });
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
	    $('div.ui-dialog-buttonset #button-yes').addClass('btn btn-primary');
	    $('div.ui-dialog-buttonset #button-no').addClass('btn btn-danger');
	    $('div.ui-dialog-titlebar').addClass('site-color-dark');
	    $('div.ui-dialog-buttonset > button').on('click', function() {
	    $(this).attr('disabled', 'true');
	    });
	    $('button.ui-dialog-titlebar-close').addClass('btn-danger').html('X');
    }
    </script>
    ${_SCRIPTS!""}
  </body>
</html>
</#macro>

<#macro heading>
	<div class="sipa-bg-primary">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2">
					<a href="<@s.url '/'/>"><img src="<@s.url '/static/img/${theme}/logo-alcaldia.png'/>" class="sipa-alcadia-logo" /></a>
				</div>
				<div class="col-md-10">
				<h2 class="sipa-m-0 sipa-title">
              <@s.message 'app.secretaria'/><br/>
              </h2>
              <h3 class="sipa-m-0 sipa-subtitle">
              <@s.message 'app.title'/> - <@s.message 'app.title.descripcion'/>
              </h3>
				</div>
			</div>
		</div>
	</div>
</#macro>

<#macro nav>
	<nav class="navbar navbar-inverse navbar-static-top">
	  	<div class="container-fluid">
	    	<!-- Brand and toggle get grouped for better mobile display -->
	    	<div class="navbar-header">
	      		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        		<span class="sr-only">Toggle navigation</span>
	        		<span class="icon-bar"></span>
	        		<span class="icon-bar"></span>
	        		<span class="icon-bar"></span>
	      		</button>
	      		<a class="navbar-brand" href="<@s.url '/'/>">SIPA</a>
	    	</div>

		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	    	  	<ul class="nav navbar-nav">
	      			<#if menu??>
	      				<@navSecondary />
	      				<#assign topMenu = menu.find("primary") />
		      			<#if topMenu?? >
		      				<#list topMenu.submenu as x>
			      				<#if x.submenu?? && (x.submenu?size > 0) >
						        	<li class="dropdown">
						          		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${x.label} <span class="caret"></span></a>
					          			<ul class="dropdown-menu">
					          				<#list x.submenu as y >
					          					<li><a href="<@s.url y.url />">${y.label}</a></li>
					          				</#list>
					          			</ul>
						        	</li>
		      					<#else>
									<li><a href="<@s.url x.url />">${x.label}</a></li>
		      					</#if>
		      				</#list>
		      			</#if>
	      			</#if>
			    </ul>
	      		<#if usuario??>
		      		<ul class="nav navbar-nav navbar-right">
		      			<#if mensajesSistema??>
			      			<li><a id="mensajesOption" href="<@s.url '/'/>"  >Mensajes <span class="badge badge-danger">${mensajesSistema?size}</span></i> </a>
			      			</li>
			      		</#if>
		        		<li class="dropdown">
		          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${(usuario.email)!'Usuario'} <span class="caret"></span></a>
		          			<ul class="dropdown-menu">
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
		      	</#if>
	    	</div><!-- /.navbar-collapse -->
	  	</div><!-- /.container-fluid -->
	</nav>
</#macro>

<#macro navSecondary>
	<#if menu??>
		<#assign secMenu = menu.find("secondary") />
		<#if secMenu?? >
	    	<li class="dropdown">
	      		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Menú <span class="caret"></span></a>
	  			<ul class="dropdown-menu">
	  				<#if secMenu.submenu?? >
	  					<#list secMenu.submenu as x >
	  						<@pintarMenuSecondary x/>
	  					</#list>
	  				</#if>
	  			</ul>
	  		</li>
  		</#if>
  	</#if>
</#macro>

<#macro pintarMenuSecondary item>
	<#if item.agrupador && item.submenu?size != 0>
	<li role="separator" class="divider"></li>
		<li>
			<a><strong>${item.label}</strong></a>
		</li>
		<#list item.submenu as x >
			<li>
				<a href="<@s.url x.url />">${x.label}</a>
			</li>
		</#list>
	<#else>
		<li>
			<a href="<@s.url item.url />">${item.label}</a>
		</li>
	</#if>
</#macro>

<#global __ERROR_404__ = true />
<#macro error404>
	<div class="jumbotron">
	  	<div class="pull-right">
	  		<img class="sipa-contstruccion" src="<@s.url '/static/img/${theme}/construccion.png' />" />
	  	</div>
	    <h1>En construcción...</h1>
	    <p>El recurso que acaba de solicitar aún se encuentra en desarrollo.</p>
	    <p>Pronto estará disponible.</p>
	    <div class="clearfix"></div>
	</div>
</#macro>

<!--Mensajes de Error y OK de la aplicación -->
<#-- <#marco>
<div class="container-fluid">
      <#if FLASH_MESSAGE_OK??>
      <div class="alert alert-success"><p>${FLASH_MESSAGE_OK}</p></div>
      </#if>
      <#if FLASH_MESSAGE_ERROR??>
      <div class="alert alert-danger"><p>${FLASH_MESSAGE_ERROR}</p></div>
      </#if>
      <#nested/>
    </div>
   </#marco>  -->