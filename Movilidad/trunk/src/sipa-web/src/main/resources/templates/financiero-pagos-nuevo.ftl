<@l.main>
	<div class="container">
		<div class="well sipa-well">
			<h1>Nuevo archivo de pagos</h1>
			<p>Complete los datos del siguiente formulario para cargar un nuevo archivo de pagos.</p>
			<@l.form action="${controllerStatics.PATH_PAGOS}" class="form-horizontal" enctype="multipart/form-data">
				<@l.formGroupSingleSelect name="tipo" label="Tipo de archivo" path="entity.tipo" options=tipos />
				<@l.formGroupFile name="archivo" label="Archivo" placeholder="Seleccione el archivo con los pagos" />
				<@s.showErrors separator=","/>
				<@l.formSubmitDoubleButton labelb1="Cargar" labelb2="Cancelar" path="<@s.url controllerStatics.PATH_PAGOS "/>
			</@l.form>
		</div>
	</div>
</@l.main>