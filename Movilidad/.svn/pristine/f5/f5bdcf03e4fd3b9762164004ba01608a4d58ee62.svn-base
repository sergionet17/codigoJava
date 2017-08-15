<#import "rangos.ftl" as r/>
<@r.template>
  <@l.form action=controllerStatics.CONTROLLER_PATH_LIST class="form-horizontal" enctype="multipart/form-data">

    <@s.bind "rangoForm.inicio"/>
    <@l.formGroupText "${s.status.expression}" "Desde" ""  "${(rangoForm.inicio)!''}" "numberValidator" "" "10" "0" "2147483647"/>
    <div class="has-error">
        <span class="help-block"><@s.showErrors "<br>"/></span>
    </div>

    <@s.bind "rangoForm.fin"/>
    <@l.formGroupText "${s.status.expression}" "Hasta" ""  "${(rangoForm.fin)!''}" "numberValidator" "" "10" "0" "2147483647"/>
    <div class="has-error">
        <span class="help-block"><@s.showErrors "<br>"/></span>
    </div>
    <@l.formGroupSingleSelect "${s.status.expression}" "Tipo de rangos" "rangoForm.tipoRangoNumeracion" tiposRangoNumeracion "id ='tipoRangoNumeracion' class='form-control'"/>
    <div class="has-error">
        <span class="help-block"><@s.showErrors "<br>"/></span>
    </div>
    <@l.formGroupFile name="file" label="Subir evidencia" placeholder="Seleccione el archivo con la evidencia" />
    <div class="has-error">
        <span class="help-block"><@s.showErrors "<br>"/></span>
    </div>
    <@l.formSubmit label="Guardar" />

  </@l.form>
</@r.template>
