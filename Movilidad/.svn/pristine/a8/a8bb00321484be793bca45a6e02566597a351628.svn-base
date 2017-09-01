package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.data.doc.TipoPlantilla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TemplateEngineFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateEngineFactory.class);

    public static TemplateEngine getEngineFor(TipoPlantilla engineName) throws Exception {

        if (engineName == null) {
            String msg = "Tipo de plantilla es 'null'. El tipo de plantilla es necesario para seleccionar el motor de plantillas";
            LOGGER.warn(msg);
            new IllegalArgumentException(msg);
        }

        if (TemplateEngineName.FREEMARKER.equals(engineName)) {
            // TODO: Implementar
            return null;
        } else if (TipoPlantilla.JASPERREPORTS.getId().equals(engineName.getId())) {
            return new JasperReportsTemplateEngine();
        } else if (TipoPlantilla.XDOCREPORT.getId().equals(engineName.getId())) {
            return new XdocReportTemplateEngine();
        } else {
            throw new Exception(String.format("El tipo de motor '%s' no está soportado", engineName));
        }
    }

}
