<#macro printDocumento>
	
</#macro>
<@l.main>
	<h1>Plantillas</h1>
	<#if list?? && list?size != 0>
		<table class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Documento</th>
					<th>Motor/Extractor</th>
					<th>Tipo salida</th>
					<th>Banderas</th>
				</tr>
			</thead>
			<tbody>
				<#list list as x>
				<tr>
					<td>${x.nombre}</td>
					<td><@printDocumento x.documento /></td>
					<td>${(x.tipoPlantilla.nombre)!"Sin motor"} / ${(x.beanExtraccion)!"Sin extractor"}</td>
					<td>${(x.contentTypeProducido)!"Sin tipo de salida"}</td>
					<td></td>
				</tr>
				</#list>
			</tbody>
		</table>
	<#else>
		<div class="jumbotron">
			<h1>No hay plantillas :-(</h1>
			<p>En el momento no hay plantillas configuradas en el sistema. Puedes usar el siguiente botón para crear una nueva plantilla. Asegúrate de tener a la mano el archivo de la plantilla ya que te pediremos que lo cargues.</p>
			<a href="<@s.url controllerStatics.PATH_NEW />" class="btn btn-lg btn-success">Crear una plantilla</a>
		</div>
	</#if>
</@l.main>