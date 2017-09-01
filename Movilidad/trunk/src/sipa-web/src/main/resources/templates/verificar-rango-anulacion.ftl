<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div class="well sipa-well">
  <div class="container">
    <div class="col-md-9">
      <h1> Anulación de Rango</h1>
      <h3>Anular rango</h3>
      <form  action="<@s.url '/rangosAnulacion/verficarRangoAnulacion'/>" method="post">
        <fieldset>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <p><em>A contiuanción ingrese los extremos del rango  a anular de la siguiente forma : [RI-RF] ejemplo [1-20] o tambien 1-20
                 o separados por comas de la siguiente forma [RI,..,RN] ejemplo [1,2,3,4,5] o tambien 1,2,3,4,5
                 los numeros de rangos se anularan inlcusive los extremos. </em></p>
          <div class="form-group">
            <label for="rangoTexto">Rango :</label>
            <@s.formTextarea "anulacionNumeracionForm.rangoTexto" "id='rangoTexto' rows='3' value='anulacionNumeracionForm.rangoTexto' class='form-control' placeholder='Escriba el rango a anular'"/>
            <div class="has-error">
                <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>
          </div>
          <div class="form-group">
             <label for="tipoRangoNumeracion">Tipo de rangos</label>
             <@s.formSingleSelect "anulacionNumeracionForm.tipoRangoNumeracion", tiposRangoNumeracion, " id ='tipoRangoNumeracion' class='form-control'"/>
         </div>
            <button class="btn btn-primary">Verificar Rango</button>
        </fieldset>
      </form>
    </div>
  </div>
</div>
</@l.main>
