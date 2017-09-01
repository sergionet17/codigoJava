<@l.main>
<div>&nbsp;</div>
<!--Contenedor con la información de la autoridad y la vigencia del turno-->
<#if turnos??>
<!--Listado con los horarios de la autoridad-->
    <div class="col-md-12">
    <div class="well sipa-well">
        <div class="page-header">
          <h3><#if historico??>Consulta de históricos sobre las asignaciones de turnos de firma de las autoridades de tránsito<#else>Consulta de asignaciones de turnos de firma de las autoridades de tránsito</#if></h3>
          <small>Haga clik en "Nuevo" si requiere realizar una nueva asignación.
        </div>
        <button type="button" id="btn-newT" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#newTurno">Nuevo</button>
        <div>&nbsp;</div>
        <div class="panel panel-info">
        <!-- Default panel contents -->
            <div class="panel-heading"><#if historico??>Listado histórico de turnos para firmas de las autoridades de tránsito<#else>Listado de turnos para firmas de las autoridades de tránsito</#if></div>
            <!-- Table -->
            <div class="table-responsive">
            <div class="input-group">
                    <span class="input-group-addon" id="sizing-addon2">Buscar:  </span>
                    <input id="inputBuscar" type="text" class="form-control" placeholder="Palabra clave ..." aria-describedby="sizing-addon2">
              </div>
            <table class="table table-bordered" id="turnos-table">
                <thead>
                    <tr>
                        <th>Autoridad de tránsito</th>
                        <th>Dia</th>
                        <th>Hora inicio Am</th>
                        <th>Hora fin Am</th>
                        <th>Hora inicio Pm</th>
                        <th>Hora fin Pm</th>
                        <th>Inicio vigencia</th>
                        <th>Activo</th>
                    </tr>
                </thead>
                <tbody>
                    <#list turnos as turno> 
                        <#if turno.horario.lunes??&&turno.horario.lunes!='' >
                            <tr>
                                <td>${turno.autoridad.persona.primerNombre}&nbsp${(turno.autoridad.persona.segundoNombre)!''}&nbsp${(turno.autoridad.persona.primerApellido)!''}&nbsp${(turno.autoridad.persona.segundoApellido)!''}</td>
                                <td>${(turno.horario.lunes)!""}</td>
                                <td>${(turno.horario.lunesInicioAm)!""}</td>
                                <td>${(turno.horario.lunesFinAm)!""}</td>
                                <td>${(turno.horario.lunesInicioPm)!""}</td>
                                <td>${(turno.horario.lunesFinPm)!""}</td>
                                <td>${(turno.desde?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td> 
                                <td><#if !turno.activo><span class="glyphicon glyphicon-remove"></span><#else><span class="glyphicon glyphicon-ok"></span></#if></td>
                            </tr>
                        </#if>
                        <#if turno.horario.martes??&&turno.horario.martes!='' >
                            <tr>
                                <td>${turno.autoridad.persona.primerNombre}&nbsp${(turno.autoridad.persona.segundoNombre)!''}&nbsp${(turno.autoridad.persona.primerApellido)!''}&nbsp${(turno.autoridad.persona.segundoApellido)!''}</td>
                                <td>${(turno.horario.martes)!""}</td>
                                <td>${(turno.horario.martesInicioAm)!""}</td>
                                <td>${(turno.horario.martesFinAm)!""}</td>
                                <td>${(turno.horario.martesInicioPm)!""}</td>
                                <td>${(turno.horario.martesFinPm)!""}</td>
                                <td>${(turno.desde?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td>
                                <td><#if !turno.activo><span class="glyphicon glyphicon-remove"></span><#else><span class="glyphicon glyphicon-ok"></span></#if></td>
                            </tr>
                        </#if>
                        <#if turno.horario.miercoles?? &&turno.horario.miercoles!=''>
                            <tr>
                                <td>${turno.autoridad.persona.primerNombre}&nbsp${(turno.autoridad.persona.segundoNombre)!''}&nbsp${(turno.autoridad.persona.primerApellido)!''}&nbsp${(turno.autoridad.persona.segundoApellido)!''}</td>
                                <td>${(turno.horario.miercoles)!""}</td>
                                <td>${(turno.horario.miercolesInicioAm)!""}</td>
                                <td>${(turno.horario.miercolesFinAm)!""}</td>
                                <td>${(turno.horario.miercolesInicioPm)!""}</td>
                                <td>${(turno.horario.miercolesFinPm)!""}</td>
                                <td>${(turno.desde?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td>
                                <td><#if !turno.activo><span class="glyphicon glyphicon-remove"></span><#else><span class="glyphicon glyphicon-ok"></span></#if></td>
                            </tr>
                        </#if>
                        <#if turno.horario.jueves??&&turno.horario.jueves!='' >
                            <tr>
                                <td>${turno.autoridad.persona.primerNombre}&nbsp${(turno.autoridad.persona.segundoNombre)!''}&nbsp${(turno.autoridad.persona.primerApellido)!''}&nbsp${(turno.autoridad.persona.segundoApellido)!''}</td>
                                <td>${(turno.horario.jueves)!""}</td>
                                <td>${(turno.horario.juevesInicioAm)!""}</td>
                                <td>${(turno.horario.juevesFinAm)!""}</td>
                                <td>${(turno.horario.juevesInicioPm)!""}</td>
                                <td>${(turno.horario.juevesFinPm)!""}</td>
                                <td>${(turno.desde?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td>
                                <td><#if !turno.activo><span class="glyphicon glyphicon-remove"></span><#else><span class="glyphicon glyphicon-ok"></span></#if></td>
                            </tr>
                        </#if>
                        <#if turno.horario.viernes??&&turno.horario.viernes!='' >
                            <tr>
                                <td>${turno.autoridad.persona.primerNombre}&nbsp${(turno.autoridad.persona.segundoNombre)!''}&nbsp${(turno.autoridad.persona.primerApellido)!''}&nbsp${(turno.autoridad.persona.segundoApellido)!''}</td>
                                <td>${(turno.horario.viernes)!""}</td>
                                <td>${(turno.horario.viernesInicioAm)!""}</td>
                                <td>${(turno.horario.viernesFinAm)!""}</td>
                                <td>${(turno.horario.viernesInicioPm)!""}</td>
                                <td>${(turno.horario.viernesFinPm)!""}</td>
                                <td>${(turno.desde?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td>
                                <td><#if !turno.activo><span class="glyphicon glyphicon-remove"></span><#else><span class="glyphicon glyphicon-ok"></span></#if></td>
                            </tr>
                        </#if>
                        <#if turno.horario.sabado??&&turno.horario.sabado!=''>
                            <tr>
                                <td>${turno.autoridad.persona.primerNombre}&nbsp${(turno.autoridad.persona.segundoNombre)!''}&nbsp${(turno.autoridad.persona.primerApellido)!''}&nbsp${(turno.autoridad.persona.segundoApellido)!''}</td>
                                <td>${(turno.horario.sabado)!""}</td>
                                <td>${(turno.horario.sabadoInicioAm)!""}</td>
                                <td>${(turno.horario.sabadoFinAm)!""}</td>
                                <td>${(turno.horario.sabadoInicioPm)!""}</td>
                                <td>${(turno.horario.sabadoFinPm)!""}</td>
                                <td>${(turno.desde?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td>
                                <td><#if !turno.activo><span class="glyphicon glyphicon-remove"></span><#else><span class="glyphicon glyphicon-ok"></span></#if></td>
                            </tr>
                        </#if>
                        <#if turno.horario.domingo??&&turno.horario.domingo!=''>
                            <tr>
                                <td>${turno.autoridad.persona.primerNombre}&nbsp${(turno.autoridad.persona.segundoNombre)!''}&nbsp${(turno.autoridad.persona.primerApellido)!''}&nbsp${(turno.autoridad.persona.segundoApellido)!''}</td>
                                <td>${(turno.horario.domingo)!""}</td>
                                <td>${(turno.horario.domingoInicioAm)!""}</td>
                                <td>${(turno.horario.domingoFinAm)!""}</td>
                                <td>${(turno.horario.domingoInicioPm)!""}</td>
                                <td>${(turno.horario.domingoFinPm)!""}</td>
                                <td>${(turno.desde?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td>
                                <td><#if !turno.activo><span class="glyphicon glyphicon-remove"></span><#else><span class="glyphicon glyphicon-ok"></span></#if></td>
                            </tr>
                        </#if>
                    </#list>
                </tbody>
            </table>
            </div>
        </div>
        <#else>
            <div class="jumbotron">
                <h1 class="display-3">Consulta sobre turnos de firma de las autoridades de tránsito</h1>
                <p class="lead">En el momento no hay turnos para firma asignados.</p>
                <hr class="my-4">
                <p>Use el siguiente botón para crear una nueva asignación de horario para firma.</p>
                <p class="lead">
                    <button id="btn-jumbo" type="button" class="btn btn-success" data-toggle="modal" data-target="#newTurno">Nuevo</button>
                </p>
            </div>
    </div>
    </div>
</#if>
<!--Modal-->
<div id="newTurno" class="modal fade" role="dialog">
    <div class="modal-dialog" role="document">
        <form action="<@s.url '/turno-firma/new' />" class="formEdit" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Asignación de turnos para firma de las autoridades de tránsito</h4>
                </div>
                <div class="modal-body">
                    <label for="exampleSelect1">Seleccione una autoridad de tránsito</label>
                    <div class="form-group">
                        <@s.bind "turnoFirma.autoridad.persona.documentoIdentidad.numero"/>
                        <select id="authorities" class="form-control" name="${s.status.expression}" >
                            <#if autoridades??>
                                <option value="none">Seleccione una opción</option>
                                <#list autoridades as auth>
                                    <#if auth.persona.documentoIdentidad.numero?string == ((turnoFirma.autoridad.persona.documentoIdentidad.numero)!"none")?string >
                                        <option value="${auth.persona.documentoIdentidad.numero}" selected="selected">${auth.persona.primerNombre}&nbsp${auth.persona.segundoNombre}&nbsp${auth.persona.primerApellido}</option>
                                    <#else>
                                        <option value="${auth.persona.documentoIdentidad.numero}">${auth.persona.primerNombre}&nbsp${auth.persona.segundoNombre}&nbsp${auth.persona.primerApellido}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                        <div id="hasError" class="has-error" hidden>
                            <span class="help-block">Debe seleccionar una autoridad.</span>
                        </div>
                    </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    <button id="enviar" type="button" class="btn btn-success btn-md">Nueva asignación</button>
                </div>
                </div><!-- /.modal-body -->
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<@l.script>
  <script type="text/javascript">
    var $rows = $('#turnos-table tr');
    $('#inputBuscar').keyup(function() {

    var val = '^(?=.*\\b' + $.trim($(this).val()).split(/\s+/).join('\\b)(?=.*\\b') + ').*$',
    reg = RegExp(val, 'i'),
    text;

    $rows.show().filter(function() {
        text = $(this).text().replace(/\s+/g, ' ');
        return !reg.test(text);
        }).hide();
    });
    $( "#enviar" ).click(function() {

        if($('#authorities').val()!="none")
            $( ".formEdit" ).submit(); 
        else
            $('#hasError').attr('hidden',false);
    });
    $('.datepickerVigencia').datetimepicker({
       format: '${constants.JQUERY_DATE_FORMAT_PATTERN}',
       timepicker:false,
       mask: true,
       startDate:new Date(),
       minDate:0
    });
  </script>
</@l.script>
</@l.main>
