<@l.main>
<h1>Herramientas para pruebas</h1>
<div class="alert alert-warning">
	<p><strong>¡ADVERTENCIA!</strong> Estas herramientas son únicamente para efectos de las pruebas y su única razón de ser es para apoyar al equipo de pruebas para agilizar algunos procedimientos que se ejecutan a determinadas horas según se indica en los requerimientos.</p>
</div>

<#if FLASH_MESSAGE_ERROR??>
	<div class="alert alert-danger">
		<p><strong>¡ERROR!</strong> ${FLASH_MESSAGE_ERROR}</p>
	</div>
</#if>
<div class="well">
	<h2>Carga de comparendos</h2>
	<p>Esta herramienta invoca el servicio de carga de comparendos tal como lo haría "Lápices ópticos" cuando envía los comparendos a la SDM. Mediante el uso de un archivo CSV podrá enviar cuantos comparendos desee.</p>
	<p><a href="<@s.url controllerStatics.PATH_CARGA_COMPARENDOS />" class="btn btn-primary">Iniciar herramienta</a></p>
</div>
<div class="well">
	<h2>Ejecución de alarmas</h2>
	<p>Ejecuta cada uno de los procesos de alerta configurados en el sistema. Esto se puede hacer para adelantar la revisión de las condiciones de alarma sin tener que esperar a que los procesos se ejecuten en a la hora prevista.</p>
	<p><a href="<@s.url controllerStatics.PATH_EJECUTAR_ALARMA />" class="btn btn-primary" onclick='return confirm("¿Está seguro que desea ejecutar todas las alarmas ahora?");' >Ejecutar todas las alarmas</a></p>
</div>
</@l.main>