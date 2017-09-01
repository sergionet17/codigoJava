<#import '/comparendo-layout.ftl' as comp />
<@comp.comparendolayout>
	<h1>Audiencia de aceptación</h1>

	<@l.form action="/comparendo/${comparendo.id}/audiencia-aceptacion" class="form-horizontal">

		<input type="hidden" name="tipo.id" value="${audiencia.tipo.id}" />
		<input type="hidden" name="comparendo.id" value="${audiencia.comparendo.id}" />
		<input type="hidden" name="fecha" value="<@l.dateFormat audiencia.fecha/>" />
		<input type="hidden" name="abogado.id" value="${audiencia.abogado.id}" />

		<div class="panel panel-default">
		  <div class="panel-heading">
		  	<@l.checkBox name="tieneDeudorSolidario" class="marker-abre-panel">
		  		Tiene deudor solidario
		  	</@l.checkBox>
		  </div>
		  <div class="panel-body hidden" id="tieneDeudorSolidarioBody">
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-r">
			    <label for="tipoDocumento">Tipo de identificación</label>
			    <@s.formSingleSelect path="audiencia.deudorSolidario.tipoDocumentoIdentidad.id" options=tipoDocumentoMap attributes="class='form-control'" />
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-l">
			    <label for="numeroIdentificacion">Número de identificación</label>
			    <@l.inputText name="deudorSolidario.numeroDocumentoIdentidad" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-r">
			    <label for="primerNombre">Primer nombre</label>
			    <@l.inputText name="deudorSolidario.primerNombre" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-l">
			    <label for="segundoNombre">Segundo nombre</label>
			    <@l.inputText name="deudorSolidario.segundoNombre" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-r">
			    <label for="primerApellido">Primer apellido</label>
			    <@l.inputText name="deudorSolidario.primerApellido" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-l">
			    <label for="segundoApellido">Segundo apellido</label>
			    <@l.inputText name="deudorSolidario.segundoApellido" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-r">
			    <label for="telefono">Teléfono</label>
			    <@l.inputText name="deudorSolidario.telefono" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-l">
			    <label for="correo">Correo electrónico</label>
			    <@l.inputText name="deudorSolidario.correo" class="form-control" size="40"/>
			  </div>
			</div>
			<div class="col-md-6">
			  <div class="form-group sipa-m-3-r">
			    <label for="porcentaje">Porcentaje</label>
			    <@l.inputText name="porcentajeDeudorSolidario" class="form-control" validator="numberValidator" max="101" />
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-l">
			    <label for="direccion">Dirección</label>
			    <@l.inputText name="deudorSolidario.direccion" class="form-control"size="40"/>
			  </div>
			</div>
		  </div>
		</div>

		<div class="panel panel-default">
		  <div class="panel-heading">
		  	<@l.checkBox name="menorDeEdad" class="marker-abre-panel">
		  		Es menor de edad
		  	</@l.checkBox>
		  </div>
		  <div class="panel-body hidden" id="menorDeEdadBody">
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-r">
			    <label for="tipoDocumento">Tipo de identificación</label>
			    <@s.formSingleSelect 
			    		path="audiencia.representanteLegal.tipoDocumentoIdentidad.id" 
			    		options=tipoDocumentoMap 
			    		attributes="class='form-control' id='tipoDocumento'" />
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-l">
			    <label for="numeroIdentificacion">Número de identificación</label>
			    <@l.inputText name="representanteLegal.numeroDocumentoIdentidad" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-r">
			    <label for="primerNombre">Primer nombre</label>
			    <@l.inputText name="representanteLegal.primerNombre" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-l">
			    <label for="segundoNombre">Segundo nombre</label>
			    <@l.inputText name="representanteLegal.segundoNombre" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-r">
			    <label for="primerApellido">Primer apellido</label>
			    <@l.inputText name="representanteLegal.primerApellido" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-l">
			    <label for="segundoApellido">Segundo apellido</label>
			    <@l.inputText name="representanteLegal.segundoApellido" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-r">
			    <label for="telefono">Teléfono</label>
			    <@l.inputText name="representanteLegal.telefono" class="form-control" size="40"/>
			  </div>
			</div>
		  	<div class="col-md-6">
			  <div class="form-group sipa-m-3-l">
			    <label for="correo">Correo electrónico</label>
			    <@l.inputText name="representanteLegal.correo" class="form-control"  size="40"/>
			  </div>
			</div>
			<div class="col-md-12">
			  <div class="form-group">
			    <label for="direccion">Dirección</label>
			    <@l.inputText name="representanteLegal.direccion" class="form-control" size="40"/>
			  </div>
			</div>
		  </div>
		</div>
		<@l.script>
			<script type="text/javascript">
				$(function(){
					$("input.marker-abre-panel").change(function() {
				        var name = $(this).attr("name");
				        var bodyId = name + "Body";
					    if(this.checked) {
					        $("#" + bodyId).removeClass("hidden");
					    } else {
					    	$("#" + bodyId).addClass("hidden");
					    }
					});
				});
			</script>
		</@l.script>
		<div class="well sipa-well">
			<@l.checkBox name="autorizaEmail">
				El ciudadano autoriza a ser notificado por correo electrónico de ahora en adelante
			</@l.checkBox>
		</div>
		<div class="alert alert-info">
			Mediante la presente audiencia el implicado se declara como <strong>contraventor</strong> y por tanto
			la decisión del proceso quedará como <strong>CONTRAVENTOR</strong>.
		</div>
		<p>
			<@l.formSubmit label="Siguiente >>" />
		</p>
	</@l.form>

</@comp.comparendolayout>