<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div>&nbsp;</div>
<div id="sipa_well" class="well sipa-well">
  <div class="container">
    <div class="col-md-11">
        <h1>Tareas de Usuario</h1>
         <html lang="es" ng-app="forest5">
            <head>
              <link href="<@s.url '/static/angularjs/css/sidebar.css' />" type="text/css" rel="stylesheet" media="screen,projection">
            <!--angular y controllers-->
            <script>
                  /* Set the width of the side navigation to 250px and the left margin of the page content to 250px */
                function openNav() {
                    document.getElementById("mySidenav").style.width = "250px";
                    document.getElementById("main").style.marginLeft = "250px";
                }

                /* Set the width of the side navigation to 0 and the left margin of the page content to 0 */
                function closeNav() {
                    document.getElementById("mySidenav").style.width = "0";
                    document.getElementById("main").style.marginLeft = "0";
                }
            </script>
            <script src="<@s.url '/webjars/jquery/1.12.4/jquery.min.js' />"></script>
            <script src="<@s.url '/webjars/angularjs/1.6.4/angular.js' />"></script>
            <script src="<@s.url '/static/angularjs/bower_components/angular-resource/angular-resource.js' />"></script>
            <script src="https://cdn.jsdelivr.net/ngstorage/0.3.6/ngStorage.min.js"></script>
            <script src="<@s.url '/static/angularjs/directives/forms.js' />"></script>
            <script src="<@s.url '/static/angularjs/app.js' />"></script> 
            <script src="<@s.url '/static/angularjs/config.js' />"></script> 
            <script src="<@s.url '/static/angularjs/rest/instanciaprocesos.js' />"></script> 
            <script src="<@s.url '/static/angularjs/rest/tarea.js' />"></script> 
            <script src="<@s.url '/static/angularjs/rest/completartareas.js' />"></script> 
            <script src="<@s.url '/static/angularjs/rest/asignartarea.js' />"></script> 
            <script src="<@s.url '/static/angularjs/rest/definicionesprocesos.js' />"></script> 
            <script src="<@s.url '/static/angularjs/rest/formas.js' />"></script> 
            <script src="<@s.url '/static/angularjs/rest/usuarios.js' />"></script> 
            <script src="<@s.url '/static/angularjs/controllers/aloneController.js' />"></script>
            <!-- jQuery Library -->
            <script src="<@s.url '/static/angularjs/plugins/jquery-1.11.2.min.js' />"></script>
            <script src="<@s.url '/static/angularjs/plugins/jquery-ui.js' />"></script>
            <script type="text/javascript">
                  fnjq = jQuery.fn.autocomplete;
                   /* $(window).resize(function(){
                        if(currentForm.xui_ui_div21){
                            currentForm.xui_ui_div21.refresh()
                        }
                    });*/
            </script>
            </head>
            <body ng-controller="aloneController as controller">
                <div class="form-group">
                  <select class="form-control"  ng-model="tareasUsuario" ng-change="controller.cargarTarea(tareasUsuario)" 
                         data-ng-options="tarea as tarea.name for tarea in controller.tareasUsuario">
                        <option value="">Seleccione..</option>
                  </select>
                </div>
                <div class="jumbotron ng-hide" ng-hide="tarea_next">
                    <div class="pull-right">
                            <img class="sipa-contstruccion" src="/sipa/static/img/anular.png">
                    </div>
                    <h1>!Alerta¡</h1>
                    <p>La tarea <b>{{tarea_name}}</b> no tiene proceso definido.</p>

                    <div class="clearfix"></div>
                </div>
                <div class="form-group" >
                  <button class="btn btn-primary" disabled ng-disabled="!tareasUsuario || !tarea_next" ng-click="controller.avanzaTarea(tareasUsuario)">Continuar proceso</button>
                </div>
        
              <div id="main">
                
              <!-- Use any element to open the sidenav -->
              
                    <div style="position:relative">

                      <div id="formxui" xui mp-form-values="formData"></div>
                    </div>
                  </div>
            </body>
            
            <link rel="stylesheet" type="text/css" href=" <@s.url '/static/angularjs/plugins/jquery-ui.css' />"/>
            <script src="<@s.url '/static/angularjs/runtime/xui/js/xui-debug.js' />"></script>
            <script src="<@s.url '/static/angularjs/custom_components.js' />"></script>
            <script src="<@s.url '/static/angularjs/runtime/xui/js/UI/FusionChartsXT.js' />"></script>
            <script src="<@s.url '/static/angularjs/runtime/xui/js/UI/AutoComplete.js' />"></script>
            <script src="<@s.url '/static/angularjs/runtime/xui/js/UI/SelectorMultiple.js' />"></script>
            <script src="<@s.url '/static/angularjs/runtime/xui/js/UI/TreeGridPaginated.js' />"></script>
            <script src="<@s.url '/static/angularjs/controllers/xuiRender.js' />"></script>
            <script src="<@s.url '/static/angularjs/controllers/custom_components.js' />"></script>
            <script src="<@s.url '/static/angularjs/controllers/custom.js' />"></script>
            <script type="text/javascript">
            xui.setTheme("vista");
            </script>
            <style>
            html{
                font-size: medium !important;
                }
                *{
                    /* box-sizing: border-box; */
                   all: revert !important;
                }
                legend{
                    border-bottom
                }
                /*manejar conflicto de tamaños bosstra*/
                *{
                  all: revert !important;
                  box-sizing: initial;
                 }

                 /*Desactivar areas*/
                 .disabledArea {
                    pointer-events: none;
                    /*opacity: 0.4;*/
                }
                /*Auto complete de jquery con un index alto*/
                .ui-autocomplete { position: absolute; cursor: default;z-index:99999 !important;}  

                .xui-required{
                color:#000000 !important;
                }
                .xui-required:before {
                content:"*";
                color:red;
                }
                /*texto requeriso*/
                .xui-required-ext{
                /*color:#000000;*/
                }
                .xui-required-ext:before {
                content:"*";
                color:red;
                }

                .toLeft{

                        float:left;
                }
            </style>
          </html>
   
    </div>
  </div>
</div>
</@l.main>
