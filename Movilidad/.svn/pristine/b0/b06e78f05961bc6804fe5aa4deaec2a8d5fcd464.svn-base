<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@l.main>
<div class="col-md-9">
<div class="well sipa-well">
  <h1>Control de Rangos y Numeración</h1>
  <h3>Registrar nuevo rango</h3>
  <@l.form action="/rangos" class="form-horizontal">
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

    <@l.formGroupSingleSelect "tipoRangoNumeracion"  "${s.status.expression}" "Tipo de rangos" "rangoForm.tipoRangoNumeracion" tiposRangoNumeracion "id ='tipoRangoNumeracion' class='form-control'"/>
    <div class="has-error">
        <span class="help-block"><@s.showErrors "<br>"/></span>
    </div>
    <@l.formSubmit label="Guardar" />
  </@l.form>
  </div>
</div>
</@l.main>
