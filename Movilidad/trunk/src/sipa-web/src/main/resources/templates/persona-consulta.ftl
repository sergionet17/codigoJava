<@l.main>
<div class="col-md-9">
 <#if personas??>
    <div class="panel panel-info">
    <!-- Default panel contents -->
        <div class="panel-heading">Consulta de Personas</div>
            <div class="panel-body">
            </div>

        <!-- Table -->
        <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Documento</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Correo electrónico</th>
                </tr>
            </thead>
            <tbody>
                <#list personas as persona>
                <tr>
                    <td><a href="<@s.url '/persona/comparendos-asociados/${persona.id}'  />"">${persona.numeroDocumentoIdentidad}</a></td>
                    <td>${persona.primerNombre!""}&nbsp${persona.segundoNombre!""}</td>
                    <td>${persona.primerApellido!""}&nbsp${persona.segundoApellido!""}</td>
                    <td>
                        <#list persona.emails as correo>
                           ${correo.email!""} 
                           <br/>
                        </#list>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
        </div>
    </div>
<#else>

    <div class="jumbotron">
        
        <#if sinresultados??>
            <h1>Buscar Personas</h1>
            <p>Sipa no encontró coincidencias de acuerdo a los criterios de búsqueda ingresados. Use los criterios de busqueda e intente nuevamente.</p>
        <#else>
            <h1>Buscar Personas</h1>
            <p class="lead">Use los criterios para realizar la búsqueda</p>
        </#if>
    </div>
</#if>
</div>
<div class="col-md-3">
    <div class="well sipa-well-search">
        <div class="form-group">
            <div>
                <#if error??>
                    <div class="has-error">
                        <span  class="help-block">${error}</span>
                    </div>
                </#if>
                <h3>Criterios de búsqueda</h3>
                <p>Use los criterios de búsqueda para encontrar las personas que está buscando</p>
                <form action="<@s.url '/persona/consulta/buscar'/>" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                <div class="form-group">
                    <label for="numero">Nombre o apellido</label>
                    <@s.bind "personaForm.nombre"/>
                    <input type="text" name="${s.status.expression}" value="${(personaForm.nombre)!""}" class="form-control" id="numero">
                    <div class="has-error">
                        <span class="help-block"><@s.showErrors "<br>"/></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="tipo-documento">Tipo documento identidad</label>
                    <@s.bind "personaForm.tipoDocumento"/>
                    <select class="form-control" id="${s.status.expression}" name="${s.status.expression}" >
                        <#if tipoDocumento??>
                            <option value="none">Seleccione una opción</option>
                            <#list tipoDocumento as td>
                                <#if td.sigla?string == ((personaForm.tipoDocumento)!"none")?string >
                                    <option value="${td.sigla}" selected="selected">${td.nombre}</option>
                                <#else>
                                    <option value="${td.sigla}">${td.nombre}</option>
                                </#if>
                            </#list>
                        </#if>
                    </select>
                    <div class="has-error">
                        <span class="help-block"><@s.showErrors "<br>"/></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="documentoIdentidad">Documento de identidad</label>
                    <@s.bind "personaForm.documentoIdentidad"/>
                    <input type="text" class="form-control" id="documentoIdentidad" name="${s.status.expression}" value="${(personaForm.documentoIdentidad)!""}">
                    <div class="has-error">
                        <span class="help-block"><@s.showErrors "<br>"/></span>
                    </div>
                </div>
                <@s.bind "personaForm.message"/>
                <div class="has-error">
                    <span  class="help-block"><@s.showErrors "<br>"/></span>
                </div>
                <div>&nbsp;</div>
                <button class="btn btn-primary">Buscar</button>
            </form>
        </div>
    </div>
 </@l.main>