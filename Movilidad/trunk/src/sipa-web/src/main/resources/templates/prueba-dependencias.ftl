<@l.main>
<div class="well sipa-well">
	<@l.form action="/prueba/dependencias">
		<@l.formGroupText name="texto" label="Dependencia" />
		<@l.selectorDependencias dependencias "idDependencia" />
		<@l.formSubmit />
	</@l.form>
	</div>
</@l.main>