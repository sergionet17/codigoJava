<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div>&nbsp;</div>
<div class="well sipa-well">
  <div class="container">
    <div class="col-md-11">
        <#if inconsistencias??>
            <h1>Comparendos con Inconsistencias</h1>
            <#if error??>
              <div class="has-error">
                  <span  class="help-block">${error}</span>
              </div>
            </#if>
            <div class="panel panel-default">
                <div class="panel-body">
                </div>
                <div class="table-responsive">
                  <div class="input-group">
                        <span class="input-group-addon" id="sizing-addon2">Buscar:  </span>
                        <input id="inputBuscar" type="text" class="form-control" placeholder="Palabra clave ..." aria-describedby="sizing-addon2">
                  </div>
                  <div class="table-responsive">
                  <table class="table table-bordered" id="tableIncosistencias">
                      <thead>
                      <tr>
                          <th>Número Comparendo</th>
                          <th>Fecha Imposición</th>
                          <th>Número identificación infractor</th>
                          <th>Código infracción</th>
                      </tr>
                      </thead>
                      <tbody>
                          <#list inconsistencias as inconsistencia>
                              <tr>
                                  <td>${(inconsistencia.numero)!""}</td>
                                  <td>${(inconsistencia.fecha)!""}</td>
                                  <td>${(inconsistencia.numeroIdentificacionInfractor)!""}</td>
                                  <td>${(inconsistencia.codigoInfraccion)!""}</td>
                                  <td>
                                      <a href="<@s.url '/inconsistencias/inconsistencias-modificar/${inconsistencia.id}'/>">
                                        Modificar
                                      </a>
                                  </td>
                              </tr>
                          </#list>
                      </tbody>
                  </table>
                  </div>
                </div>
            </div>
                <a class="btn btn-primary" href="<@s.url '/inconsistencias/inconsistencias-descarga/'/>">Descargar Inconsistencias</a>

        <#else>
            <P>No existen comparendos con inconsistencias</P>
        </#if>

    </div>
  </div>
</div>



<@l.script>
      <script type="text/javascript">
        var $rows = $('#tableIncosistencias tr');
        $('#inputBuscar').keyup(function() {

        var val = '^(?=.*\\b' + $.trim($(this).val()).split(/\s+/).join('\\b)(?=.*\\b') + ').*$',
        reg = RegExp(val, 'i'),
        text;

        $rows.show().filter(function() {
            text = $(this).text().replace(/\s+/g, ' ');
            return !reg.test(text);
            }).hide();
        });
      </script>
    </@l.script>

</@l.main>
