<#include 'persona-header.ftl' />
<div>&nbsp;</div>
<div class="well sipa-well">
<div class="panel panel-primary sipa-ml-mr-10">
    <div class="panel-heading"><strong>Selección de abogado para llevar el proceso de reincidencia</strong></div>
    <div class="panel-body">
        <form class="horizontal-form" action="/persona/reincidencias/crear">
            <div class="form-group">
                <label for="dispo" class="col-sm-2 control-label">Abogado</label>
                <div class="col-sm-10" id="dispo">
                    <div class="radio">
                        <label>
                            <input type="radio" name="abogado" value="ANA CALDERON">
                            ANA CALDERON
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="abogado" value="ALVARO CEDIEL">
                            ALVARO CEDIEL
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="abogado" value="CAROLINA PRIETO">
                            CAROLINA PRIETO
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="abogado" value="ADRIANA ROJAS">
                            ADRIANA ROJAS
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <a href="/sipa/construccion"><button type="submit" class="btn btn-primary">Asignar abogado</button></a>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
