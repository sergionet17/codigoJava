package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.data.doc.Plantilla;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Implementación de un motor de plantillas basado en JasperReports
 */
@Component
public class XdocReportTemplateEngine implements TemplateEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(XdocReportTemplateEngine.class);

    @Override
    public byte[] merge(Plantilla plantilla, byte[] bytesPlantilla, Map<String, Object> context) {

        // Compila la plantilla
        IXDocReport report;
        try {
            report = XDocReportRegistry.getRegistry().loadReport(new ByteArrayInputStream(bytesPlantilla), TemplateEngineKind.Freemarker);
        } catch (Exception e) {
            LOGGER.warn(String.format("Compilando el reporte '%s'", plantilla.getNombre()), e);
            throw new RuntimeException(e);
        }

        // Se combina el reporte con los datos

        IContext contextPlantilla = null;
        ByteArrayOutputStream out = null;
        try {
            contextPlantilla = report.createContext();
            contextPlantilla.putMap(context);
            out = new ByteArrayOutputStream();
            report.process(contextPlantilla, out);
        } catch (Exception e) {
            LOGGER.warn(String.format("Combinando el reporte '%s'", plantilla.getNombre()), e);
            throw new RuntimeException(e);
        }

        if (Constants.MIME_TYPE_MSWORD.equals(plantilla.getContentTypeProducido())) {
            //Se retorna en formato docx
            return out.toByteArray();

        } else if (Constants.MIME_TYPE_PDF.equals(plantilla.getContentTypeProducido())) {
            // Transforma el printer genérico en una stream que corresponde al PDF
            Options options = Options.getFrom(DocumentKind.DOCX).to(ConverterTypeTo.PDF);

            // Se obtiene el convertidor
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            //Se convierte a PDF
            InputStream inPdf = new ByteArrayInputStream(out.toByteArray());
            OutputStream outPdf = new ByteArrayOutputStream();
            try {
                converter.convert(inPdf, outPdf, options);
                ByteArrayOutputStream bos = (ByteArrayOutputStream) outPdf;
                return bos.toByteArray();
            } catch (Exception e) {
                LOGGER.warn(String.format("Creando el PDF del reporte '%s'", plantilla.getNombre()), e);
                throw new RuntimeException(e);
            }
        } else {
            LOGGER.warn(String.format("Exportando el reporte '%s'", plantilla.getNombre()));
            throw new RuntimeException(new Exception("Formato no soportado: " + plantilla.getContentTypeProducido()));
        }

    }

}
