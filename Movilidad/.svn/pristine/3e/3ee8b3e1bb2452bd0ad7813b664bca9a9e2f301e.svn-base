package co.gov.movilidadbogota.sipa.bpm;

import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Operación que genera un documento a partir de una plantilla. Los datos con
 * los que se hace el merge de la plantilla
 */
public class GenerarDocumentoDesdePlantilla implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerarDocumentoDesdePlantilla.class);
    @Autowired
    private DocumentoService documentoService;

    public GenerarDocumentoDesdePlantilla(DocumentoService documentoService) {
        LOGGER.info("Constructor con documentoService=" + documentoService);
        this.documentoService = documentoService;
    }

    public void execute(DelegateExecution execution) throws Exception {
        //execution.getBpmnModelElementInstance().getAttributeValue("sipaServicesClient");
        String nombrePlantilla = (String) execution.getVariable("nombrePlantilla");
        LOGGER.info("Nombre plantilla: " + nombrePlantilla);
        String idcomparendo = (String) execution.getVariable("idComparendo");
        LOGGER.info("Id comparendo: " + idcomparendo);
        String originalName = (String) execution.getVariable("originalName");
        LOGGER.info("Original name: " + originalName);
        String idTipoDocumental = (String) execution.getVariable("idTipoDocumental");
        LOGGER.info("Id tipo documental: " + idTipoDocumental);

        CommandContext ctxResultado = documentoService.generarDocumento(nombrePlantilla, new CommandContext()
                .put("idComparendo", idcomparendo)
                .put(DocumentoService.CTX_DOCUMENTO_ORIGINAL_NAME, originalName)
                .put(DocumentoService.CTX_DOCUMENTO_TIPO_DOCUMENTAL, idTipoDocumental)
        );
        execution.setVariable(DocumentoService.CTX_DOCUMENTO_ID, ctxResultado.get(DocumentoService.CTX_DOCUMENTO_ID));
    }
}
