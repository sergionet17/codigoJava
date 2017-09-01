<@l.main>
<div class="well sipa-well">
RESOLUCION No. ${resolucionAutomaticaDeFallo.numero}
<br>COMPARENDO No. ${resolucionAutomaticaDeFallo.comparendo.numero}
<br>FECHA COMPARENDO: ${resolucionAutomaticaDeFallo.comparendo.fechaImposicion}
<br>INFRACCIÓN: ${resolucionAutomaticaDeFallo.comparendo.infraccion.codigo}
<br>PROPIETARIO: ${resolucionAutomaticaDeFallo.comparendo.vehiculo.propietario.obtenerNombreCompleto()}
<br>CEDULA DE CIUDADANÍA No: ${resolucionAutomaticaDeFallo.comparendo.vehiculo.propietario.numeroDocumentoIdentidad}
<br>VEHÍCULO PLACA: ${resolucionAutomaticaDeFallo.comparendo.vehiculo.propietario.obtenerNombreCompleto()}
<br>CEDULA DE CIUDADANÍA No: ${resolucionAutomaticaDeFallo.comparendo.vehiculo.placaVehiculo}
<br>SERVICIO: ${resolucionAutomaticaDeFallo.comparendo.vehiculo.clase.nombre}

<br>DESCRIPCION INFRACCION: ${resolucionAutomaticaDeFallo.comparendo.infraccion.nombre}

<br>AUTORIDAD: resolucionAutomaticaDeFallo.autoridad.persona.obtenerNombreCompleto()}

<br>INFRACTOR: ${resolucionAutomaticaDeFallo.comparendo.infractor.obtenerNombreCompleto()}
<br>INFRACTOR CEDULA DE CIUDADANÍA No: ${resolucionAutomaticaDeFallo.comparendo.infractor.numeroDocumentoIdentidad}

<br>CANTIDAD DE SALARIOS: ${resolucionAutomaticaDeFallo.comparendo.infraccion.valor} 

<br>DESCRIPCION UNIDAD DE MEDIDA: ${resolucionAutomaticaDeFallo.comparendo.valorNominal} 

</div>
</@l.main>