<#macro pinta menu>
    <strong>
        <#if menu.url?has_content>
            <a href="<@s.url menu.url/>">${menu.label}</a>
        <#else>
            ${menu.label}
        </#if>
    </strong>
    ${menu.description!""} <#if menu.permissions??><small><span class="label label-success">${menu.permissions!""}</span></small></#if>
    <#if menu.submenu?? >
        <ul>
        <#list menu.submenu as m>
            <li><@pinta m/></li>
        </#list>
        </ul>
    </#if>
</#macro>

<@l.main>
<div class="well sipa-well">
    <h1>Mapa de navegación</h1>
    <@pinta menu=mapaMenu />
</div>

</@l.main>

