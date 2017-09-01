<#macro main>

<#-- <#include 'header.ftl'/> -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><@spring.message "app.title"/></title>
    <!-- Bootstrap core CSS -->
    <link href="<@spring.url '/static/bootstrap/3.3.7/css/bootstrap.min.css' />" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="<@spring.url '/static/bootstrap/3.3.7/css/bootstrap-theme.min.css' />" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="docs/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="<@spring.url '/webjars/jquery-ui/1.12.1/jquery-ui.min.css' />">


</head>
<body>
<#nested />


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<@spring.url '/webjars/jquery/1.12.4/jquery.min.js' />"></script>
<script src="<@spring.url '/webjars/jquery-ui/1.12.1/jquery-ui.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<@spring.url '/static/bootstrap/3.3.7/js/bootstrap.min.js' />"></script>

<script>
  $( function() {
    $( ".datepicker" ).datepicker();
  } );
  </script>

</body>
</html>
</#macro>