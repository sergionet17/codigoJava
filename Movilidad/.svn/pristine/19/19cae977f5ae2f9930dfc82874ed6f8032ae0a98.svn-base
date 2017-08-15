<@l.main>
	<div class="container">
		<div class="well sipa-well">
			<h1>Pagos</h1>
			<p>A continuación se listan las cargas de pagos realizadas anteriormente ordenadas desde la más reciente.</p>
			<div class="table-responsive">
			<table class="table">
				<thead>
					<tr class="sipa-tr">
						<th>Fecha</th>
						<th>Usuario</th>
						<th>Tipo</th>
						<th>Resultados</th>
						<th>Valores</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<#list pagos as x>
					<tr class="sipa-tr">
						<td>${x.fecha}</td>
						<td>${x.usuario.username}</td>
						<td>${x.tipo.nombre}</td>
						<td>Cargados: ${x.cargados} / Rechazados: ${x.rechazados}</td>
						<td>Valor cargado: ${x.valorCargado} / Valor total: ${x.valorTotal}</td>
						<td><a href="<@s.url controllerStatics.PATH_PAGOS/>/${x.id}" class="btn btn-link">Detalles...</a></td>
					</tr>
					</#list>
				</tbody>
			</table>
			<div class="pull-left">
				<p><a href="<@s.url controllerStatics.PATH_NUEVO_PAGO />" class="btn btn-success">Nueva carga</a></p>
				</div>
			</div>
		</div>
	</div>
</@l.main>