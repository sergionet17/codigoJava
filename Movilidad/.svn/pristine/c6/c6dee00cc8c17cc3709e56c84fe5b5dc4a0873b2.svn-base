<#--
Establece el nombre del tema que se usa en toda la aplicación
-->
<#global theme="design-02" />
<#global themeLib="/${theme}.ftl" />
<#import themeLib as d />

<#--
Envuelve el contenido en la plantilla principal del sistema.
Principalmente se usa como punto de intercambio de las diferentes propuestas gráficas.
-->
<#macro main>
  <@d.layout>
    <#nested/>
  </@d.layout>
</#macro>

<#--

Almacena su contenido en la variable global "_SCRIPTS". Esta variable acumula todos los contenidos
que se reciban por esta macro. Usualmente se acumulan scripts de JavaScript que dependen de JQuery y por
tanto deben ejecutarse al final de la página, luego de incluir la librería de JQuery; sin embargo, es
mucho más cómodo permitir la escritura de los scripts en la plantilla o punto en el que se definen los
elementos de HTML con los que interactúan

-->
<#macro script>
  <#if _SCRIPTS??>
    <#local previousScript = _SCRIPTS />
    <#global _SCRIPTS>
      ${previousScript}
      <#nested />
    </#global>
  <#else>
    <#global _SCRIPTS>
      <#nested />
    </#global>
  </#if>
</#macro>

<#--

Almacena su contenido en la variable global "_HTML". Esta variable acumula todos los contenidos
que se reciban por esta macro. Usualmente se acumulan trozos de HTML que requieren estar externos a
cualquier contenedor y por tanto deben ejecutarse al final de la página antes de los scripts finales;
sin embargo, es mucho más cómodo permitir la escritura de los modales en la plantilla o punto en el
que se definen los elementos de HTML con los que interactúan.

-->
<#macro appendHtml>
  <#if _HTML??>
    <#local previousHtml = _HTML />
    <#global _HTML>
      ${previousHtml}
      <#nested />
    </#global>
  <#else>
    <#global _HTML>
      <#nested />
    </#global>
  </#if>
</#macro>

<#--

Verifica si el usuario tiene los permisos correspondientes y muestra su contenido en caso que cumpla con
todos los permisos. La macro usa el controlador de base para realizar esta tarea. Esta macro *únicamente*
funciona si el controlador hereda de la clase BaseController

@param permission Un listado separado por comas con los permisos a verificar

-->
<#macro hasRole permission>
  <#if controller??>
    <#if controller.hasRole(permission)>
      <#nested />
    </#if>
  <#else>
    El controlador no tiene el método <strong>hasRole</strong>.
    Sugerencia: herede el controlador de <strong>BaseController</strong>.
  </#if>
</#macro>

<#--

Construye un cuadro de texto que controla el tamaño de la cadena de texto que se ingresa y el formato
de la entrada mediante el uso de validadores.

@param name
  Es el nombre del campo tal como debe ser enviado en el formulario

@param size @default 255
  Es el tamaño máximo permitido para la cadena de texto

@param class @default ""
  Lo que se ingrese en este parámetro será puesto en el atrubuto class del elemento input

@param validator @default "textValidator" @values "textValidator" "numberValidator" "decimalValidator"
  Es el tipo de validación que se debe aplicar al contenido que se ingresa

@param value @default ""
  Es el valor por defecto con el que se debe construir el elemento input

@param min @default ""
  En caso que el validador sea "numberValidator" entonces este es el valor de número
  mínimo que acepta el campo

@param max @default ""
  En caso que el validador sea "numberValidator" entonces este es el valor de número
  máximo que acepta el campo

