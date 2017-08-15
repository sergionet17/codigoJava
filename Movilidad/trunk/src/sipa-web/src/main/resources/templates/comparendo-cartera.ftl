<#import '/comparendo-layout.ftl' as comp />
<@comp.comparendolayout>
<div>&nbsp;</div>
<div class="container-fluid">
    <div class="table-responsive">
        <#if cartera??>
            <table class="table table-bordered">
            <thead>
            <tr>
                <th>Fecha</th>
                <th>Concepto</th>
                <th class="text-right">Débito</th>
                <th class="text-right">Crédito</th>
            </tr>
            </thead>
            <tbody>
                <#list cartera as c>
                <tr>
                    <td><@l.dateFormat c.fechaEvento /></td>
                    <td>
                        <small>
                            ${(c.cuenta.naturalezaCuenta.nombre)!''}-${(c.cuenta.nombre)!''}<br>
                            ${(c.descripcion)!''}
                        </small>
                    </td>
                    <td class="text-right"><#if c.tipoMovimiento == true><@l.currencyFormat c.valor /></#if></td>
                    <td class="text-right"><#if c.tipoMovimiento == false><@l.currencyFormat (c.valor * -1) /></#if></td>
                 </tr>
                </#list>
                <tr>
                    <th colspan="2">Saldo</th>
                    <th colspan="2" class="text-right"><strong><@l.currencyFormat saldo /></strong></th>
            </tbody>
            </table>

        <#else>
            <div class="jumbotron">
                <h1>Consulta de Cartera</h1>
                <p class="lead">En este momento no fue posible cargar la consulta. Por favor intente más tarde.</p>       
            </div>   
        </#if>
         <a class="btn btn-primary" href="<@s.url '/comparendo/crearVolantePago/${comparendo.id}'/>">
         Generar Volante de Pago</a>
    </div>
</div>
</@comp.comparendolayout>