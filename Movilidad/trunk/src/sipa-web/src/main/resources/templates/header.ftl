<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>SIPA</title>
    <!-- Bootstrap core CSS -->
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/theme.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="docs/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<@s.url '/webjars/jquery/1.12.4/jquery.min.js' />"></script>
<script src="<@s.url '/webjars/jquery-ui/1.12.1/jquery-ui.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<@s.url '/static/bootstrap/3.3.7/js/bootstrap.min.js' />"></script> 

</head>
<body>
<div class="container">
    <div class="col-md-3">
        <img class="ban-header-logo" src="http://www.movilidadbogota.gov.co/web/sites/all/themes/sdmtheme/xlogo.png.pagespeed.ic.WWOATdaA3q.webp" alt="Inicio">
    </div>
    <div class="col-md-8 text-center">
        <h3>Sistema de Información de Procesos Administrativos</h3>
    </div>
    <div class="col-md-1">
        <img src="http://www.movilidadbogota.gov.co/web/sites/all/themes/sdmtheme/images/xescudo.png.pagespeed.ic.gq1zPCkebS.webp">
    </div>
</div>
<div class="container">
    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Activar navegación</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">SIPA</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/index">Inicio</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Bandejas <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/bandeja/tareas">Tareas pendientes</a></li>
                            <li><a href="/bandeja/notificaciones">Notificaciones</a></li>
                            <li><a href="/bandeja/correspondencia">Correspondencia</a></li>
                            <li><a href="/bandeja/reincidencias">Reincidencias</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"  class="active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Consultas <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<@s.url '/comparendo/consulta' />">Comparendos</a></li>
                            <li><a href="<@s.url '/persona/consulta' />"</a>Personas</a></li>
                            <li><a href="<@s.url '/documentos/consulta' />">Documentos</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"  class="active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Configuración <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/conf/audiencia/horarios">Horarios de audiencia</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="https://localhost:9443/commonauth?commonAuthLogout=true&type=oid&commonAuthCallerPath=http://localhost:8081/logout&relyingParty=localhost">Salir</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>
</div>
<div class="container theme-showcase" role="main">
    <div class="container-fluid">
        <#if SUCCESS??>
            <div class="center-block alert alert-success">${SUCCESS}</div>
        </#if>
        <#if ERROR??>
            <div class="center-block alert alert-danger">${ERROR}</div>
        </#if>
