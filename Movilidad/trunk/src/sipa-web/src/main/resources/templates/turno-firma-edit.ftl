<@l.main>
<div class="col-md-12">
<div class="well sipa-well">
	<div class="page-header">
	  <h3>Asignación de horarios para firma&nbsp</h3>
	  <small>A continuación seleccione las opciones para configurar el horario y la vigencia de firmas de una autoridad de tránsito.
	  Para finalizar haga click en "Guardar".</small>
	</div>
	<form action="<@s.url '/turno-firma/save' />" class="formEdit" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<div class="row">
			<div class="col-md-3">
				<label>Autoridades</label>
			</div>
			<div class="col-md-3">
				<label>Fecha inicio vigencia</label>
			</div>
			<div class="col-md-3">
				<label>Fecha fin vigencia</label>
			</div>
			<div class="col-md-3">
				<label>Autoridad suplente</label>
			</div>
		</div>
		<div>&nbsp;</div>
		<#if autoridades??>
			<#list autoridades as auth>
				<!--Se cargan las autoridades-->
				<div class="row">
				  	<!--Columna con las autoridades-->
				  	<div class="col-md-3">
				  		<input type="hidden" class="form-control" name="${'auth_'+turnosFirma.auth.id}" value="${turnoFirma.auth.id}" id="autoridad">
				  		<p>${auth.persona.primerNombre}&nbsp${(auth.persona.segundoNombre)!''}&nbsp${(auth.persona.primerApellido)!''}&nbsp${(auth.persona.segundoApellido)!''}</p>
				  	</div>
				  	<!--Columna con fecha inicio vigencia-->
				  	<div class="col-md-3">
				  		<div class='input-group date'>
						    <input type="text"  class="form-control datepickerVigencia" name="${'desde_'+auth.id}" value="${turnosFirma.desde}" id="desde"/>				
						    <span class="input-group-addon">
					    		<span class="glyphicon glyphicon-calendar"></span>
						  	</span>
					    </div>
				  	</div>
				  	<!--Columna con fecha fin vigencia-->
				  	<div class="col-md-3">
				  		<div class='input-group date'>
						    <input type="text"  class="form-control datepickerVigencia" name="${'hasta_'+auth.id}" value="${turnosFirma.hasta}"" id="hasta"/>				
						    <span class="input-group-addon">
					    		<span class="glyphicon glyphicon-calendar"></span>
						  	</span>
					    </div>
				  	</div>
				  	<!--Columna con las autoridades sustitutas-->
				  	<div class="col-md-3">
				  		<div class="form-group">
                        <select id="authorities" class="form-control" name="${'alt_'+auth.id}" value="${'authAlt.suplente.id'}" >
                            <option value="none" name="${'authAlt'}" id="auth-alt0">Seleccione una opción</option>
                            <#list autoridades as authAlt>
                                <#if authAlt.persona.documentoIdentidad.numero?string == ((authAlt.persona.documentoIdentidad.numero)!"none")?string >
                                    <option value="${authAlt.suplente.id}" name="${'alt_'+auth.id}" id="auth-alt1"selected="selected">${authAlt.persona.primerNombre}&nbsp${authAlt.persona.segundoNombre}&nbsp${authAlt.persona.primerApellido}</option>
                                <#else>
                                    <option value="${authAlt.suplente.id}" name="${'alt_'+auth.id}" id="auth-alt2">${authAlt.persona.primerNombre}&nbsp${authAlt.persona.segundoNombre}&nbsp${authAlt.persona.primerApellido}</option>
                                </#if>
                            </#list>
                        </select>
                        <div id="hasError" class="has-error" hidden>
                            <span class="help-block">Debe seleccionar una autoridad.</span>
                        </div>
                    </div>
				  	</div>
				</div>
			</#list>
			<div>&nbsp;</div>
			<div class="row">
				<div class="col-md-2">
					<button type="submit" class="btn btn-primary btn-md">Guardar</button>
				</div>
			</div>
		</form>
		<#else>
			<!--Se muestra un mensaje de error-->
		</#if>
		</div>
</div>
<@l.script>
  <script type="text/javascript">
   $('.datepickerVigencia').datetimepicker({
      format: '${constants.JQUERY_DATE_FORMAT_PATTERN}',
      timepicker:false,
      mask: true,
      startDate:new Date(),
      minDate:0

    });
   $( "#cerrarVigencia" ).click(function() {

        if($('#vigencia').val()==''||$('#vigencia').val()=='____-__-__')
            $('#vigenciaHasError').attr('hidden',false); 
        else
          $( ".formEdit" ).submit();
    });
  </script>
</@l.script>
</@l.main>
