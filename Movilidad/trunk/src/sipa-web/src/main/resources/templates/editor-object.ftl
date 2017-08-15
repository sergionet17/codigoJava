<#--

Macro para el editor de objectos

@param value
  Valor del campo

@param list
  Lista con datos

-->
<#macro editorObject value="valorParametroForm.valor" list="">
  <@l.formGroupSingleSelect name="valor" label="Valor" path=value options=list attributes="class='chosen-select'"/>
</#macro>