<#import "titulos-ejecutivos.ftl" as t/>
<@t.template>
 <h4>Registro de titulo ejecutivo</h4>

  <@l.form class="form-horizontal" action=controllerStatics.CONTROLLER_PATH_SAVE_TITULO>
      <p><small><strong>Registra el título ejecutivo con los documentos que lo respaldan.</strong></small></p>
      <div class="table-responsive">
        <table class="table table-condensed" id="tablaDocumentos">
          <thead>
            <tr>
              <th>Fecha</th>
              <th>Asunto</th>
              <th>Radicado</th>
            </tr>
          </thead>
          <tbody>
              <#list documentos as documento>
                    <tr>
                      <td>[${(documento.fechaCreacion)!""}, ${(documento.fechaCreacion)!""}]</td>
                      <td>[${(documento.asunto)!""}, ${(documento.asunto)!""}]</td>
                      <td>[${(documento.referencia)!""}, ${(documento.referencia)!""}]</td>
                    </tr>
              </#list>
         </tbody>
        </table>
      </div>
      <div class="row">
        <div class="col-md-4">
          <p>
            <a id="adicionarDoc" href="#" class="btn btn-md btn-primary hidden">¡Adicionar documento!</a>
          </p>
        </div>
        <div class="col-md-8">
          <@l.formGroupFile name="documento" label="Selecionar el documento: "
                placeholder="Seleccione el documento de respaldo ...." accept="application/msword ,
                application/pdf" />
        </div>
      </div>

      <@s.bind "tituloEjecutivo.dependencia.id"/>
      <@l.selectorDependencias dependencias "${s.status.expression}" "${(tituloEjecutivo.dependencia.id)!''}" "${(tituloEjecutivo.dependencia.nombre)!''}"/>
      <div class="has-error">
        <span class="help-block"><@s.showErrors "<br>"/></span>
      </div>

      <div class="row">
        <div class="col-md-6">
          <@s.bind "tituloEjecutivo.fechaTitulo"/>
          <@l.formGroupDate  name="${s.status.expression}"  label="fecha Título" value="${(tituloEjecutivo.fechaTitulo)!''}" class="form-control" />
          <div class="has-error">
            <span class="help-block"><@s.showErrors "<br>"/></span>
          </div>
        </div>

        <div class="col-md-6">
          <@s.bind "tituloEjecutivo.fechaEjecutoria"/>
          <@l.formGroupDate  name="${s.status.expression}"  label="fecha Ejecutoria" value="${(tituloEjecutivo.fechaEjecutoria)!''}" class="form-control"/>
          <div class="has-error">
            <span class="help-block"><@s.showErrors "<br>"/></span>
          </div>
        </div>
      </div>




      <@l.formGroupSingleSelect "${s.status.expression}" "Tipo de fuente" "tituloEjecutivo.fuenteTituloEjecutivo.identifier" tiposFuente "id ='fuenteTituloEjecutivo' class='form-control'"/>
      <div class="has-error">
          <span class="help-block"><@s.showErrors "<br>"/></span>
      </div>



      <!-- <@l.formGroupSingleSelect  name="deudorSelectId}" label="Datos del tercer deudor solidario"
          path="personaModal.id" options=personas attributes="class='chosen-select' id='deudorSelectId'"/>
      <div class="has-error">
        <span class="help-block"><@s.showErrors "<br>"/></span>
      </div> -->


      <!-- <div class="row">
          <div class="col-md-2">
            <button type="button" class="btn btn-info btn-md" data-toggle="modal"
              data-target="#crearUsuarioModal">!Nuevo tercero!</button>
          </div>
          <div class="col-md-2">
            <button type="button" class="btn btn-secondary" id="adicionarDedudor">!Adicionar deudor!</button>
          </div>
      </div> -->

      <div class="table-responsive">
        <table class="table table-condensed" id="tablaDeudores">
          <thead>
            <tr>
              <th class="col-md-6">Deudor</th>
              <th class="col-md-6">Procentaje de participación</th>
            </tr>
          </thead>
          <tbody>
              <#list deudores as deudor>

              </#list>
         </tbody>
        </table>

      </div>

      <div class="input-group">
           <button class="btn btn-primary" type="button" id="openModalForm"
                  data-toggle="modal"  data-target="#crearUsuarioModal">¡Adicionar Deudor!</button>
       </div>

       <br>
       <br>
      <@s.bind "tituloEjecutivo.valor"/>
      <@l.formGroupText "${s.status.expression}" "Valor título " ""  "${(tituloEjecutivo.valor)!''}" "decimalValidator" "" "10" "0" "2147483647"/>
      <div class="has-error">
          <span class="help-block"><@s.showErrors "<br>"/></span>
      </div>

        <input type="hidden" id="idsDeudores" name="idsDeudores"/>
        <input type="hidden" id="idsDocumentos" name="idsDocumentos"/>

    <@l.formSubmit label="Guardar" />
  </@l.form>

  <div id="crearUsuarioModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Adicionar nuevo deudor tercero</h4>
        </div>
        <div class="modal-body">
          <@l.form  class="form-horizontal" id="crearUsuarioForm">
              <div class="col-md-6">
                <div class="form-group sipa-m-3-r">
                  <label for="tipoDocumento">Tipo de identificación</label>
                  <@s.formSingleSelect path="personaModal.tipoDocumentoIdentidad.id"
                      options=tiposIdentificacion attributes="class='form-control' id='tipoDocumentoIdentidad'" />
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group sipa-m-3-l">
                  <label for="numeroIdentificacion">Número de identificación</label>
                  <@s.bind "personaModal.numeroDocumentoIdentidad"/>
                  <@l.inputText name="${s.status.expression}" class="form-control" size="40"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group sipa-m-3-r">
                  <label for="primerNombre">Primer nombre</label>
                  <@s.bind "personaModal.primerNombre"/>
                  <@l.inputText name="${s.status.expression}" class="form-control" size="40"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group sipa-m-3-l">
                  <label for="segundoNombre">Segundo nombre</label>
                  <@s.bind "personaModal.segundoNombre"/>
                  <@l.inputText name="${s.status.expression}" class="form-control" size="40"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group sipa-m-3-r">
                  <label for="primerApellido">Primer apellido</label>
                  <@s.bind "personaModal.primerApellido"/>
                  <@l.inputText name="${s.status.expression}" class="form-control" size="40"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group sipa-m-3-l">
                  <label for="segundoApellido">Segundo apellido</label>
                  <@s.bind "personaModal.segundoApellido"/>
                  <@l.inputText name="${s.status.expression}" class="form-control" size="40"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group sipa-m-3-r">
                  <label for="telefono">Teléfono</label>
                  <@l.inputText name="telefonoPersona" class="form-control" size="40"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group sipa-m-3-l">
                  <label for="correo">Correo electrónico</label>
                  <@l.inputText name="correoPersona" class="form-control" size="40"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group sipa-m-3-r">
                  <label for="direccion">Dirección</label>
                  <@l.inputText name="direccionPersona" class="form-control" size="40"/>
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group sipa-m-3-l">
                  <label for="porcentaje">Porcentaje</label>
                  <@l.inputText name="porcentajePersona" class="form-control" validator="numberValidator" max="101" />
                  <div class="has-error">
                    <span class="help-block"><@s.showErrors "<br>"/></span>
                  </div>
                </div>
              </div>

              <div class="form-group">
                <div class="col-sm-12">
                  <button type="submit" class="btn btn-success btn-md" >Crear tercero</button>
                  <a href="#closeModal" class="btn btn-danger btn-md">Cancelar</a>
                </div>
              </div>
          </@l.form>
        </div>
      </div>
    </div>
  </div>


  <@l.script>
       <script type="text/javascript">
        $("#documento").change(function(e){
            $("#adicionarDoc").removeClass("hidden");
        });

         $("#adicionarDoc").click(function(e){
             var data = new FormData();
             var selectedFile = document.getElementById('documento').files[0];
             data.append("documento", selectedFile);
             data.append("${_csrf.parameterName}", "${_csrf.token}");
             $(this).addClass("hidden");
             $(this).after("<span class=\"btn\">Espere...</span>");
             $.ajax({
                      url : "http://localhost:8081/sipa/titulosEjecutivos/saveDocument",
                      type : 'POST',
                      cache: false,
                      contentType: false,
                      processData: false,
                      data : data ,
                      success : function (result) {
                        var  date = new Date(result.fechaCreacion);
                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', date);
                        var fecha = $("<td></td>").text(dateFormat);
                        var asunto = $("<td></td>").text(result.originalName);
                        var referencia = $("<td></td>").text(result.externalId);
                        var fila = $("<tr></tr>").append(fecha, asunto, referencia);
                        var idsDocs= document.getElementById('idsDocumentos').value;
                        idsDocs = idsDocs+","+result.id;
                        $("#idsDocumentos").val(idsDocs);
                        $("#tablaDocumentos > tbody").append(fila);
                        $("#adicionarDoc").removeClass("hidden");
                        $("#adicionarDoc").next().addClass("hidden");
                      },
                      error : function (result) {
                      }
            });
            e.preventDefault();
          });



          $('a[href="#closeModal"]').click(function(){
             $('#crearUsuarioModal').modal('toggle');
          });


          $( "#crearUsuarioForm").submit(function( event ) {
              var postData = $(this).serializeArray();
              $('#crearUsuarioModal').modal('toggle');
              $("#adicionarDoc").addClass("hidden");
              $("#adicionarDoc").after("<span class=\"btn\">Espere...</span>");
              event.preventDefault();

              $.ajax(
              {
                url : "http://localhost:8081/sipa/titulosEjecutivos/savePersona",
                type: "POST",
                data : postData,
                success:function(result)
                {
                    //data: return data from server
                      var tipoDocSigla =!result.persona.tipoDocumentoIdentidad.sigla ?"":result.persona.tipoDocumentoIdentidad.sigla+" ";
                      var numeroDoc =!result.persona.numeroDocumentoIdentidad ?"":result.persona.numeroDocumentoIdentidad+" - ";
                      var nombres= (!result.persona.primerNombre ?"":result.persona.primerNombre)
                                   +" "+(!result.persona.segundoNombre ?"":result.persona.segundoNombre);
                      var apellidos= (!result.persona.primerApellido ?"":result.persona.primerApellido+" ")
                                    +(!result.persona.segundoApellido ?"":result.persona.segundoApellido);
                      var deudor = $("<td></td>")
                                    .text(tipoDocSigla+numeroDoc+nombres+apellidos);
                      var porcentaje=$("<td></td>").text(!result.porcentajeParticipacion ?"":result.porcentajeParticipacion);
                      var fila = $("<tr></tr>").append(deudor, porcentaje);
                      var idsDeudores= document.getElementById('idsDeudores').value;
                      idsDeudores = idsDeudores+","+result.id;
                      $("#idsDeudores").val(idsDeudores);
                      $("#tablaDeudores > tbody").append(fila);
                      document.getElementById("crearUsuarioForm").reset();
                      $("#adicionarDoc").removeClass("hidden");
                      $("#adicionarDoc").next().addClass("hidden");
                },
                error: function(result)
                {
                    //if fails
                }
              })

          });


           $("#numeroDocumentoIdentidad").blur(function(e){
              var idTipoId = $( "#tipoDocumentoIdentidad\\.id" ).val();
              var numeroId = $( "#numeroDocumentoIdentidad" ).val();

              if(!$.isEmptyObject(numeroId)){

                  $.ajax(
                  {
                    url : "http://localhost:8081/sipa/titulosEjecutivos/getPersona/"+idTipoId+"/"+numeroId,
                    success:function(result)
                    {
                        //data: return data from server
                        $("#primerNombre").val(result.primerNombre);
                        $("#segundoNombre").val(result.segundoNombre);
                        $("#primerApellido").val(result.primerApellido);
                        $("#segundoApellido").val(result.segundoApellido);
                        if(result.direcciones.length > 0){
                           $("#direccionPersona").val(direcciones[0].direccion);
                        }
                        if(result.direcciones.length > 0){
                           $("#direccionPersona").val(direcciones[0].direccion);
                        }
                        if(result.emails.length > 0){
                           $("#correoPersona").val(emails[0].email);
                        }
                        if(result.telefonos.length > 0){
                           $("#telefonoPersona").val(telefonos[0].numero);
                        }
                      //  $("#telefonoPersona").val(result.telefonoPersona);
                      //  $("#correoPersona").val(result.correoPersona);
                      //  $("#direccionPersona").val(result.direccionPersona);






                          /*

                            <@s.bind "personaModal.primerNombre"/>

                            <@s.bind "personaModal.segundoNombre"/>

                            <@s.bind "personaModal.primerApellido"/>

                            <@s.bind "personaModal.segundoApellido"/>

                            <@l.inputText name="telefonoPersona" class="form-control" size="40"/>

                            <@l.inputText name="correoPersona" class="form-control" size="40"/>

                            <@l.inputText name="direccionPersona" class="form-control" size="40"/>





                         var tipoDocSigla =result.tipoDocumentoIdentidad.sigla+" ";
                         var numeroDoc =result.numeroDocumentoIdentidad+" - ";
                         var nombres= result.primerNombre+" "+result.segundoNombre;
                         var apellidos=result.primerApellido+" "+result.segundoApellido;
                         var deudor = $("<td></td>")
                                      .text(tipoDocSigla+numeroDoc+nombres+apellidos);
                         var porcentaje=$("<td></td>").text(result.porcentajeParticipacion);
                         var fila = $("<tr></tr>").append(deudor, porcentaje);
                         var idsDeudores= document.getElementById('idsDedudores').value;
                         idsDeudores = idsDeudores+","+result.id;
                         $("#idsDedudores").text(idsDeudores);
                         $("#tablaDeudores > tbody").append(fila);*/
                    },
                    error: function(result)
                    {

                        //if fails
                    }
                  })
                }
          });

      </script>
   </@l.script>
</@t.template>
