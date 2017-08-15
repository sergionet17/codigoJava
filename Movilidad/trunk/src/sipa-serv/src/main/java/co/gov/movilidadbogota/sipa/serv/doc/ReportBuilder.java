package co.gov.movilidadbogota.sipa.serv.doc;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * Constructor de archivos PDF a partir de un árbol de objetos y una plantilla de JasperReports
 *
 * @author arturo.cruz
 */
@Component
public class ReportBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportBuilder.class);

    @Value("${app.jasper.templatePath:classpath:/reports}")
    public String templatePath;

    /**
     * Crea un PDF con la plantilla y los datos suministrados
     *
     * @param templateName El nombre de la plantilla que se busca en
     * @param data         Datos con los que se rellena la plantilla
     * @return Arreglo de bytes que corresponde al PDF
     */
    public byte[] pdf(String templateName, Collection<?> data) {
        return pdf(templateName, data, null);
    }

    /**
     * Crea un PDF con la plantilla y los datos suministrados
     *
     * @param templateName El nombre de la plantilla que se busca en
     * @param data         Datos con los que se rellena la plantilla
     * @param parameters   Parámetros extra para el reporte
     * @return Arreglo de bytes que corresponde al PDF
     */
    public byte[] pdf(String templateName, Collection<?> data, Map<String, Object> parameters) {

        // Obtiene el stream correpondiente a la plantilla
        InputStream templateInputStream = getTemplateInputStream(templateName);

        // Compila la plantilla
        JasperReport report;
        try {
            report = JasperCompileManager.compileReport(templateInputStream);
        } catch (JRException e) {
            LOGGER.error(String.format("Compilando el reporte %s", templateName), e);
            throw new RuntimeException(e);
        }

        // Crea el datasource a partir de la colección de datos suministrados
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(data);

        // Crea el objeto printer con la combinación de la plantilla con los datos
        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport(report, parameters, datasource);
        } catch (JRException e) {
            LOGGER.error(String.format("Combinando el reporte %s", templateName), e);
            throw new RuntimeException(e);
        }

        // Transforma el printer genérico en una stream que corresponde al PDF
        try {
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            LOGGER.error(String.format("Creando el PDF del reporte %s", templateName), e);
            throw new RuntimeException(e);
        }
    }

    private InputStream getTemplateInputStream(String templateName) {
        String fullTemplateName = String.format("%s/%s.jrxml", templatePath, templateName);
        if (templatePath.startsWith("classpath:")) {
            String resourceName = fullTemplateName.replace("classpath:", "");
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(resourceName);
            if (is == null) {
                throw new RuntimeException(String.format(
                        "El recurso %s no se encuentra en el classpath",
                        resourceName
                ));
            }
            return is;
        } else if (templatePath.startsWith("file:")) {
            String fileName = fullTemplateName.replace("file:", "");
            File file = new File(fileName);
            if (!file.exists()) {
                throw new RuntimeException(String.format(
                        "El archivo %s no existe",
                        fileName
                ));
            }
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                // No se hace nada porque la verificación de la existencia del archivo ya se hizo antes.
                LOGGER.info("El archivo de plantilla no se puede abrir", e);
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("El tipo de fuente de recursos no es válido, use 'classpath:' o 'file:'");
        }
    }

}
