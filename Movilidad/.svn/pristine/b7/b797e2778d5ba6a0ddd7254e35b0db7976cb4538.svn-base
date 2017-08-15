<#macro personalayout>
<@l.main>
<div>&nbsp;</div>
<!--Menú lateral que contiene información básica de la persona y las acciones a realizar-->
<div class="col-md-3" style="background: #fff;">
	<#if persona??>
	<div class="panel panel-default">
  		<h3>Persona</h3>
  		<div class="panel-body">
    		<dl class="sipa-persona">
		        <dd><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp<strong>${persona.primerNombre}&nbsp ${(persona.segundoNombre)!""}${persona.primerApellido}&nbsp${(persona.segundoApellido)!""}</strong></dd>
		        <dd>${(persona.tipoDocumentoIdentidad.sigla)!''}<span>&nbsp${persona.numeroDocumentoIdentidad}</span></dd>
		        <dd>
			        <#list persona.emails as correo>
	                   ${correo.email!""} 
	                   <br/>
	                </#list>
                </dd>
    		</dl>
  		</div>
	        <ul class="nav nav-pills nav-stacked">
	            <li role="presentation" class="active"><a href="#">Imprimir estado de cuenta</a></li>
	            <li role="presentation"><a href="#">Registrar custodia de licencia</a></li>
	            <li role="presentation"><a href="#">Devolver licencia en custodia</a></li>
	            <li role="presentation"><a href="#">Iniciar audiencia</a></li>
	        </ul>
	</div> 
	</#if>
</div>
<!--Menú con tabs que administra el contenido-->
<div class="col-md-9">
	<div class="well">
		<ul class="nav nav-tabs">
			<#if !activePill??><#assign activePill = ''/></#if>
			<#if activePill = 'asociados'>
				<li role="presentation" class="active"><a href="#">Comparendos asociados</a></li>
			<#else>
				<li role="presentation"><a href="<@s.url '/persona/comparendos-asociados/${persona.id}' />">Comparendos asociados</a></li>
			</#if>
			<#if activePill = 'reincidencias'>
				<li role="presentation" class="active"><a href="#">Reincidencias</a></li>
			<#else>
				<li role="presentation"><a href="<@s.url '/persona/reincidencias/${persona.id}' />">Reincidencias</a></li>
			</#if>
			<#if activePill = 'suspencion-licencia'>
				<li role="presentation" class="active"><a href="#">Suspenciones de licencia</a></li>
			<#else>
				<li role="presentation"><a href="#">Suspenciones de licencia</a></li>
			</#if>	
		</ul>
	<!--Contenido-->
	<#nested/>
	</div>
</div>
</@l.main>
</#macro>