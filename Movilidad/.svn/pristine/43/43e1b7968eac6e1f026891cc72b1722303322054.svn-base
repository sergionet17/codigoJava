<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@l.main>
<div class="well sipa-well">
<div class="panel panel-primary">
<div class="panel-heading"><strong>Nuevo Horario de Audiencia</strong></div>
<div class="panel-body sipa-m-3">
<form class="form-horizontal" action="/conf/audiencia/horarios/crear" method="post">
<div class="form-group">
    <label for="tipo" class="col-sm-2 control-label">Tipo de audiencia</label>
    <div class="col-sm-6">
        <select name="tipo" id="tipo" class="form-control">
            <option>Audiencia en que se comparece con abogado o practica de pruebas sobre inmovilización de vehículo</option>
            <option>Audiencia de práctica de pruebas o notificación de fallo</option>
            <option>Rango cuando no encuentra cupo para audiencia se habilitan todos los horarios</option>
        </select>
    </div>
</div>
<div class="form-group">
    <label for="vigencia" class="col-sm-2 control-label">Vigencia</label>
    <div class="col-sm-10 form-inline" id="vigencia">
        <input type="text" class="form-control datepicker" placeholder="Desde"> - <input type="text" class="form-control datepicker" placeholder="Hasta">
    </div>
</div>
<div class="form-group">
    <label for="horario1" class="col-sm-2 control-label">Horario primer turno</label>
    <div class="col-sm-10 form-inline" id="horario1">
        <select name="h1" id="h1" class="form-control">
            <option>06:00</option>
            <option>07:00</option>
            <option>08:00</option>
            <option>09:00</option>
            <option>10:00</option>
            <option>11:00</option>
            <option>12:00</option>
            <option>13:00</option>
            <option>14:00</option>
            <option>15:00</option>
            <option>16:00</option>
            <option>17:00</option>
            <option>18:00</option>
            <option>19:00</option>
            <option>20:00</option>
            <option>21:00</option>
            <option>22:00</option>
        </select>
        -
        <select name="h2" id="h2" class="form-control">
            <option>06:00</option>
            <option>07:00</option>
            <option>08:00</option>
            <option>09:00</option>
            <option>10:00</option>
            <option>11:00</option>
            <option>12:00</option>
            <option>13:00</option>
            <option>14:00</option>
            <option>15:00</option>
            <option>16:00</option>
            <option>17:00</option>
            <option>18:00</option>
            <option>19:00</option>
            <option>20:00</option>
            <option>21:00</option>
            <option>22:00</option>
        </select>
    </div>
</div>
<div class="form-group">
    <label for="horario2" class="col-sm-2 control-label">Horario primer turno</label>
    <div id="horario2" class="col-sm-10 form-inline">
        <select name="h3" id="h3" class="form-control">
            <option>06:00</option>
            <option>07:00</option>
            <option>08:00</option>
            <option>09:00</option>
            <option>10:00</option>
            <option>11:00</option>
            <option>12:00</option>
            <option>13:00</option>
            <option>14:00</option>
            <option>15:00</option>
            <option>16:00</option>
            <option>17:00</option>
            <option>18:00</option>
            <option>19:00</option>
            <option>20:00</option>
            <option>21:00</option>
            <option>22:00</option>
        </select>
        -
        <select name="h4" id="h4" class="form-control">
            <option>06:00</option>
            <option>07:00</option>
            <option>08:00</option>
            <option>09:00</option>
            <option>10:00</option>
            <option>11:00</option>
            <option>12:00</option>
            <option>13:00</option>
            <option>14:00</option>
            <option>15:00</option>
            <option>16:00</option>
            <option>17:00</option>
            <option>18:00</option>
            <option>19:00</option>
            <option>20:00</option>
            <option>21:00</option>
            <option>22:00</option>
        </select>
    </div>
</div>
<div class="col-sm-offset-2 col-sm-10">
<@l.formSubmitDoubleButton labelb1="Guardar" labelb2="Cancelar" path="/sipa/conf/audiencia/horarios"/>
</div>
</form>
</div>
</div>
</div>
</@l.main>
