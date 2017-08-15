package co.gov.movilidadbogota.sipa.util;

import co.gov.movilidadbogota.sipa.trans.MetadataEditor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.lang.annotation.Annotation;
import java.util.Locale;

/**
 * Editor para los parámetros tipo Object
 *
 * @author lorenzo.pinango
 */
public class ObjectParametroEditor implements ParametroEditor<Object> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getTemplate() {
        return "editor-object.ftl";
    }

    @Override
    public String getWidget(String valor) {
        return "<@editorObject list=__OBJECT_LIST__ />";
    }

    @Override
    public String marshal(Object obj) {
        return obj.toString();
    }

    @Override
    public Object unmarshal(String content) {
        try {
            return Class.forName(content).getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void fillModel(Model model, Session session, String metadata,
                          MessageSource messageSource, Locale locale) throws Exception {
        MetadataEditor metadataObj = mapper.readValue(metadata, MetadataEditor.class);
        if (metadataObj == null)
            throw new NullPointerException("La metadata del editor no se encuentra especificada en el parámetro");
        if (metadataObj.getClase().isEmpty())
            throw new NullPointerException("La clase no fue especificada en la metadata del parámetro");
        if (metadataObj.getCampo().isEmpty())
            throw new NullPointerException("El campo no fue especificada en la metadata del parámetro");
        Class clazz = Class.forName(metadataObj.getClase());
        if (clazz == null)
            throw new ClassNotFoundException("La clase " + metadataObj.getClase() + " especificada en la " +
                    "metadata del editor del parámetro no fue encontrada");
        Annotation[] anotaciones = clazz.getDeclaredAnnotations();
        Boolean isEntity;
        isEntity = false;
        for (Annotation anotacion : anotaciones) {
            if ("javax.persistence.Entity".equals(anotacion.annotationType().getTypeName())) {
                isEntity = true;
                break;
            }
        }
        if (!isEntity)
            throw new ClassNotFoundException("La clase " + metadataObj.getClase() + " especificada en la " +
                    "metadata del editor del parámetro no es una entidad");
        Criteria cr = session.createCriteria(clazz);
        model.addAttribute("__OBJECT_LIST__", ControllerUtils.listToMapWithDefault(cr.list(),
                metadataObj.getCampo(), messageSource, locale));
    }
}
