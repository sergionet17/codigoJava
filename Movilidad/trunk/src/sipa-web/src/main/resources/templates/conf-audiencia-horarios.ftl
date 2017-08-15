
<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@l.main>
<div class="well sipa-well">
<h1>Horarios de Audiencia</h1>
<div class="container-fluid">
    

<div class="table-responsive">
<table class="table table-condensed table-bordered">

<thead>
    <tr>
        <th>Tipo de audiencia</th>
        <th>Vigencia</th>
        <th>Horario</th>
    </tr>
</thead>
<tbody>
    <tr>
        <td>Audiencia en que se comparece con abogado o practica de pruebas sobre inmovilización de vehículo</td>
        <td>desde 2017-01-01<br/>hasta [indefinido]</td>
        <td>L-V 07:00-12:00<br/>L-V 14:00-18:00</td>
    </tr>
    <tr>
        <td>Audiencia de práctica de pruebas o notificación de fallo</td>
        <td>desde 2017-01-01<br/>hasta [indefinido]</td>
        <td>L-V 07:00-12:00<br/>L-V 14:00-18:00</td>
    </tr>
    <tr>
        <td>Rango cuando no encuentra cupo para audiencia se habilitan todos los horarios</td>
        <td>desde 2017-01-01<br/>hasta [indefinido]</td>
        <td>L-V 07:00-12:00<br/>L-V 14:00-18:00</td>
    </tr>
</tbody>

</table>
</div>
<div class="pull-right">
    <p>
        <a href="horarios/nuevo" class="btn btn-success">Nuevo horario</a>
    </p>
</div>
</div>
</div>
</@l.main>