<#macro comparendolayout>
<@l.main>
<#if comparendo??>
	<div class="well">
		<div class="row">
			<!--Menú lateral que contiene información básica de la persona y las acciones a realizar-->
			<div class="col-md-3">
	            <h1 class="has-id">Comparendo
	                <small class="hidden is-id">${comparendo.id}</small>
	            </h1>
	            <p><span class="label sipa-label">${comparendo.numero.valor}</span></p>
	            <p>
	                <span class="label label-primary">${(comparendo.tipoComparendo.nombre)!''}</span>
	                <span class="label label-danger"
	                      title="${comparendo.infraccion.nombre}">${comparendo.infraccion.codigo}</span>
	            </p>
	            <p><i class="glyphicon glyphicon-user"></i>
	                (${comparendo.infractor.tipoDocumentoIdentidad.sigla}-${comparendo.infractor.numeroDocumentoIdentidad})
	                ${(comparendo.infractor.obtenerNombreCompleto())!""}</p>
	            <div><strong>Vehículo: </strong>${(comparendo.vehiculo.placaVehiculo)!''}</div>
	            <div><strong>Imposición: </strong>
	                <@l.dateFormat comparendo.fechaImposicion />
	            </div>
	            <div><strong>Notificación: </strong>
	                <@l.dateFormat (comparendo.fechaNotificacion)!"" />
	            </div>
	            <div><strong>Estado: </strong>${(comparendo.estado.nombre)!""}</div>
	            <div><strong>Decisión: </strong>${(comparendo.decision.nombre)!'Ninguna'}</div>
	            <div><strong>Valor nominal: </strong>
	                <@l.currencyFormat (comparendo.valorNominal)!0 />
	            </div>
			</div>
			<!--Menú con tabs que administra el contenido-->
			<div class="col-md-9">
	            <p>
	                <ul class="nav nav-tabs">
	                    <#if !activePill??><#assign activePill = ''/></#if>
	                	<#if activePill = 'expediente'>
	                		<li role="presentation" class="active"><a href="#">Expediente</a></li>
	                	<#else>
	                		<li role="presentation"><a href="<@s.url '/comparendo/expediente/${comparendo.id}' />">Expediente</a></li>
	            		</#if>
	            		<#if activePill = 'audiencias'>
							<li role="presentation" class="active"><a href="#">Audiencias</a></li>
						<#else>
	            			<li role="presentation"><a href="<@s.url controllerStatics.CONTROLLER_PATH_AUDIENCIAS />/${comparendo.id}">Audiencias</a></li>
						</#if>
						<#if activePill = 'cartera'>
							<li role="presentation" class="active"><a href="#">Cartera</a></li>
						<#else>
							<li role="presentation"><a href="<@s.url '/comparendo/cartera/${comparendo.id}' />">Cartera</a></li>
						</#if>
					</ul>
	        	</p>
				<!--Contenido-->
				<#nested/>
			</div>
		</div>
	</div>
<#else>
	<div class="jumbotron">
		<h1>Comparendo no encontrado</h1>
		<p>El comparendo solicitado no ha sido registrado en el sistema. Intente realizando una nueva consulta.</p>
	</div>
</#if>
</@l.main>
</#macro>