-->
<#macro inputText name size="255" class="" validator="textValidator" value="" min="0" max="">
  <#assign id = name?replace(".", "_") />
  <#if validator="textValidator">
    <#if ! __TEXT_VALIDATOR_INCLUDED__?? >
      <@script>
        <script type="text/javascript">
          <!--
          $(document).ready(function(){
            $("input").filter(function(){
              return ($(this).attr("sipa-validator") == "textValidator");
            }).keypress(function(event) {
              var maxSize = parseInt($(this).attr("sipa-size"));
              var currentLength = $(this).val().length;
              var remainingSize = maxSize - currentLength;
              $(this).next("span").text(remainingSize);
              if(remainingSize <= 0) {
                event.preventDefault();
              }
            });
          });
          -->
        </script>
      </@script>
      <#global __TEXT_VALIDATOR_INCLUDED__ = true />
    </#if>
      <div class="input-group">
        <input type="text" class="form-control ${class}" value="${value}" id="${id}" sipa-validator="${validator}" sipa-size="${size}" name="${name}" autocomplete="off" />
        <span class="input-group-addon">${size}</span>
      </div>
  </#if>

  <#if validator="numberValidator" || validator="decimalValidator">
    <#if ! __NUMBER_VALIDATOR_INCLUDED__?? >
      <@script>
        <script type="text/javascript">
          <!--
          $(document).ready(function(){
              $("input").filter(function(){
              //  $(this).next("span").text("#")
                return ($(this).attr("sipa-validator") == "numberValidator");
              }).keydown(function(event) {
              if(event.which == 32){
                event.preventDefault();
              }
              if(event.which != 8  &&
                isNaN(String.fromCharCode(event.which)) &&
                !(event.which >= 96 && event.which <= 105)){
                 event.preventDefault();
              }else{
                if(event.which != 8){
                    var maxSize = parseInt($(this).attr("sipa-max"));
                    var minSize = parseInt($(this).attr("sipa-min"));
                    var number;
                    if(event.which >= 96 && event.which <= 105 ){
                        number = $(this).val()+String.fromCharCode(event.keyCode-48);
                    }else{
                        number = $(this).val()+String.fromCharCode(event.which);
                    }
                    var estaEnRango =  number >  minSize ? true : false;
                    var estaEnRango = estaEnRango && ( number < maxSize? true : false);
                    if(estaEnRango){
                        $(this).next("span").text("✔");
                    }else{
                        $(this).next("span").text("X");
                        event.preventDefault();
                    }
                }
              }

              });
            });
        </script>
      </@script>
      <#global __NUMBER_VALIDATOR_INCLUDED__ = true />
      </#if>
      <#if ! __DECIMAL_VALIDATOR_INCLUDED__?? >
      <@script>
        <script type="text/javascript">
          <!--
          $(document).ready(function(){
              $("input").filter(function(){
              //  $(this).next("span").text("#")
                return ($(this).attr("sipa-validator") == "decimalValidator");
              }).keydown(function(event) {
              if(event.which == 32){
                event.preventDefault();
              }
              var regexString = "${constants.DECIMAL_REGEX_STRING}";
              //var regexString = "^\\d+(\\.\\d{1,2})?$";
              var regex = new RegExp(regexString);
              if(event.which != 8  && event.which != 110 && event.which != 190 &&
                isNaN(String.fromCharCode(event.which)) &&
                !(event.which >= 96 && event.which <= 105)){
                 event.preventDefault();
              }else{
                if(event.which != 8){
                  var maxSize = parseFloat($(this).attr("sipa-max"));
                  var minSize = parseFloat($(this).attr("sipa-min"));
                  var number;
                  var punto = false;
                  var estaEnRango;
                  var numberAnt = $(this).val();
                  if (event.which == 110 || event.which == 190) {
                    punto = true;
                    number = $(this).val()+".";
                  }
                  else if(event.which >= 96 && event.which <= 105)
                    number = $(this).val()+String.fromCharCode(event.keyCode-48);
                  else
                    number = $(this).val()+String.fromCharCode(event.which);
                  var m = number.match(/\./g);
                  var cantidad = 0;
                  if (m)
                    cantidad = number.match(/\./g).length;
                  if(cantidad > 1)
                      event.preventDefault();
                  else {
                    if (regex.test(number) || Number.isInteger(number)) {
                      var estaEnRango =  parseFloat(number) >  minSize ? true : false;
                      var estaEnRango = estaEnRango && ( parseFloat(number) < maxSize? true : false);
                      if(estaEnRango){
                          $(this).next("span").text("✔");
                      }else{
                          $(this).next("span").text("X");
                          event.preventDefault();
                      }
                    } else {
                      if (!punto) {
                        event.preventDefault();
                      } else {
                        $(this).next("span").text("X");
                      }
                    }
                  }
                }
              }
              });
            });
        </script>
      </@script>
      <#global __DECIMAL_VALIDATOR_INCLUDED__ = true />
    </#if>
      <div class="input-group " >
        <span class="input-group-addon">#</span>
        <input type="text" class="form-control ${class}" value="${value}" id="${id}" sipa-validator="${validator}"   sipa-min="${min}" sipa-max="${max}" sipa-size="${size}" name="${name}" autocomplete="off"/>
        <span class="input-group-addon">✔</span>
      </div>
  </#if>
