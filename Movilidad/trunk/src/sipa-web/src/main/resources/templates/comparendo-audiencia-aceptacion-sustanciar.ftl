<#import 'comparendo-layout.ftl' as comp />
<@comp.comparendolayout>
    <div class="jumbotron">
        <h1>Sustanciar</h1>
        <p>
            Pulsa el siguiente botón para abrir el procesador de texto con la plantilla del acto administrativo
            correspondiente al trámite. Cada vez que guardes el sistema almacenará los cambios en el servidor central.
        </p>
        <p>
            <a href="ms-word:ofe|u|https://cmism.ingenian.com/alfresco/aos/${audiencia.comparendo.id}/${audiencia.documento.id}_/${audiencia.documento.originalName}" class="btn btn-primary btn-md">Abrir documento con Microsoft Word&copy;</a>
        </p>
    </div>
</@comp.comparendolayout>   