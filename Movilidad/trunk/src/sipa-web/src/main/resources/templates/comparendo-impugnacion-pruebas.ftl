<#import 'comparendo-layout.ftl' as comp />
<@comp.comparendolayout>

    <h2>Incorporar pruebas del proceso contravencional</h2>
    <@l.form action="/comparendo/${comparendo.id}/audiencia-impugnacion-pruebas" class="form-horizontal" enctype="multipart/form-data">
        <div class="panel panel-default">
            <div class="panel-body">

                 <div class="col-md-6">
                    <div class="form-group sipa-m-3-r">
                        <label for="tipoPrueba">Tipo de identificación</label>
                        <@s.formSingleSelect path="comparendo.tipoPruebaProcesoContravencional.nombre" options=tipoPrueba attributes="class='form-control' id='tipoPrueba'" />
                    </div>
                </div>
                <!-- <div class="col-md-6">
                    <div class="form-group sipa-m-3-l">
                        <label for="usr">¿Otro?</label>
                        <@l.inputText name="representanteLegal.numeroDocumentoIdentidad" class="form-control" size="40" />
                    </div>
                </div> -->
                <input type="hidden" name="id" value="${(comparendo.id)!''}" />
                <div class="col-md-8">
                    <@l.formGroupFile name="documento" label="Selecionar el documento: " placeholder="Seleccione el documento de respaldo ...." accept="application/msword ,
                    application/pdf" />
                </div>
                <p>
                    <@l.formSubmit label="Guardar evidencia" />
                </p>
            </div>
        </div>
    </@l.form>
</@comp.comparendolayout>