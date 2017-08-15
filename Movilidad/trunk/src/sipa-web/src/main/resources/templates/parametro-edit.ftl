<@l.main>

<div class="col-md-9">
<div class="well sipa-well">
<h1>Parámetros de proceso</h1>
<#if error??>
  <div class="has-error">
      <span  class="help-block">${error}</span>
  </div>
<#else>
  <h3>Editar valor del parámetro ${valorParametroForm.parametro.nombre}</h3>
  <br>
  <@l.form action="/parametro" class="form-vertical" enctype="multipart/form-data">
    <@s.bind "valorParametroForm.parametro.clave"/>
    <input type="hidden" class="form-control" name="${s.status.expression}" value="${valorParametroForm.parametro.clave}" id="clave"/>
    <@s.bind "valorParametroForm.inicioVigencia"/>
    <input type="text" class="form-control datetimepickerVigencia" name="${s.status.expression}" value="" id="inicioVigencia"/>
    <div class="has-error">
      <span class="help-block"><@s.showErrors "<br>"/></span>
    </div>
    <@s.bind "valorParametroForm.fechaCreacion"/>
    <input type="hidden" class="form-control" name="${s.status.expression}" value="${valorParametroForm.fechaCreacion?string[constants.DATE_TIME_FORMAT_PATTERN]}" id="fechaCreacion"/>
    <@s.bind "valorParametroForm.valor"/>
    <#if editor??>
      <#if !(editor.getTemplate() = "") >
        <#include "${editor.getTemplate()}">
      </#if>
      <#assign templateSource = "${editor.getWidget((valorParametroForm.valor)!'')}">
      <#assign inlineTemplate = templateSource?interpret>
      <@inlineTemplate />
    <#else>
      El tipo de dato (<strong>${valorParametroForm.parametro.tipo}</strong>) del parámetro no es soportado por el editor.
    </#if>
    <div class="has-error">
      <span class="help-block"><@s.showErrors "<br>"/></span>
    </div>
    <@l.formSubmit label="Guardar" />
  </@l.form>
  <@l.script>
    <script type="text/javascript">
      <!-- 
      -->
    </script>
  </@l.script>
</#if>
</div>
</div>
</@l.main>