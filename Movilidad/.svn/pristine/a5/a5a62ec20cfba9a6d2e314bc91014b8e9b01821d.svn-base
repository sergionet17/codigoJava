<@l.main>
	<div class="container">
		<div class="well sipa-well">
			<h1>Detalle de la carga de archivo</h1>
			<p>A continuación los resultados de la carga del archivo de pagos</p>
			<p>Tipo de archivo: ${pago.tipo.nombre}, cargado en ${pago.fecha}</p>
			<table class="table">
				<thead>
					<tr class="sipa-tr">
						<th>Índice</th>
						<th>Referencia</th>
						<th>Valor</th>
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
					<#list pago.pagos as x>
					<tr class="sipa-tr">
						<td>${x?index}</td>
						<td>${x.referencia}</td>
						<td>${x.valor}</td>
						<td><span class="label <#if x.estadoPago.id == 2>label-info<#else>label-danger</#if>">${x.estadoPago.nombre}</span></td>
					</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</div>
</@l.main>