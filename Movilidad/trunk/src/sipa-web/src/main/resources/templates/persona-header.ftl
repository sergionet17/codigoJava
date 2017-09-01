<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@l.main>
<div class="col-md-12">

    <h1>Vista de Persona</h1>

    <ul class="nav nav-tabs">
    <#-- Cambiar <a href="/sipa/construccion"> cuando el link funcione -->
<#--<a href="../persona/general">no funciona por favor revisar -->
        <li role="presentation" <#if tab == 'general'>class="active"</#if>><a href="/sipa/construccion">General</a></li>
         <#-- Cambiar <a href="/sipa/construccion"> cuando el link funcione -->
<#--<a href="../persona/comparendos">no funciona por favor revisar -->
        <li role="presentation" <#if tab == 'comparendos'>class="active"</#if>><a href="/sipa/construccion">Comparendos</a></li>
        <li role="presentation" <#if tab == 'reincidencias'>class="active"</#if>><a href="../persona/reincidencias">Reincidencias</a></li>
    </ul>
    </div>
    </@l.main>