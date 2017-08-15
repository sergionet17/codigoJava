<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<@l.main>
<div class="well sipa-well">
  <div class="container">
    <div class="col-md-9">
    <h1>Cursos Pedagógicos</h1>
    <h3>Crear registro de nuevo curso pedagógico</h3>
    <br>
    <#if error??>
      <div class="has-error">
          <span  class="help-block">${error}</span>
      </div>
    </#if>
    <div>
    <form  action="<@s.url '/cursos'/>"  method="post" >
     <fieldset>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <label for="tema"><span class="sipa-red">*</span> Tema del curso</label>
              <@s.bind "cursoForm.tema"/>
             <@l.inputText  "${s.status.expression}" "100" "" "textValidator" "${(cursoForm.tema)!''}"/>
              <div class="has-error">
                      <span class="help-block"><@s.showErrors "<br>"/></span>
              </div>
            <div class="form-group">
                <label for="fecha"><span class="sipa-red">*</span> Fecha</label>
                <@s.bind "cursoForm.fecha"/>
                <input type="text"  class="form-control datepickerVigencia" name="${s.status.expression}" value="${(cursoForm.fecha?string[constants.DATE_FORMAT_PATTERN])!''}" id="fecha"/>
                <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
            </div>
             <div class="form-group">
                <label for="fecha"><span class="sipa-red">*</span> Hora Inicial</label>
                <@s.bind "cursoForm.horaInicial"/>
                <input type="text"  class="form-control timepicker" name="${s.status.expression}" value="${(cursoForm.horaInicial)!''}" id="horaInicial"/>
                <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
            </div>
             <div class="form-group">
                <label for="fecha"><span class="sipa-red">*</span> Hora Final</label>
                <@s.bind "cursoForm.horaFinal"/>
                <input type="text"  class="form-control timepicker" name="${s.status.expression}" value="${(cursoForm.horaFinal)!''}" id="horaFinal"/>
                <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
            </div>
            <label for="maximoAsistentes"><span class="sipa-red">*</span> Número de participantes</label>
            <@s.bind "cursoForm.maximoAsistentes"/>
            <@l.inputText  "${s.status.expression}" "2" "" "numberValidator" "${(cursoForm.maximoAsistentes)!''}" "0" "100"/>
             <div class="has-error">
                <span class="help-block"><@s.showErrors "<br>"/></span>
            </div>

             <div class="form-group">
                <label for="instructor"><span class="sipa-red">*</span> Instructor</label>
                <@s.bind "cursoForm.instructor.id"/> 
                <@s.formSingleSelect "cursoForm.instructor", instructores, " id ='instructor' class='form-control'"/> 
                <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
            </div>
            <#if (sedes?? && sedes?size != 0)>
            <div class="form-group">
                <label for="sede"><span class="sipa-red">*</span> Sedes</label>
                <@s.bind "cursoForm.salon.sede.id"/>         
               <@s.formSingleSelect "cursoForm.salon.sede.id", sedes, " id ='sede' class='form-control sede'"/> 
               <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
               </div>
                <@l.script>
                  <script type="text/javascript">
                    <!-- 
                    $(document).ready(function() {
                        $(".sede").change(function() {
                          var sede = $(".sede").val();
                          if(sede!='0'){
                            var url = "<@s.url controllerStatics.CONTROLLER_PATH + controllerStatics.CONTROLLER_PATH_SEDES />/" + sede;
                              $.get(url, function(sedeData) {
                                  var salonSelect = $("#salon");
                                  salonSelect.find('option').remove();
                                  $.each(sedeData.salones, function(i, salon) {
                                      $('<option />')
                                          .val(salon.id)
                                          .text(salon.nombre)
                                          .appendTo(salonSelect);
                                  }); 
                              });
                            }else{
                              $("#salon").html("");
                            }
                        });
                    });
                    -->
                  </script>
                </@l.script>
            </div>
            </#if>

            <div class="form-group">
                  <label for="salon"><span class="sipa-red">*</span> Salon</label>
                   <@s.bind "cursoForm.salon.id"/>
                   <select name="${s.status.expression}" class="form-control" value="${(cursoForm.salon)!''}" id="salon">
                    <#if salones??>
                       <#list salones.salones as salon>
                          <#if cursoForm.salon.id == salon.id>
                            <option value="${salon.id}"[@s.checkSelected value/] selected>${salon.nombre}</option>
                          <#else>
                            <option value="${salon.id}"[@s.checkSelected value/]>${salon.nombre}</option>
                          </#if>
                      </#list>
                    </#if>
                    
                  </select>
                 <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                </div>
              </div>
              <div class="sipa-bottom-10"><@s.message 'obligatorio.campos'/>
          </div>
            <button class="btn btn-primary">Guardar</button>
        </fieldset>
     </form>
    </div>
    </div>
  </div>
</div>
</@l.main>