</#macro>

<#--

Definición de un formulario. Incluye lo necesario para CSRF de String
A continuación se muestra un ejemplo del uso del macro
<@l.master>
  <@l.panelHeading title="Ingreso al sistema">
    <@l.form action="/login" class="form-horizontal">
      <@l.formGroup name="username" label="Username" placeholder="email@domain.com"/>
      <@l.formGroup name="password" label="Password" type="password"/>
      <@l.formSubmit label="Login" />
    </@l.form>
  </@l.panelHeading>
</@l.master>

@param action @default ""
  Es el valor del atributo action del formulario. Esta URL se normaliza automáticamente

@param method @default "post"
  Es el valor del atributo method del formulario.

@param enctype @default "application/x-www-form-urlencoded"
  Es el valor del atributo enctype del formulario.

@param class @default ""
  Es el valor del atributo class del formulario.

-->
<#macro form action="" method='post' enctype='application/x-www-form-urlencoded' class="" id="">
  <#assign isHorizontal=(class=="form-horizontal") >
  <form action="<@s.url action />" method="${method}" enctype="${enctype}" class="${class}" id="${id}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <#nested/>
  </form>
</#macro>

<#--

Definición de un campo de formulario.
A continuación se muestra un ejemplo del uso del macro
<@l.master>
  <@l.panelHeading title="Ingreso al sistema">
    <@l.form action="/login" class="form-horizontal">
      <@l.formGroup name="username" label="Username" placeholder="email@domain.com"/>
      <@l.formGroup name="password" label="Password" type="password"/>
      <@l.formSubmit label="Login" />
    </@l.form>
  </@l.panelHeading>
</@l.master>

@param label
  Es el valor de la etiqueta

@param id
  Es el id del elemento al cual se le asocia el label

@nested Es el contenido que representa la parte de componente del grupo

-->
<#macro formGroup label id >
  <div class="form-group">
  <#if isHorizontal >
    <label for="${id}" class="col-sm-2 control-label sipa-p-0">${label}</label>
    <div class="col-sm-10">
      <#nested/>
    </div>
  <#else>
    <label for="${id}">${label}</label><br>
    <#nested/>
  </#if>
  </div>
</#macro>

<#--

DIANA: Por favor documentar

-->
<#macro formGroupText name label placeholder="" value="" validator="" size="255" class="" min="0" max="">
  <#assign id = name?replace('.','_') />
  <@formGroup label id>
    <@inputText name=name size=size class=class validator=validator value=value min=min max=max/>
  </@formGroup>
</#macro>



<#macro formGroupSingleSelect  name label path options attributes="" >
  <#assign id = name?replace('.','_') />
  <@formGroup label id>
    <@s.formSingleSelect path options attributes/>
  </@formGroup>
</#macro>

<#--

Construye un input tipo FILE.

@param name
  Es el nombre del campo tal como debe ser enviado en el formulario

@param label
  Es el nombre del label que acompaña al input

@param placeholder
  Es el nombre del placeholder que acompaña al input

@param value
  Es el nombre del value que acompaña al input

@param class
  Cadena con las clases que acompañan al input

@param accept
  Los tipos de archivos que permite por defecto subir a traves del input

-->
<#macro formGroupFile name label placeholder="" value="" class="" accept="">
  <#assign id = name?replace('.','_') />
  <@formGroup label id>
    <input type="file" class="${class}" name="${name}" placeholder="${placeholder}" value="${value}" id="${id}" accept="${accept}" />
  </@formGroup>
</#macro>

<#--

Construye un input tipo Date.

@param name
  Es el nombre del campo tal como debe ser enviado en el formulario

@param label
  Es el nombre del label que acompaña al input

@param placeholder
  Es el nombre del placeholder que acompaña al input

@param value
  Es el nombre del value que acompaña al input

@param class
  Cadena con las clases que acompañan al input

