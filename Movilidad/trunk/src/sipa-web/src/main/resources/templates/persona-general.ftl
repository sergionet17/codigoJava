<#import '/persona-layout.ftl' as per />
<@per.personalayout>
<div>&nbsp;</div>
<#if persona??>
	<div class="panel panel-info">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<h5>Vista de persona</h5>
		</div>
		<div class="panel-body">

		</div>
		<!-- Table -->
		<div class="table-responsive">
		<table class="table table-hover table-bordered table-condensed">
			<tbody>
				<tr class="sipa-tr">
					<th>Tipo Documento</th>
					<td>${persona.tipoDocumentoIdentidad.sigla}</td>
				</tr>
				<tr>
					<th>Documento</th>
					<td>${persona.numeroDocumentoIdentidad}</td>
				</tr>
				<tr>
					<th>Nombres</th>
					<td>${persona.primerNombre}&nbsp${(persona.segundoNombre)!""}</td>
				</tr>
				<tr>
					<th>Apellidos</th>
					<td>${persona.primerApellido}&nbsp${(persona.segundoApellido)!""}</td>
				</tr>
				<tr>
					<th>Email</th>
					<td><#list persona.emails as correo>
                           ${correo.email!""} 
                           <br/>
                        </#list>
                    </td>
				</tr>
			</tbody>
		</table>
		</div>
<#else>
<div class="jumbotron">
	<h1>Consulta de personas</h1>
	<p class="lead">En este momento no fue posible cargar la consulta. Por favor intente más tarde.</p>		  
</div>
</div>
 </#if>
</@per.personalayout>