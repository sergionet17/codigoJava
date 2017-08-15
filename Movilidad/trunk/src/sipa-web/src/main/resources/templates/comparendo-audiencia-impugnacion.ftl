<#import 'comparendo-layout.ftl' as comp />
<@comp.comparendolayout>
<h2>Apertura audiencia de impugnación</h2>
<@l.form action="/comparendo/${comparendo.id}/audiencia-impugnacion" class="form-horizontal">
<input type="hidden" name="tipo.id" value="${audiencia.tipo.id}" />
<input type="hidden" name="comparendo.id" value="${audiencia.comparendo.id}" />
<input type="hidden" name="fecha" value="<@l.dateFormat audiencia.fecha/>" />
<input type="hidden" name="abogado.id" value="${audiencia.abogado.id}" />
<div class="panel panel-default">
    <div class="panel-body">
        <h4>Tiene apoderado</h4>
        <div class="panel-heading">
            <@l.checkBox name="tieneDeudorSolidario" class="form-horizontal">
            Si
            </@l.checkBox>
            <@l.checkBox name="tieneDeudorSolidario" class="form-horizontal">
            No
            </@l.checkBox>
        </div>
        <p>
            <div class="page-header">
                <h4>A continuación, ingrese los datos del apoderado</h4>
            </div>
        </p>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-r">
                <label for="usr">Número de cedula:</label>
                <@l.inputText name="apoderado.numeroDocumentoIdentidad" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-l">
                <label for="pwd">Primer nombre:</label>
                <@l.inputText name="apoderado.primerNombre" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-r">
                <label for="pwd">Segundo nombre:</label>
                <@l.inputText name="apoderado.segundoNombre" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-l">
                <label for="pwd">Primer apellido:</label>
                <@l.inputText name="apoderado.primerApellido" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-r">
                <label for="pwd">Segundo apellido:</label>
                <@l.inputText name="apoderado.segundoApellido" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-l">
                <label for="pwd">Teléfono:</label>
                <@l.inputText name="apoderado.telefono" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-r">
                <label for="pwd">Dirección de correspondencia:</label>
                <@l.inputText name="apoderado.direccion" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-l">
                <label for="pwd">Correo electrónico:</label>
                <@l.inputText name="apoderado.correo" class="form-control" size="40" />
            </div>
        </div>
        <div class="page-header">
            <h4>A continuación, ingrese los datos del representante</h4>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-r">
                <label for="tipoDocumento">Tipo de identificación</label>
                <@s.formSingleSelect path="audiencia.representanteLegal.tipoDocumentoIdentidad.id" options=tipoDocumentoMap attributes="class='form-control' id='tipoDocumento'" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-l">
                <label for="usr">Numero de cedula:</label>
                <@l.inputText name="representanteLegal.numeroDocumentoIdentidad" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-r">
                <label for="pwd">Primer Nombre:</label>
                <@l.inputText name="representanteLegal.primerNombre" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-l">
                <label for="segundoNombre">Segundo nombre</label>
                <@l.inputText name="representanteLegal.segundoNombre" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-r">
                <label for="pwd">Primer apellido:</label>
                <@l.inputText name="representanteLegal.primerApellido" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-l">
                <label for="pwd">Segundo apellido:</label>
                <@l.inputText name="representanteLegal.segundoApellido" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group sipa-m-3-r">
                <label for="pwd">Teléfono:</label>
                <@l.inputText name="representanteLegal.telefono" class="form-control" size="40" />
            </div>
        </div>
        
        <div class="col-md-6">
            <div class="form-group sipa-m-3-l">
                <label for="pwd">Correo electrónico:</label>
                <@l.inputText name="representanteLegal.correo" class="form-control" size="40" />
            </div>
        </div>
        <div class="col-md-12">
            <div class="form-group">
                <label for="pwd">Dirección de correspondencia:</label>
                <@l.inputText name="representanteLegal.direccion" class="form-control" size="40" />
            </div>
        </div>
        <p>
            <h4>¿Autoriza a la secretaria de movilidad para el envió de correspondencia vía correo electrónico?</h4>
        </p>
        <label class="radio-inline">
            <input type="radio" name="optradio">Si
        </label>
        <label class="radio-inline">
            <input type="radio" name="optradio">No
        </label>
        <p>
            <h4>A continuación, use las siguientes casillas para el registro de la decisión tomada</h4>
        </p>
        <label class="radio-inline">
            <input type="radio" name="optradio">Contraventor
        </label>
        <label class="radio-inline">
            <input type="radio" name="optradio">Solicitar revisión de exoneración
        </label>
        <label class="radio-inline">
            <input type="radio" name="optradio">Suspender audiencia – Programar nueva
        </label>
        <p>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Observaciones:</label>
                <div class="col-sm-10">
                    <textarea class="form-control" rows="5" id="comment"></textarea>
                </div>
            </div>
            <p>
                <h4>Recurso de reposición</h4>
            </p>
            <label class="radio-inline">
                <input type="radio" name="optradio">Si
            </label>
            <label class="radio-inline">
                <input type="radio" name="optradio">No
            </label>
            <label class="radio-inline" for="pwd">Decisión de la reposición:</label>
            <div class="radio-inline">
                <select class="form-control" id="sel1">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                </select>
            </div>
            <p>
                <h4>Recurso de apelación</h4>
            </p>
            <label class="radio-inline">
                <input type="radio" name="optradio">Si
            </label>
            <label class="radio-inline">
                <input type="radio" name="optradio">No
            </label>
            <p>
                <h4>Concedió apelación </h4>
            </p>
            <label class="radio-inline">
                <input type="radio" name="optradio">Si
            </label>
            <label class="radio-inline">
                <input type="radio" name="optradio">No
            </label>
            <p>
                <@l.formSubmit label="Guardar" />
            </p>
        </div>
    </div>
    </@l.form>
    </@comp.comparendolayout>