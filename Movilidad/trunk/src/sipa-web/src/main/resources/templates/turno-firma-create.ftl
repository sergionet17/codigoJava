<#import "/spring.ftl" as s/>
<@l.main>
<#macro mostrarErrors id map>
<#if map??>
	<#if map["desdeOverlap_${id}"]?? >
		<div class="has-error"><span class="help-block">${map["desdeOverlap_${id}"]}</span></div>
	</#if>
</#if>
</#macro>
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
				<label>Autoridad</label>
			</div>
			<div class="col-md-2">
				<label>Fecha inicio vigencia</label>
			</div>
			<div class="col-md-2">
				<label>Fecha fin vigencia</label>
			</div>
			<div class="col-md-2">
				<label>Autoridad suplente</label>
			</div>
			<div class="col-md-2">
				<label>Activo</label>
			</div>
		</div>
		<div>&nbsp;</div>

		<#if turnosFirma??>
			<#list turnosFirma as tf>
				<!--Se cargan las autoridades-->
				<div class="row">
				  	<!--Columna con las autoridades-->
				  	<div class="col-md-3">
				  		<input type="hidden" class="autoridad" name="${'autoridad_'+tf.autoridad.id}" value="${tf.autoridad.id}" id="">
				  		<p>${tf.autoridad.persona.primerNombre}&nbsp${(tf.autoridad.persona.segundoNombre)!''}&nbsp${tf.autoridad.persona.primerApellido}&nbsp${(tf.autoridad.persona.segundoApellido)!''}</p>
				  	</div>
				  	<!--Columna con fecha inicio vigencia-->
				  	<div class="col-md-2">
				  		<div class='input-group date'>																
						    <input type="text"  class="form-control datepickerVigencia" name="${'desde_'+tf.autoridad.id}" value="${(tf.desde?string[constants.DATE_FORMAT_PATTERN])!''}"  id="${'desde_'+tf.autoridad.id}"/>			
						    <span class="input-group-addon">
					    		<span class="glyphicon glyphicon-calendar"></span>
						  	</span>
					    </div>
					    <div id="errorDateOverlaps">
                            <span class="help-block"><#if errorMap?? ><@mostrarErrors tf.autoridad.id errorMap/></#if></span>
                        </div>
					    <div id="${'desdeHasError_'+tf.autoridad.id}" class="has-error" hidden="true">
                            <span id="${'desdeHasErrorMsg_'+tf.autoridad.id}" class="help-block">Debe seleccionar una fecha inicial.</span>
                        </div>
				  	</div>
				  	<!--Columna con fecha fin vigencia-->
				  	<div class="col-md-2">
				  		<div class='input-group date'>
						    <input type="text"  class="form-control datepickerVigencia" name="${'hasta_'+tf.autoridad.id}" value="${(tf.hasta?string[constants.DATE_FORMAT_PATTERN])!''}"  id="${'hasta_'+tf.autoridad.id}"/>				
						    <span class="input-group-addon">
					    		<span class="glyphicon glyphicon-calendar"></span>
						  	</span>
					    </div>
					    <div id="${'hastaHasError_'+tf.autoridad.id}" class="has-error" hidden="true">
                            <span id="${'hastaHasErrorMsg_'+tf.autoridad.id}" class="help-block">Debe seleccionar una fecha final.</span>
                        </div>
				  	</div>
				  	<!--Columna con las autoridades sustitutas-->
				  	<#if autoridades??>
					  	<div class="col-md-2">
					  		<div class="form-group">
		                        <select id="${'suplente_'+tf.autoridad.id}" class="form-control" name="${'suplente_'+tf.autoridad.id}">
		                            <option value="none">Seleccione una opción</option>
		                            <#list autoridades as authAlt>
			                            <#if authAlt.id!=tf.autoridad.id>
			                                <#if authAlt.id?string == ((tf.suplenteOficial.id)!"none")?string>
			                                    <option  value="${tf.suplenteOficial.id}" name="${'suplente_'+tf.autoridad.id}" id="auth-alt1" selected="selected">${authAlt.persona.primerNombre}&nbsp${(authAlt.persona.segundoNombre)!''}&nbsp${authAlt.persona.primerApellido}${(authAlt.persona.segundoApellido)!''}</option>
			                                <#else>
			                                    <option value="${authAlt.id}" name="${'suplente_'+tf.autoridad.id}" id="auth-alt3">${authAlt.persona.primerNombre}&nbsp${(authAlt.persona.segundoNombre)!''}&nbsp${authAlt.persona.primerApellido}${(authAlt.persona.segundoApellido)!''}</option>
			                                </#if>
			                            </#if>
		                            </#list>
		                        </select>
		                        <div id="${'suplenteHasError_'+tf.autoridad.id}" class="has-error" hidden="true">
		                            <span id="${'suplenteHasErrorMsg_'+tf.autoridad.id}" class="help-block" >Debe seleccionar una autoridad.</span>
		                    	</div>
	                    	</div>
				  		</div>
				  		</#if>
				  		<div class="col-md-2">
				  		<p><#if !tf.activo><span class="glyphicon glyphicon-remove"></span><#else><span class="glyphicon glyphicon-ok"></span></#if></p>
				  	</div>
				  		</div>
					
					
			</#list>
			<div>&nbsp;</div>
			<div class="row">
				<div class="col-lg-2">
					<button id="guardar" type="button" class="btn btn-primary btn-md btn-block">Guardar</button>
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
  //Función personalizada para los inputs de fecha
   $('.datepickerVigencia').datepicker({
      format: '${constants.JQUERY_DATE_FORMAT_PATTERN}',
      timepicker:false,
      mask: true,
      startDate:false,
      minDate:0

    });
   	//Previene el envío del request sin validar los campos
    var hasErrors=false;
   	$( "#guardar" ).click(function() {
   		var count=0;
   		var totalErr=0;
   		
   		$('.autoridad').each(function() {
		    var currentElement = $(this);
			var idAuth = currentElement.val();
			hasErrors=validateForm(idAuth);
			if(hasErrors)
				totalErr=totalErr+1;
			
		});
		
		if(totalErr==0)
			$( ".formEdit" ).submit();
	});
	//Función que se encarga de la validación de los campos del formulario.
	function validateForm(idAuth) {
		var counter=0;
		var counter2=0;
		if($('#desde_'+idAuth).val()!='____-__-__'&&$('#hasta_'+idAuth).val()!='____-__-__'&&$('#suplente_'+idAuth).val()=='none'){
			$('#desdeHasError_'+idAuth).attr('hidden',true);
			$('#hastaHasError_'+idAuth).attr('hidden',true);
			$('#suplenteHasError_'+idAuth).attr('hidden',false);
			//Valida las fechas
			var inicial = new Date($('#desde_'+idAuth).val());
   			var final = new Date($('#hasta_'+idAuth).val());
   			inicial.getTime();
   			final.getTime();
			if(final<inicial){
				$('#desdeHasError_'+idAuth).attr('hidden',false);
				$('#desdeHasErrorMsg_'+idAuth).text('La fecha inicial no puede ser mayor a la final.');
			}
			if(final.getTime()==inicial.getTime()){
				$('#desdeHasError_'+idAuth).attr('hidden',false);
				$('#hastaHasError_'+idAuth).attr('hidden',false);
				$('#desdeHasErrorMsg_'+idAuth).text('Las fechas no deben ser iguales.');
				$('#hastaHasErrorMsg_'+idAuth).text('Las fechas no deben ser iguales.');
			}
			
			counter=counter+1;
			//alert("Countrer1="+counter);
			
			}else if($('#desde_'+idAuth).val()!='____-__-__'&&$('#hasta_'+idAuth).val()=='____-__-__'&&$('#suplente_'+idAuth).val()=='none'){

			
			$('#desdeHasError_'+idAuth).attr('hidden',true);
			$('#hastaHasError_'+idAuth).attr('hidden',false); 
			$('#suplenteHasErrorMsg_'+idAuth).attr('hidden',false);  
			
			counter=counter+1;
			//alert("Countrer2="+counter);
			}else if($('#desde_'+idAuth).val()=='____-__-__'&&$('#hasta_'+idAuth).val()!='____-__-__'&&$('#suplente_'+idAuth).val()=='none'){
		 	
			$('#desdeHasError_'+idAuth).attr('hidden',false); 
			$('#hastaHasError_'+idAuth).attr('hidden',true); 
			$('#suplenteHasError_'+idAuth).attr('hidden',false); 
			
			counter=counter+1;
			//alert("Countrer3="+counter);
			}else if($('#desde_'+idAuth).val()!='____-__-__'&&$('#hasta_'+idAuth).val()=='____-__-__'&&$('#suplente_'+idAuth).val()!='none'){
			
			$('#desdeHasError_'+idAuth).attr('hidden',true);
			$('#hastaHasError_'+idAuth).attr('hidden',false); 
			$('#suplenteHasError_'+idAuth).attr('hidden',true);
			
			counter=counter+1;
			//alert("Countrer4="+counter);
			}else if($('#desde_'+idAuth).val()=='____-__-__'&&$('#hasta_'+idAuth).val()=='____-__-__'&&$('#suplente_'+idAuth).val()!='none'){
			
			$('#desdeHasError_'+idAuth).attr('hidden',false);        
			$('#hastaHasError_'+idAuth).attr('hidden',false);
			$('#suplenteHasError_'+idAuth).attr('hidden',true);
			
			counter=counter+1;
			//alert("Countrer5="+counter);
			}else  if($('#desde_'+idAuth).val()=='____-__-__'&&$('#hasta_'+idAuth).val()!='____-__-__'&&$('#suplente_'+idAuth).val()!='none'){
				$('#desdeHasError_'+idAuth).attr('hidden',false);        
				$('#hastaHasError_'+idAuth).attr('hidden',true);
				$('#suplenteHasError_'+idAuth).attr('hidden',true);
			 	
				counter=counter+1;
				//alert("Countrer6="+counter);
			}
			var inicial = new Date($('#desde_'+idAuth).val());
   			var final = new Date($('#hasta_'+idAuth).val());
   			inicial.getTime();
   			final.getTime();
			if(final<inicial){
				$('#desdeHasError_'+idAuth).attr('hidden',false);
				$('#desdeHasErrorMsg_'+idAuth).text('La fecha inicial no puede ser mayor a la final.');
				counter=counter+1;
			}
			if(final.getTime()==inicial.getTime()){
				$('#desdeHasError_'+idAuth).attr('hidden',false);
				$('#hastaHasError_'+idAuth).attr('hidden',false);
				$('#desdeHasErrorMsg_'+idAuth).text('Las fechas no deben ser iguales.');
				$('#hastaHasErrorMsg_'+idAuth).text('Las fechas no deben ser iguales.');
				counter=counter+1;
			}		
		//alert("CountrerFinal="+counter);
		if(counter==0)
			return false
		else
			return true;   
		
	}
</script>
</@l.script>
</@l.main>
