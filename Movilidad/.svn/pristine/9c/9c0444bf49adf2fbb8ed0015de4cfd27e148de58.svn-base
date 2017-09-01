package co.gov.movilidadbogota.sipa.util;

import org.hibernate.Session;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

/**
 * Editor para los parámetros tipo Datetime
 *
 * @author lorenzo.pinango
 */
public class DateTimeParametroEditor implements ParametroEditor<String> {
    @Override
    public String getTemplate() {
        return "";
    }

    @Override
    public String getWidget(String valor) {
        return "<@l.formGroupDate name=\"valor\" label=\"Valor\" placeholder=\"Valor\" " +
                "value=\"" + valor + "\" class=\"form-control\" tipo=\"datetime\"/>";
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
