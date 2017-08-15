<#macro vehiculolayout>
<@l.main>
<div>&nbsp;</div>
<#if vehiculo??>
	<!--Menú lateral que contiene información básica de la persona y las acciones a realizar-->
	<div class="col-md-3" style="background: #fff;">
		<div class="panel panel-default">
	  		<div class="panel-heading"><h5>Placa vehículo:&nbsp${vehiculo.placaVehiculo}</h5></div>
	  		<div class="panel-body">
	    		<dl class="sipa-vehiculo">
			        <dt>Tipo de vehículo</dt>
	                <dd><small>${(vehiculo.tipo.nombre)!""}</small></dd>
	                <dt>Estado del vehículo</dt>
	                <dd><small>${(vehiculo.estado.nombre)!""}</small></dd>
	                <dt></dt>
	                <dd><small><a href="#">Procesos contravencionales <span class="badge"></span></a></small></dd>
	                <dt></dt>
	        		<dd><small><a href="<@s.url '/vehiculo/comparendos-asociados/${vehiculo.placaVehiculo}' />">Comparendos asociados <span class="badge">${numComparendos}</span></a></small></dd>
	    		</dl>
	  		</div>
			<div class="panel-footer">
	            <ul class="nav nav-pills nav-stacked">
	                <li role="presentation"><a href="#">Imprimir estado de cuenta</a></li>
	                <li role="presentation"><a href="#">Ver requisitos de salida</a></li>
	                <li role="presentation"><a href="#">Iniciar salida de patios</a></li>
	                <li role="presentation"><a href="#">Cargar pruebas de subsanación</a></li>
	                <li role="presentation"><a href="#">Cargar pruebas de salida provisional</a></li>
	            </ul>
	        </div>
		</div> 
	</div>
	<!--Menú con tabs que administra el contenido-->
	<div class="col-md-9">
		<ul class="nav nav-tabs">
			<#if !activePill??><#assign activePill = ''/></#if>
			<#if activePill = 'consulta'>
				<li role="presentation" class="active"><a href="#">Consulta</a></li>
			<#else>
				<li role="presentation"><a href="<@s.url '/vehiculo/general/${vehiculo.id}' />">Consulta</a></li>
			</#if>
			<#if activePill = 'asociados'>
				<li role="presentation" class="active"><a href="<@s.url '/vehiculo/comparendos-asociados/${vehiculo.id}' />">Comparendos asociados</a></li>
			<#else>
				<li role="presentation"><a href="<@s.url '/vehiculo/comparendos-asociados/${vehiculo.id}' />">Comparendos asociados</a></li>
			</#if>
			<#if activePill = 'reincidencias'>
				<li role="presentation" class="active"><a href="#">Reincidencias</a></li>
			<#else>
				<li role="presentation"><a href="#">Reincidencias</a></li>
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
</#if>
</@l.main>
</#macro>