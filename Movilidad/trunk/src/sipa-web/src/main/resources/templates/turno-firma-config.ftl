
<@l.main>
<#if turnos??>
<div class="well sipa-well">
<div class="alert alert-warning" role="alert">${errorMessage}</div>
<div class="list-group">
   	<h3>Configurar los turnos de firma de las turnos de tránsito</h3>
   	<h4><small>A continuación use los campos de fecha para configurar los turnos de firma de las turnos.</small></h4>
   	<br/>
   	<#list turnos as turno>	
   		
		<table class="table table-hover table-condensed">
		<form action="<@s.url '/turno-firma/edit' />" class="formEdit" method="post">
   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<tbody>
			<tr>
				<!--Campo que contiene los datos de la autoridad de tránsito-->
				<td class="active col-sm-3">
				  <dl>
				  	<dt>Nombre</dt>
				  	<@s.bind "turnoFirma.turno.id"/>
				  	<input type="hidden" class="form-control" name="${s.status.expression}" value="${(turnoFirma.turno.id)!''}"/>
				  	<dd><small>${turno.persona.primerNombre}&nbsp${turno.persona.segundoNombre}&nbsp${turno.persona.primerApellido}&nbsp${(turno.persona.segundoApellido)!''}</small></dd>
				  	<dt>Cargo</dt>
				  	<dd><small>${turno.cargo}</small></dd>
				  	<dt>Dependencia</dt>
				  	<dd><small>${turno.dependencia.nombre}</small></dd>
				  </dl>
			  	</td>
			  	<!--Campo que contiene los horarios de firma de la autoridad-->
			  	<td>
				  <div class="row">
	        		<div class='col-sm-6'>
	            		<div class="form-group">
	                		<div class='input-group date'>
	                			<@s.bind "turnoFirma.desde"/>
	                			<input type="text" class="form-control datepicker" name="${s.status.expression}" value="${(turnoFirma.desde?string[constants.JQUERY_DATE_FORMAT_PATTERN])!''}"/>
		                    	<span class="input-group-addon">
		                        	<span class="glyphicon glyphicon-calendar"></span>
		                    	</span>
	                		</div>
	            		</div>
	            		<div class="has-error">
                    		<span class="help-block"><@s.showErrors "<br>"/></span>
                		</div>
	        		</div>
	        	</div>
				</td>
				<!--Campo que contiene la vigencia en la que inica a firmar la autoridad-->
				<td>
				  <div class="row">
	        		<div class='col-sm-6'>
	            		<div class="form-group">
	                		<div class='input-group date'>
	                			<@s.bind "turnoFirma.desde"/>
	                			<input type="text" class="form-control datepicker" name="${s.status.expression}" value="${(turnoFirma.desde?string[constants.JQUERY_DATE_FORMAT_PATTERN])!''}"/>
		                    	<span class="input-group-addon">
		                        	<span class="glyphicon glyphicon-calendar"></span>
		                    	</span>
	                		</div>
	            		</div>
	            		<div class="has-error">
                    		<span class="help-block"><@s.showErrors "<br>"/></span>
                		</div>
	        		</div>
	        	</div>
				</td>
				<!--Campo que contiene la fecha fin de la vigencia -->
				<td>
				   <div class="row">
	    			    <div class='col-sm-6'>
	        			     <div class="form-group">	JQUERY_DATE_FORMAT_PATTERN
			            		<div class='input-group date'>
			                		<@s.bind "turnoFirma.desde"/>
									<input type="text" class="form-control datepicker" name="${s.status.expression}" value="${(turnoFirma.hasta??string[constants.DATE_TIME_FORMAT_PATTERN])!''}"/>
			                		<span class="input-group-addon">
			                    		<span class="glyphicon glyphicon-calendar"></span>
			                		</span>
		            			</div>
		            			<div class="has-error">
                    				<span class="help-block"><@s.showErrors "<br>"/></span>
                				</div>
	            			</div>
	            		</div>
	            	</div>
				 </td> 
				 <td>
				   <div class="row">
	    			    <div class='col-sm-2'>
	        			     <div class="form-group">	
			            		<button class="btn btn-primary btn-md">Guardar cambios</button>
	            			</div>
	            		</div>
	            	</div>
				 </td> 
			</tr>
		</tbody>
		</form>
		</table>
		
	</#list>
	</div>
</div>
<#else>
	<div class="jumbotron">
  		<h1 class="display-3">Asignación de turnos</h1>
  		<p class="lead">En este momento no se encontraron asignaciones para firma de las autoridades de tránsito.</p>
  		<hr class="my-4">
  		<p>Puse el botón para empezar a crear una nueva asignación.</p>
  		<p class="lead">
    		<a class="btn btn-success btn-md" href="" role="button">Nuevo</a>
  		</p>
	</div>
</#if>
</@l.main>