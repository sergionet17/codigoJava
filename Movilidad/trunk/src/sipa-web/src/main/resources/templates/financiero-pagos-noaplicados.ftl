<@l.main>
	<div class="container">
		<div class="well sipa-well">
			<h1>Pagos no aplicados</h1>
			<p>A continuación los pagos que no se han podido aplicar automáticamente</p>
			<div class="table-responsive">
			<table class="table">
				<thead>
					<tr class="sipa-tr">
						<th>Índice</th>
						<th>Referencia</th>
						<th>Fecha</th>
						<th>Valor</th>
						<th>Archivo</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<#list pagos as x>
					<tr class="sipa-tr">
						<td>${x?index}</td>
						<td>${x.referencia}</td>
						<td>${x.fecha}</td>
						<td>${x.valor}</td>
						<td><a href="<@s.url controllerStatics.PATH_PAGOS/>/${x.archivoPagos.id}">Ver archivo...</a></td>
						<td><a href="#">Aplicar...</a></td>
					</tr>
					</#list>
				</tbody>
			</table>
			</div>
		</div>
	</div>
</@l.main>