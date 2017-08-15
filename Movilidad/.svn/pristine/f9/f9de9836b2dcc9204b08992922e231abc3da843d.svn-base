package co.gov.movilidadbogota.sipa.util;

import co.gov.movilidadbogota.sipa.WebApplication;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Properties;

/**
 * Created by Diego Gomez on 11/07/2017.
 */
public class VelocityParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    /**
     * Directorio de plantillas
     */
    private static final String FOLDER_TEMPLATES = "templates/correo";

    /**
     * Referencia única
     */
    private static VelocityParser instance;

    /**
     * Inicializa Velocity.
     *
     * @throws Exception
     */
    private VelocityParser() throws Exception {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
    }

    /**
     * Obtiene la referencia únicar del parser de velocity.
     *
     * @return reerencia al parser
     * @throws Exception
     */
    public static VelocityParser getInstance() throws Exception {
        if (instance == null) {
            instance = new VelocityParser();
        }
        return instance;
    }

    /**
     * Genera el contenido final de acuerdo a la plantilla.
     *
     * @param plantilla
     * @return contenido de plantilla
     */
    public String getContenidoPorPlantilla(PlantillaDto plantilla) {
        String contenido = "";

        try {
            Template template = Velocity.getTemplate(FOLDER_TEMPLATES + "/" + plantilla.getNombre() + ".vm");
            VelocityContext context = new VelocityContext();
            context.put("parametros", plantilla.getParametros());
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            contenido = writer.getBuffer().toString();
            contenido = contenido.replace("\r", "");
            contenido = contenido.replace("\n", "");
            LOGGER.info(contenido);

        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } catch (ParseErrorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contenido;
    }
}
