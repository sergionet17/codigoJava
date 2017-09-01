<#import 'comparendo-layout.ftl' as comp />
<@comp.comparendolayout>
    <!-- Table -->
    <div class="table-responsive">
        <h2 class="has-id">
      <small class="hidden is-id"></small>
   </h2>

        <h2>Apertura audiencia de impugnación</h2>

        <div class="panel panel-default">
            <div class="panel-body">

                <p>
                    <div>
                        <h4>¿Tiene apoderado?</h4>
                    </div>
                </p>
                <label class="checkbox-inline">
                    <input type="checkbox" value="">Si
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" value="">No
                </label>
                <p>
                    <div class="page-header">
                        <h4>A continuación, ingrese los datos del apoderado</h4>
                    </div>
                </p>
                <div class="form-group">
                    <label for="usr">Numero de cedula:</label>
                    <input type="text" class="form-control" id="cedula">
                </div>
                <div class="form-group">
                    <label for="pwd">Nombre:</label>
                    <input type="text" class="form-control" id="nombre">
                </div>
                <div class="form-group">
                    <label for="pwd">Primer apellido:</label>
                    <input type="text" class="form-control" id="nombre">
                </div>
                <div class="form-group">
                    <label for="pwd">Segundo apellido:</label>
                    <input type="text" class="form-control" id="nombre">
                </div>
                <div class="form-group">
                    <label for="pwd">Teléfono:</label>
                    <input type="text" class="form-control" id="nombre">
                </div>
                <div class="form-group">
                    <label for="pwd">Dirección de correspondencia:</label>
                    <input type="text" class="form-control" id="nombre">
                </div>
                <div class="form-group">
                    <label for="pwd">Correo electrónico:</label>
                    <input type="text" class="form-control" id="nombre">
                </div>
            </div>

            <h4>¿Es menor de edad?</h4>
            <label class="radio-inline">
                <input type="radio" name="optradio">Si
            </label>
            <label class="radio-inline">
                <input type="radio" name="optradio">No
            </label>
            <div class="page-header">
                <h4>A continuación, ingrese los datos del representante</h4>
            </div>

            <div class="form-group">
                <label for="sel1">Tipo de documento</label>
                <select class="form-control" id="sel1">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                </select>
            </div>

            <div class="form-group">
                <label for="usr">Numero de cedula:</label>
                <input type="text" class="form-control" id="cedula">
            </div>
            <div class="form-group">
                <label for="pwd">Nombre:</label>
                <input type="text" class="form-control" id="nombre">
            </div>
            <div class="form-group">
                <label for="pwd">Primer apellido:</label>
                <input type="text" class="form-control" id="nombre">
            </div>
            <div class="form-group">
                <label for="pwd">Segundo apellido:</label>
                <input type="text" class="form-control" id="nombre">
            </div>
            <div class="form-group">
                <label for="pwd">Teléfono:</label>
                <input type="text" class="form-control" id="nombre">
            </div>
            <div class="form-group">
                <label for="pwd">Dirección de correspondencia:</label>
                <input type="text" class="form-control" id="nombre">
            </div>
            <div class="form-group">
                <label for="pwd">Correo electrónico:</label>
                <input type="text" class="form-control" id="nombre">
            </div>

            <p>
                <h4>¿Autoriza a la secretaria de movilidad para el envió de correspondencia vía correo electrónico?</h4>
            </p>

            <label class="radio-inline">
                <input type="radio" name="optradio">Si
            </label>
            <label class="radio-inline">
                <input type="radio" name="optradio">No
            </label>
            <p>
                <h4>A continuación, use las siguientes casillas para el registro de la decisión tomada</h4>
            </p>
            <label class="radio-inline">
                <input type="radio" name="optradio">Contraventor
            </label>
            <label class="radio-inline">
                <input type="radio" name="optradio">Solicitar revisión de exoneración
            </label>
            <label class="radio-inline">
                <input type="radio" name="optradio">Suspender audiencia – Programar nueva
            </label>
            <p>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Observaciones:</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="5" id="comment"></textarea>
                    </div>
                </div>
                </br></br></br></br></br>
                </br>
                <p>
                    <h4>Recurso de reposición</h4>
                </p>
                <label class="radio-inline">
                    <input type="radio" name="optradio">Si
                </label>
                <label class="radio-inline">
                    <input type="radio" name="optradio">No
                </label>
                <label class="radio-inline" for="pwd">Decisión de la reposición:</label>
                <div class="radio-inline">
                    <select class="form-control" id="sel1">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </select>
                </div>
                <p>
                    <h4>Recurso de apelación</h4>
                </p>
                <label class="radio-inline">
                    <input type="radio" name="optradio">Si
                </label>
                <label class="radio-inline">
                    <input type="radio" name="optradio">No
                </label>
                <p>
                    <h4>Concedió apelación </h4>
                </p>
                <label class="radio-inline">
                    <input type="radio" name="optradio">Si
                </label>
                <label class="radio-inline">
                    <input type="radio" name="optradio">No
                </label>
                <p>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Guardar impugnación</button>
                </div>
                </p>

        </div>

    </div>
</@comp.comparendolayout>