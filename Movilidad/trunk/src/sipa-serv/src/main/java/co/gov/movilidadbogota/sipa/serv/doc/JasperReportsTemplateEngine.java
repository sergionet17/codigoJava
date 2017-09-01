package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.data.doc.Plantilla;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementación de un motor de plantillas basado en JasperReports
 */
@Component
public class JasperReportsTemplateEngine implements TemplateEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(JasperReportsTemplateEngine.class);

    private ConcurrentHashMap<String, JasperReport> reportCache = new ConcurrentHashMap<>();

    @Override
    public byte[] merge(Plantilla plantilla, byte[] bytesPlantilla, Map<String, Object> context) {

        // Compila la plantilla
        JasperReport report = reportCache.get(plantilla.getNombre());
        if(report == null) {
            try {
                report = JasperCompileManager.compileReport(new ByteArrayInputStream(bytesPlantilla));
                reportCache.put(plantilla.getNombre(), report);
            } catch (JRException e) {
                LOGGER.warn(String.format("Compilando el reporte '%s'", plantilla.getNombre()), e);
                throw new RuntimeException(e);
            }
        }

        // Crea el datasource a partir de la colección de datos suministrados
        JRBeanCollectionDataSource datasource = null;
        Object object = context.get(DEFAULT_COLLECTION);
        if (object != null && object instanceof Collection) {
            Collection collection = (Collection) object;
            datasource = new JRBeanCollectionDataSource(collection);
        }

        // Crea el objeto printer con la combinación de la plantilla con los datos
        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport(report, context, datasource);
        } catch (JRException e) {
            LOGGER.warn(String.format("Combinando el reporte '%s'", plantilla.getNombre()), e);
            throw new RuntimeException(e);
        }

        if(Constants.MIME_TYPE_PDF.equals(plantilla.getContentTypeProducido())) {
            // Transforma el printer genérico en una stream que corresponde al PDF
            try {
                return JasperExportManager.exportReportToPdf(jasperPrint);
            } catch (JRException e) {
                LOGGER.warn(String.format("Creando el PDF del reporte %s", plantilla.getNombre()), e);
                throw new RuntimeException(e);
            }
        } else {
            LOGGER.warn(String.format("Exportando el reporte '%s'", plantilla.getNombre()));
            throw new RuntimeException(new Exception("Formato no soportado: " + plantilla.getContentTypeProducido()));
        }

    }

}
