package co.gov.movilidadbogota.sipa.util;

import org.hibernate.Session;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

/**
 * Interface para el editor de los parámetros
 *
 * @author lorenzo.pinango
 */
public interface ParametroEditor<T> {

    /**
     * Devuelve el nombre de la plantilla en la que se encuentra definidos los macros que se usan en el editor
     *
     * @return
     */
    String getTemplate();

    /**
     * Devuelve la cadena de texto que se inserta en la plantilla para "pintar" el editor
     * @param valor Es el valor inicial del editor
     * @return
     */
    String getWidget(String valor);

    /**
     * Devuelve el valor del editor a manera de cadena de texto para almacenarlo
     * @param obj
     * @return
     */
    String marshal(T obj);

    /**
     * Devuelve el valor del editor como objeto, interpretado a partir de la cadena de texto almacenada
     * @param content
     * @return
     */
    T unmarshal(String content);

    /**
     * Inyecta los datos al modelo para mostrarse en la vista
     *
     * @param model
     * @param session
     * @param metadata
     * @param messageSource
     * @param locale
     */
    void fillModel(Model model, Session session, String metadata, MessageSource messageSource, Locale locale) throws Exception;

}
