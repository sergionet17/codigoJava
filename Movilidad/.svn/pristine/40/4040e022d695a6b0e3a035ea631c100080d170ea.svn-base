<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
    <div class="well sipa-well">
        <div class="table-responsive">
          <table class="table table-hover table-bordered table-condensed">
            <tbody>
                <tr class="sipa-tr">
                  <th>Fecha de anulación:</th>
                  <td>${anulacionNumeracion.fecha}</td>
                </tr>
                <tr class="sipa-tr">
                  <th>numeros:</th>
                  <td>
                    <#list anulacionNumeracion.numeros as numero>
                      <#if (numero_has_next)>
                        ${(numero.consecutivo)+","!""}
                      <#else>
                        ${(numero.consecutivo)!""}
                      </#if>
                    </#list>
                  </td>
                </tr>
                <tr class="sipa-tr">
                  <th>Tipo rango:</th>
                  <td>${anulacionNumeracion.tipoRangoNumeracion.nombre}</td>
                </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="col-md-6">
        <@l.form action="/evidenciaAnulacion/guardarEvidencia" class="form-horizontal" enctype="multipart/form-data">
          <@s.bind "anulacionNumeracion.resolucion"/>
          <@l.formGroupFile name="documento" label="Selecionar imagen: "
                placeholder="Seleccione la imagen de la envidencia ...." />
          <div class="has-error">
              <span class="help-block"><@s.showErrors "<br>"/></span>
          </div>
          <@s.bind "anulacionNumeracion.id"/>
          <input type="hidden" name="${s.status.expression}"  value="${anulacionNumeracion.id!''}" id="id"/>
          <@l.formSubmit label="Guardar evidencia"/>
        </@l.form>
      </div>

</@l.main>
