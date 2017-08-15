<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@l.main>
<div class="well sipa-well">

<h1>Top de Reincidencias</h1>
<div class="table-responsive">
<table class="table table-condensed table-bordered">

<thead>
    <tr>
        <th>Persona</th>
        <th>Identificación</th>
        <th>C. Impuestos</th>
        <th>C. Fallados</th>
        <th>Ident. reincidente</th>
        <th>Decl. reincidente</th>
        <th>Tiene proceso en curso</th>
        <th>Acciones</th>
    </tr>
</thead>
<tbody>
    <tr>
        <td><a href="../persona/reincidencias">JUAN ARTURO CRUZ ORTIZ</a></td>
        <td>CC 80109446</td>
        <td>34</td>
        <td>15</td>
        <td>30</td>
        <td>2</td>
        <td>No</td>
        <td><a href="../persona/reincidencias/iniciar">Iniciar proceso</a></td>
    </tr>
    <tr>
        <td><a href="../persona/reincidencias">CARLOS RODRIGUEZ</a></td>
        <td>CC 80445087</td>
        <td>33</td>
        <td>12</td>
        <td>25</td>
        <td>1</td>
        <td>No</td>
        <td><a href="../persona/reincidencias/iniciar">Iniciar proceso</a></td>
    </tr>
    <tr>
        <td><a href="../persona/reincidencias">HERMES PUENTES</a></td>
        <td>CC 80545789</td>
        <td>33</td>
        <td>12</td>
        <td>25</td>
        <td>1</td>
        <td>No</td>
        <td><a href="../persona/reincidencias/iniciar">Iniciar proceso</a></td>
    </tr>
    <tr>
        <td><a href="../persona/reincidencias">WILLIAM BARRIOS</a></td>
        <td>CC 80763986</td>
        <td>33</td>
        <td>12</td>
        <td>25</td>
        <td>1</td>
        <td>No</td>
        <td><a href="../persona/reincidencias/iniciar">Iniciar proceso</a></td>
    </tr>
    <tr>
        <td><a href="../persona/reincidencias">ALVARO CEDIEL</a></td>
        <td>CC 80908742</td>
        <td>33</td>
        <td>12</td>
        <td>25</td>
        <td>1</td>
        <td>Sí</td>
        <td></td>
    </tr>
</tbody>

</table>
</div>

</div>
</@l.main>
