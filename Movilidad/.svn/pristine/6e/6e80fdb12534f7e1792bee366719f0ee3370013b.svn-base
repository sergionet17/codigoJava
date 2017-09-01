<@l.main>
  <div class="container">
    <div class="col-md-7">
    	<h1>Editar configuracion rango</h1>
    	<form  action="<@s.url '/editarRango'/>" id="formEditarRango" method="post" >
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    	<@s.bind "rangoForm.id"/>
    	<input type="hidden" class="form-control" name="${s.status.expression}" value="${(rangoForm.id)!''}" id="id"/>
    	<fieldset>
    	<table class="table table-condensed">
			<thead>
			  <tr>
				<th>Nombre</th>
				<th>Dias</th>
				<th>TipoDia</th>
				<th>TipoNotificacion</th>
			  </tr>
			</thead>
			<tbody>  
				<tr>
				  <td>${(rangoForm.nombre)!""}</td>
				  <td>${(rangoForm.dias)!""}</td>
				  <td>
				  	<div class="form-group">
				  		<@s.bind "rangoForm.tipoDia.id"/>
						<@s.formSingleSelect "rangoForm.tipoDia.id", tiposDia, " class='chosen-select'"/>
						<div class="has-error">
						  <span class="help-block"><@s.showErrors "<br>"/></span>
						</div>
					</div>	
				  </td>
				  <td>
		  			<div class="form-group">
						<@s.bind "rangoForm.tipoNotificacion.id"/>
						<@s.formSingleSelect "rangoForm.tipoNotificacion.id", tiposNotificacion, " class='chosen-select'"/>
						<div class="has-error">
						  <span class="help-block"><@s.showErrors "<br>"/></span>
						</div>
					</div>
				  </td>
				</tr>
			</tbody>
		  </table>
		  </fieldset>
		  <button class="btn btn-primary" type="button" id="btnGuardarRango">Guardar</button>
    	</form>
	</div>
</div>


<@l.script>
  <script type="text/javascript">
    <!-- 
      $("#btnGuardarRango").click(function() {
          var $form = $('#formEditarRango');
		  $form.submit();
      });
</script>
</@l.script>

</@l.main>