@param tipo @default "date"
  Tipo si es date o datetime o time

-->
<#macro formGroupDate name label placeholder="" value="" class="" tipo="date">
  <#assign id = name?replace('.','_') />
  <#if tipo = "datetime">
    <#assign clase = "${class} datetimepicker" />
  <#elseif tipo = "time">
    <#assign clase = "${class} timepicker" />
  <#else>
    <#assign clase = "${class} datepicker" />
  </#if>
  <@formGroup label id>
    <div class='input-group'>
      <input type="text"  class="${clase}" name="${name}" value="${value}" id="${id}"/>
      <span class="input-group-addon">
        <span class="glyphicon glyphicon-calendar"></span>
      </span>
    </div>
  </@formGroup>
</#macro>

<#--

DIANA: Documentar por favor

-->
<#macro checkBox name constant=1 isSelected=false class="" >
  <#assign id = name?replace('.','_') />
  <div class="checkbox">
    <label>
      <#if isSelected >
        <input type="checkbox" name="${name}" id="${id}" value="${constant}" class="${class}" checked="checked"> <#nested/>
      <#else>
        <input type="checkbox" name="${name}" id="${id}" value="${constant}" class="${class}"> <#nested/>
      </#if>
    </label>
  </div>
</#macro>

<#--

Construye un botón submit para el formulario

@param label @default "Enviar"
  Es la etiqueta del botón

-->
<#macro formSubmit label="Enviar">
  <#if isHorizontal>
    <div class="form-group">
      <div class="col-sm-12">
        <button type="submit" class="btn btn-primary" id="submitButton">${label}</button>
      </div>
    </div>
  <#else>
    <button type="submit" class="btn btn-primary" id="submitButton">${label}</button>
  </#if>
  <@script>
    <script type="text/javascript">
      $("#submitButton").click(function(){
        $(this).addClass("hidden");
        $(this).after("<span class=\"btn\">Espere...</span>");
      });
    </script>
  </@script>
</#macro>

<#--

Construye un panel con encabezado

@param title
  Es el título del panel

@param element
  Es el elemento que debe envolver el título

@param class
  Es el valor del atributo class del elemento que envuelve el título

-->
<#macro panelHeading title element="" class="">
  <div class="panel panel-default">
    <div class="panel-heading">
      <#if element=="">
        <h3 class="panel-title">${title}</h3>
      <#else>
        <${element} class="${class}">${title}</${element}>
      </#if>
    </div>
    <div class="panel-body">
      <#nested/>
    </div>
  </div>
</#macro>

<#--

Macro para crear el listado de dependencias de manera recursiva

@param listado
  Es el listado de dependencias raíz

@param idSeleccionado @default ""
  Es el identificador único de la dependencia seleccionada

-->
<#macro dependencia listado idSeleccionado="">
    <ul class="sipa-li">
    <#list listado as dep>
      <li>
        <#assign class><#if dep.id == idSeleccionado>label label-primary</#if></#assign>
        <span sipa-id="${dep.id}" class="sipa-dependencia-mark ${class!''}">${dep.nombre}</span>
        <#if dep.dependencias??>
          <@dependencia dep.dependencias />
        </#if>
      </li>
    </#list>
    </ul>
</#macro>

<#--

Construye los artefactos necesarios para la selección de dependencias por ventana modal

@param listado
  Es el listado de dependencias raíz

@param nombreCampo
  Es el nombre del campo con el que se debe enviar la información al servidor

@param dependenciaSeleccionada
  Es el objeto dependencia que se encuentra seleccionada al momento de la construcción

