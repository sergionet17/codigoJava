<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@l.main>
<div class="col-md-12 sipa-p-0">
    <h1>Vista de Comparendo</h1>

    <ul class="nav nav-tabs">
        <li role="presentation" <#if tab == 'general'>class="active"</#if>><a href="../comparendo/general/1">General</a></li>
        <li role="presentation" <#if tab == 'expediente'>class="active"</#if>><a href="../comparendo/expediente">Expediente</a></li>
        <li role="presentation" <#if tab == 'cartera'>class="active"</#if>><a href="../comparendo/cartera">Cartera</a></li>
        <li role="presentation" <#if tab == 'audiencias'>class="active"</#if>><a href="../comparendo/audiencias">Audiencia</a></li>
    </ul>
    </div>
    </@l.main>