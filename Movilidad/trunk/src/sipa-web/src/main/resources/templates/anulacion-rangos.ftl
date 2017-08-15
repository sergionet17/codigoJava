<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>

    <div class="col-md-9">
        <div class="well sipa-well">
              <h1>Control de Rangos  de Anulación</h1>
              <fieldset>
                <p  class="lead">Registro de nuevo rango de numeración</p>
                <p><em>A continuación se muestra el listado de rangos anteriormente registrados</em></p>
                <#if (anulacionRangos?? && anulacionRangos?size != 0)>
                    <div class="table-responsive">
                      <table class="table table-condensed">
                        <thead>
                          <tr>
                            <th>Fecha</th>
                            <th>Números</th>
                            <th>Tipo rango numeración</th>
                            <th>Subir evidencia</th>
                          </tr>
                        </thead>
                        <#list anulacionRangos as anulacionRango>
                          <tr>
                            <td>${(anulacionRango.fecha)!""}</td>
                            <td>
                              <#list anulacionRango.numeros as numero>
                                <#if (numero_has_next)>
                                  ${(numero.consecutivo)+","!""}
                                <#else>
                                  ${(numero.consecutivo)!""}
                                </#if>
                              </#list>
                            </td>
                            <td>${(anulacionRango.tipoRangoNumeracion.nombre)!""}</td>
                            <td>
                                <a href="<@s.url '/evidenciaAnulacion/subirEvidencia/${anulacionRango.id}'/>">
                                  Adjuntar evidencia
                                </a>
                            </td>
                          </tr>
                        </#list>
                      </table>
                    </div>
                </#if>
              </fieldset>
        </div>
    </div>
</@l.main>
