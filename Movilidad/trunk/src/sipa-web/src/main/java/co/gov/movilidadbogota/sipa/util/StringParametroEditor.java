package co.gov.movilidadbogota.sipa.util;

import org.hibernate.Session;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

/**
 * Editor para los parámetros tipo String
 *
 * @author lorenzo.pinango
 */
public class StringParametroEditor implements ParametroEditor<String> {
    @Override
    public String getTemplate() {
        return "";
    }

    @Override
    public String getWidget(String valor) {
        return "<@l.formGroupText name=\"valor\" label=\"Valor\" placeholder=\"Valor\" " +
                "value=\"" + valor + "\" validator=\"textValidator\" " +
                "size=\"255\" class=\"form-control\"/>";
    }

    @Override
    public String marshal(String obj) {
        return obj;
    }

    @Override
    public String unmarshal(String content) {
        return content;
    }

    @Override
    public void fillModel(Model model, Session session, String metadata, MessageSource messageSource, Locale locale) {

    }

}
