<@l.main>
  <div class="well sipa-well">
    <h1>Tablas de referencia</h1>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    <#else>
      <@l.form action="/tablaReferencia" class="form-vertical" enctype="multipart/form-data">
        
        <select name="tablasReferencia" id="tablasReferencia" class='chosen-select'>
          <#list tablasReferencia?keys as value>
            <#if value = tablaReferencia>
              <option value="${value}" selected>${tablasReferencia[value]}</option>
            <#else>
              <option value="${value}">${tablasReferencia[value]}</option>
            </#if>
          </#list>
        </select>
        <#if errorTablaReferencia??>
          <div class="has-error">
              <span  class="help-block">${errorTablaReferencia}</span>
          </div>
        </#if>
        <div id="divCargaData" class="sipa-display">
          <h3 id="lblTablaReferencia"></h3>
          <br>
          <div class="form-group">
            <a class="btn btn-primary" id="enlaceDescarga" href="" target="_blank">Descargar valores</a>
          </div>
          <#--<#if controller.hasRole("MODIFICAR_TABLA_REFERENCIA")>-->
            <div class="form-group">
              <label for="inicioVigencia"><span style="color: red">*</span> Inicio de vigencia</label>
              <input type="text" class="form-control datetimepickerVigencia" name="inicioVigencia" value="${(inicioVigencia)!''}" id="inicioVigencia"/>
              <#if errorInicioVigencia??>
                <div class="has-error">
                    <span  class="help-block">${errorInicioVigencia}</span>
                </div>
              </#if>
            </div>
            <div class="form-group">
              <label for="documento"><span style="color: red">*</span> Subir archivo</label>
              <input type="file" class="form-control" name="documento" value="" id="documento" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />
              <#if errorDocumento??>
                <div class="has-error">
                    <span  class="help-block">${errorDocumento}</span>
                </div>
              </#if>
            </div>
            <@l.formSubmit label="Actualizar" />
          <#--</#if>-->
        </div>
      </@l.form>
      <@l.script>
        <script type="text/javascript">
          <!-- 
            cargarValoresTablaReferencia();

            $("#tablasReferencia").change(function() {
              cargarValoresTablaReferencia();
            });

            function cargarValoresTablaReferencia() {
              var tabla = $("#tablasReferencia").val();
              if(tabla!=''){
                var url = "<@s.url controllerStatics.CONTROLLER_PATH_EXPORT />/" + tabla;
                $("#enlaceDescarga").attr("href", url)
                $("#lblTablaReferencia").text($("#tablasReferencia option:selected").text());
                $("#divCargaData").show();
              }else{
                $("#enlaceDescarga").attr("href", "")
                $("#lblTablaReferencia").text("");
                $("#divCargaData").hide();
              }
            }
          -->
        </script>
      </@l.script>
    </#if>
  </div>
</@l.main>
