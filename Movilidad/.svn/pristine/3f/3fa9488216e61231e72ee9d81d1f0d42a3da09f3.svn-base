<@l.main>
  <div class="container">
    <div class="col-md-8">
    	<h1>Alarma</h1>
    	<@s.bind "alarmaForm.clave"/>
    	<input type="hidden" class="form-control" name="${s.status.expression}" value="${(alarmaForm.clave)!''}" id="id"/>
				<div class="form-group">
    				<label for="alarmaForm.clave">${(alarmaForm.clave)!''}</label>
    			</div>
				<div class="form-group">
    				<label for="alarmaForm.clave">${(alarmaForm.descripcion)!''}</label>
    			</div>
				<div>
				<table class="table table-condensed">
					<thead>
					  <tr>
						<th>Nombre</th>
						<th>Dias</th>
						<th>TipoDia</th>
						<th>TipoNotificacion</th>
						<th></th>
					  </tr>
					</thead>
					<tbody>
					  <#list alarmaForm.rangos as rango>
						<tr>
						  <td>${(rango.nombre)!""}</td>
						  <td>${(rango.dias)!""}</td>
						  <td>${(rango.tipoDia.nombre)!""}</td>
						  <td>${(rango.tipoNotificacion.nombre)!""}</td>
						  <td><a class="btn btn-primary" href="<@s.url '/editarRango'+'/${rango.id}'/>">Cambiar Configuracion</a></td>
						</tr>
					  </#list>
					</tbody>
				  </table>
				</div>
    </div>
  </div>
</@l.main>