-->
<#macro selectorDependencias listado nombreCampo dependenciaSeleccionada="" nombreDependencia="">
  <#assign id = nombreCampo?replace('.', '_') />
  <#assign isHorizontal=false >
  <@formGroup "Dependencia" id>
  <p hidden><#if dependenciaSeleccionada = ""><#else>${(dependenciaSeleccionada)!""}</#if></p>
    <div class="input-group">
      <#if nombreDependencia=="">
       <input class="form-control" id="disabledInputDep" type="text" value="Ninguna"
        disabled>
      <#else>
        <input class="form-control" id="disabledInputDep" type="text" value="${nombreDependencia}"
        disabled>
      </#if>
      <div class="input-group-btn">
      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal_${id}">
      Seleccionar
      </button>
      <input class="sipa-dependencia-mark" name="${nombreCampo}" type="hidden" value="${dependenciaSeleccionada}" />
    </div><!-- /btn-group -->
  </div><!-- /input-group -->
  </@formGroup>

  <@appendHtml>
    <div class="modal fade" id="modal_${id}" tabindex="-1" role="dialog" aria-labelledby="modallabel_${id}" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" id="btnSalir" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <h5 class="modal-title" id="exampleModalLabel">Selección de dependencia</h5>
          </div>
          <div class="modal-body">
            <@dependencia listado dependenciaSeleccionada />
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-danger" id="btnSeleccionarNing" data-dismiss="modal">Ninguna</button>
            <button type="button" class="btn btn-primary" id="btnSeleccionarDep" data-dismiss="modal">Seleccionar</button>
          </div>
        </div>
      </div>
    </div>
  </@appendHtml>

  <@script>
    <script type="text/javascript">
      $("span.sipa-dependencia-mark").click(function() {
      $("span.sipa-dependencia-mark").removeClass("label label-primary");
      id = $(this).attr("sipa-id");
      $(this).addClass("label label-primary");
      });
    </script>
  </@script>

<@script>
 <script type="text/javascript">
  $("#btnSeleccionarDep").click(function(){
  campo = $("input.sipa-dependencia-mark").filter(function(index){
  return $(this).attr("name") == "${nombreCampo}"
  });
  campo.val(id);
  if ($("span.label-primary").text()!=""){
    $("#disabledInputDep").val($("span.label-primary").text());
  }else{
    $("#disabledInputDep").val("Ninguna");
  }
  });
 </script>
</@script>

<@script>
<script type="text/javascript">
$(function () {
  $("#modal_${id}").on('hidden.bs.modal', function () {
  $(this).removeData('bs.modal');
  $("span.sipa-dependencia-mark").removeClass("label label-primary");
  });
});
</script>
</@script>

<@script>
<script type="text/javascript">
$("#btnSeleccionarNing").click(function(){
  $("#disabledInputDep").val("Ninguna");
  $(".sipa-dependencia-mark").val("");
});
</script>
</@script>

</#macro>

<#macro fluid>
  <#global _FLUID = true />
</#macro>

<#macro dateFormat date ><#if date?is_string >Sin fecha<#else><#if constants?? ><span title="${date?string[constants.DATE_TIME_FORMAT_PATTERN]}">${date?string[constants.DATE_FORMAT_PATTERN]}</span><#else>${date?string.iso}</#if></#if></#macro>

<#macro datetimeFormat date ><#if date?is_string >Sin fecha<#else><#if constants?? >${date?string[constants.DATE_TIME_FORMAT_PATTERN]}<#else>${date?string.iso}</#if></#if></#macro>

<#macro currencyFormat number>
  <#if number??>
    <#assign x = number />
  <#else>
    <#assign x = 0 />
  </#if>
  <#setting number_format=",##0">
  <#setting locale="es_CO">
    <#assign round = number?round />
    <#assign currency = round?string.currency />
    ${currency}
  <#setting number_format="number">
</#macro>


<#--

Construye un botón submit para el formulario

@param label @default "Enviar"
  Es la etiqueta del botón

-->
<#macro formSubmitDoubleButton labelb1="Enviar" labelb2="Cancelar" path="">
    <div class="form-group">
      <div class="col-sm-12">
        <button type="submit" class="btn btn-success btn-md" id="submitButton">${labelb1}</button>
        <a href="${path}" class="btn btn-danger btn-md">${labelb2}</a>
      </div>
    </div>
  <@script>
    <script type="text/javascript">
      $("#submitButton").click(function(){
        $(this).addClass("hidden");
        $(this).after("<span class=\"btn\">Espere...</span>");
      });
    </script>
  </@script>
</#macro>

<#--

Construye un enlace de descarga para un documento

@param documento
  Es el documento
@param label @default "Ver documento"
  Es la etiqueta del enlace

-->
<#macro documentoLink documento label="Ver documento">
  <a href="<@s.url '/documentos/${documento.id}'/>"><i class="glyphicon glyphicon-file"></i> ${label}</a>
</#macro>
