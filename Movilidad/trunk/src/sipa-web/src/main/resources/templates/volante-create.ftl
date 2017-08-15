<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>

<div class="col-md-12">
<div class="well sipa-well">
  <h1>Cursos Pedagógicos</h1>
  <h3>Generar volante de pago</h3>
  <div>
  <#if error??>
    <div class="has-error">
        <span  class="help-block">${error}</span>
    </div>
  </#if>
   <fieldset>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <#if (comparendo??)>
      <div class="panel panel-info">
      <div class="panel-heading">Consulta del comparendo</div>   
        <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
              <tr>
                 <th>Tipo</th>
                  <th>Estado</th>
                  <th>Número</th>
                  <th>Placa</th>
                  <th>Fecha</th>
                  <th>Saldo</th>
                  <th>Interes</th>
                  <th>Total saldo + Interes</th>
              </tr>
            </thead>
            <tr>
              <td>${(comparendo.tipoComparendo.nombre)!""}</td>
              <td>${(comparendo.estado.nombre)!""}</td>
              <td>${(comparendo.numero.numeroComparendoId.numeroComparendoId.numero)!""}</td>
              <td>${(comparendo.vehiculo.placaVehiculo)!""}</td>
              <td>${(comparendo.fechaImposicion?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''}</td>
              <td>$ ${(comparendo.valorNominal)!"0.00"}</td>
              <td>$0.00</td>
              <td>$ ${(comparendo.valorNominal)!"0.00"}</td>
          </tr>
        </table>
        </div>
      </div>   
      <#else>
            <P>No hay información del comparendo</P>
      </#if>
    </fieldset>
  </div>
  <div>
    <p>A continuación se muestra las opciones de pago para el comparendo. Use el boton "Imprimir volante de pago" para generar el PDF</p>
    <fieldset>
    <div class="panel panel-info col-md-10">
      <div class="table-responsive">
        <table class="table table-condensed">
          <#if ( valorDescuento != 0) >
          <tr>
              <td>
                 <#if (valorDescuento != 0)>
                    Descuento de ${(porcentajeDescuento)!""} %
                 </#if>
              </td>
              <td>Valor a Cancelar <br> $ ${(valorDescuento)!""} </td>
              <td>Plazo hasta <br> ${(fechaDescuento?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''} </td>
              <#if (valorDescuento != 0)>
              <td> <a class="btn btn-primary" id="enviarAbono" onclick="generarVolante(false,'${valorDescuento}')">Imprimir Volante</a></td>
              <#else>
              <td> <a class="btn btn-primary" id="enviarAbono" onclick="generarVolante(false,'${valorDescuento}')">Imprimir Volante</a></td>
                
              </#if>
          </tr>
          </#if>
          <tr>
               <#if (interes == 0) >
                <td> Pago total sin intereses </td>
               <#else>
                 <td> Pago total con intereses  </td>
               </#if>
            <td>Valor a Cancelar <br> $ ${(valor)!""} </td>
            <td>Plazo hasta <br> ${(fechaPago?string[constants.DATE_FORMAT_PATTERN_DDMMYY])!''} </td>
              <#if (valorDescuento != 0)>
               <td> <a class="btn btn-primary" id="enviarAbono" onclick="generarVolante(false,'${valor}')">Imprimir Volante</a></td>
              <#else>
               <td> <a class="btn btn-primary" id="enviarAbono" onclick="generarVolante(false,'${valor}')">Imprimir Volante</a></td>
              </#if>
          </tr>
          <tr>
            <td>Abono</td>
            <td>Ingrese valor  </td>
            <td>
              <@l.inputText  "abono" "" "" "decimalValidator" "" "0" "${comparendo.valorNominal?replace('.','')}"/>
               <div class="alert alert-danger sipa-display" id="mensajeError">
                      <@s.message "volante.abono.required"/>
                  </div>
                  <div class="alert alert-danger sipa-display" id="mensajeZero">
                      <@s.message "volante.abono.size"/>
                  </div>
            </td>
            <td> <a class="btn btn-primary" id="enviarAbono"  onclick="generarVolante(true)">Imprimir Volante</a></td>
          </tr>
        </table>
      </div>
    </fieldset>
  </div> 
<#if (comparendo??)>
  <a class="btn btn-primary" href="<@s.url controllerStatics.CONTROLLER_PATH+controllerStatics.CONTROLLER_CANCELAR+'/${comparendo.numero.numeroComparendoId.numeroComparendoId.numero}'/>">Cancelar</a>
</#if>
  </div>
</div>

<@l.script>
      <script type="text/javascript">
        <!-- 
      function generarVolante(evento,valor){
        if(evento){
           var abono = $('#abono').val();
           var abonoInt = parseInt($('#abono').val());
           alert('valor'+abonoInt);
              if(abono==''){
                alert('valor vacio');
                $('#mensajeError').show();
                  abono=0;
              }else if(abono=='0'){
                alert('valor cero');
                $('#mensajeZero').show();
                  abono=0;
              }else if(!isNaN(abonoInt)){
                  if(abonoInt<=0){
                  $('#mensajeZero').show();
                  abono=0;
                  }
              }else{
                  $('#mensajeError').hide();

                window.open("<@s.url '/volante-create/generate/"+abono+"/0/${comparendo.id}' />", '_blank');
                window.location.href = "<@s.url '/comparendo/general/${comparendo.numero.numeroComparendoId.numeroComparendoId.numero}' />";
              }
          }else{
                window.open("<@s.url '/volante-create/generate/0/"+valor+"/${comparendo.id}' />", '_blank');
                window.location.href = "<@s.url '/comparendo/general/${comparendo.numero.numeroComparendoId.numeroComparendoId.numero}' />";
          }
        }


      </script>
    </@l.script>

</@l.main>